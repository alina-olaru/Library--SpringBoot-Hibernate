import { AddEditAuthorComponent } from './add-edit-author/add-edit-author.component';
import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Author } from 'src/app/Models/admin/AuthorModel';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { AuthorsService } from './authors.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ActivatedRoute } from '@angular/router';
import { Book } from 'src/app/Models/admin/BookModel';

@Component({
  selector: 'app-authors',
  templateUrl: './authors.component.html',
  styleUrls: ['./authors.component.scss']
})
export class AuthorsComponent implements OnInit {
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  authors: Author[] = [];
  _addAuthor: Author;
  displayedColumns: string[] = ['authorId', 'firstName', 'lastName', 'actions'];
  fromRedirect = false;

  dataSource: MatTableDataSource<Author> = new MatTableDataSource(this.authors);

  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    public authorsService: AuthorsService,
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
      this.AddAuthor();
    }
    this.GetAuthors();
    this.titleService.setTitle('faPenFancy', 'Autori');
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.authors);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
  GetAuthors() {
    this.loadingService.start();
    this.authorsService
      .GetAuthors()
      .subscribe((response: ApiResponse<Author[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: 'info',
              title: 'Nu exista autori in baza de date'
            });
          }

          this.authors = response.body;
          this.updateDataSouce();
        }
      });
  }

  DeleteAuthor(autor: Author) {
    this.toastr.Swal.fire({
      title: 'Esti sigur ca vrei sa stergi acest bautura?',
      html: `Id: <b>${autor.authorId}</b> - Nume: <b>${autor.firstName} ${autor.lastName}</b>`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Da',
      cancelButtonText: 'Nu'
    }).then(result => {
      if (result.value) {
        this.loadingService.start();

        this.authorsService
          .DeleteAuthor(autor.authorId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (
              response &&
              response.status == ApiResponseType.SUCCESS
            ) {
              this.toastr.Toast.fire({
                title: 'Autorul a fost sters.',
                icon: 'success'
              });
              this.GetAuthors();
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

  EditAuthor(item: Author) {
    const dialogRef = this.dialog.open(AddEditAuthorComponent, {
      width: '400px',
      data: {
        type: 'edit',
        model: Object.assign({}, item)
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.EditAuthorConfirm(result, item);
      }
    });
  }

  EditAuthorConfirm(newAuthor: Author, oldAuthor: Author) {
    this.loadingService.start();

    this.authorsService
      .UpdateAuthor(newAuthor, newAuthor.authorId)
      .subscribe((response: ApiResponse<Author>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Autorul a fost editat cu succes!',
            icon: 'success'
          });

          const idxOld = this.authors.indexOf(oldAuthor);
          this.authors[idxOld] = newAuthor;
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
            response.message,
            'error'
          );
        }
      });
  }

  AddAuthor() {
    const dialogRef = this.dialog.open(AddEditAuthorComponent, {
      width: '400px',
      data: {
        type: 'add',
        model: this._addAuthor
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddAuthorConfirm(result);
      }
    });
  }

  AddAuthorConfirm(author: Author) {
    this.loadingService.start();
    this.authorsService.AddAuthor(author).subscribe(
      (response: ApiResponse<Author>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Autorul a fost adaugat cu succes',
            icon: 'success'
          });
          this.authors.push(response.body);
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
           response.message,
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
