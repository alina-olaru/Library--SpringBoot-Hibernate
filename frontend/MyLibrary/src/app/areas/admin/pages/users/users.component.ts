import { AddEditUserComponent } from './add-edit-user/add-edit-user.component';

import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ActivatedRoute } from '@angular/router';
import { BookUser } from 'src/app/Models/BookUser';
import { UserService } from './user.service';
import { YesNoPipe } from "../../../../modules/pipes/yes-no.pipe";
@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  users: BookUser[] = [];
  _addUser: BookUser;




  displayedColumns: string[] = [
    "userId",
    "firstName",
    "lastName",
    "emailAdress",
    "phoneNumber",
    "newsletter",
    "adminPrivilege",
    "userPrivilege",
    "blocked",
    "username",
    "password",
    "actions"

  ];
  fromRedirect = false;

  dataSource: MatTableDataSource<BookUser> = new MatTableDataSource(this.users);

  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    public usersService: UserService,
    private activatedRoute: ActivatedRoute
  ) {
    activatedRoute.queryParamMap.subscribe(params => {
      if (params["action"] == "add") {
        this.fromRedirect = true;
      }
      if(params["action"]==undefined && (params as any).params["action"]=="add"){
        this.fromRedirect = true;
      }
    });
  }

  ngOnInit() {
    if (this.fromRedirect == true) {
      this.AddUser();
    }
    this.GetUsers();
    this.titleService.setTitle('faUsers', 'Useri');
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.users);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
  GetUsers() {
    this.loadingService.start();
    this.usersService
      .GetUsers()
      .subscribe((response: ApiResponse<BookUser[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: 'info',
              title: 'Nu exista useri in baza de date'
            });
          }

          this.users = response.body;
          this.updateDataSouce();
        }
      });
  }

  DeleteUser(user: BookUser) {
    this.toastr.Swal.fire({
      title: 'Esti sigur ca vrei sa stergi acest user?',
      html: `Id: <b>${user.userId}</b> - Nume: <b>${user.firstName} ${user.lastName}</b>`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Da',
      cancelButtonText: 'Nu'
    }).then(result => {
      if (result.value) {
        this.loadingService.start();

        this.usersService
          .DeleteUser(user.userId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (
              response &&
              response.status == ApiResponseType.SUCCESS &&
              response.body == true
            ) {
              this.toastr.Toast.fire({
                title: 'Userul a fost sters.',
                icon: 'success'
              });
              this.GetUsers();
            } else {
              this.toastr.Swal.fire(
                'Eroare!',
                'A aparut o eroare la stergere, incearca din nou!',
                'error'
              );
            }
          });
      }
    });
  }

  EditUser(item: BookUser) {
    const dialogRef = this.dialog.open(AddEditUserComponent, {
      width: '40&',
      data: {
        type: 'edit',
        model: Object.assign({}, item)
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.EditUserConfirm(result, item);
      }
    });
  }

  EditUserConfirm(newUser: BookUser, oldUser: BookUser) {
    this.loadingService.start();

    this.usersService
      .UpdateUser(newUser, newUser.userId)
      .subscribe((response: ApiResponse<BookUser>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Userul a fost editat cu succes!',
            icon: 'success'
          });

          const idxOld = this.users.indexOf(oldUser);
          this.users[idxOld] = newUser;
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la editare, incearca din nou!',
            'error'
          );
        }
      });
  }

  AddUser() {
    const dialogRef = this.dialog.open(AddEditUserComponent, {
      width: '40%',
      data: {
        type: 'add',
        model: this._addUser
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddUserConfirm(result);
      }
    });
  }

  AddUserConfirm(user: BookUser) {
    this.loadingService.start();
    this.usersService.AddUser(user).subscribe(
      (response: ApiResponse<BookUser>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Userul a fost adaugat cu succes',
            icon: 'success'
          });
          this.users.push(response.body);
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la adugare, incearca din nou!',
            'error'
          );
        }
      },
      error => {
        this.toastr.Swal.fire(
          'Eroare!',
          'A aparut o eroare la adugare, incearca din nou!',
          'error'
        );
      }
    );
  }
}
