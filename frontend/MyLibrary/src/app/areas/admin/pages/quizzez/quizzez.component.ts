import { AddEditQuizzComponent } from './add-edit-quizz/add-edit-quizz.component';
import { Quizz } from '../../../../Models/admin/QuizzModel';
import { ActivatedRoute } from "@angular/router";

import { Component, OnInit, ViewChild } from "@angular/core";
import { MatTableDataSource } from "@angular/material/table";
import { TitleService } from "./../../services/title.service";
import { DomSanitizer } from "@angular/platform-browser";
import { ToastrService } from "src/app/services/toastr.service";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { MatDialog } from "@angular/material/dialog";
import { ApiResponse } from "src/app/Models/general/api-response";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { QuizzService } from './quizz.service';


@Component({
  selector: 'app-quizzez',
  templateUrl: './quizzez.component.html',
  styleUrls: ['./quizzez.component.scss']
})


export class QuizzezComponent implements OnInit {

  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;


  quizzezArray: Quizz[] = [];
  _addquizz: Quizz;
  displayedColumns: string[] = [

    "quizzId",
    "quizzQuestion",
    "quizzCorrectAnswer",
    "quizzStartDate",
    "quizzEndDate"



  ];
  dataSource: MatTableDataSource<Quizz> = new MatTableDataSource(
    this.quizzezArray
  );
  fromRedirect: boolean = false;


  constructor(  private titleService: TitleService,
                private toastr: ToastrService,
                private sanitizer: DomSanitizer,
                private loadingService: LoadingService,
                public dialog: MatDialog,
                private quizzService:QuizzService,
                private activatedRoute: ActivatedRoute) {

                  activatedRoute.queryParamMap.subscribe(params => {
                    if (params["action"] == "add") {
                      this.fromRedirect = true;
                    }
                    if(params["action"]==undefined && (params as any).params["action"]=="add"){
                      this.fromRedirect = true;
                    }
                  });

                 }

  ngOnInit(): void {
    if (this.fromRedirect == true) {
      this.AddQuizzez();
    }
    this.GetQuizzezs();
    this.titleService.setTitle("faPrint", "Quizzuri");


  }

applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.quizzezArray);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

GetQuizzezs() {
    this.loadingService.start();
    this.quizzService
      .GetQuizzez()
      .subscribe((response: ApiResponse<Quizz[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: "info",
              title: "Nu exista quizzuri  in baza de date"
            });
          }

          this.quizzezArray = response.body;
          this.updateDataSouce();
        }
      });
  }

DeleteQuizzez(quizz: Quizz) {
    this.toastr.Swal.fire({
      title: "Esti sigur ca vrei sa stergi acest quizz?",
      html: `Id: <b>${quizz.quizzId}</b> - Nume: <b>${quizz.quizzQuestion}</b>`,
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Da",
      cancelButtonText: "Nu"
    }).then(result => {
      if (result.value) {
        this.loadingService.start();

        this.quizzService
          .DeleteQuizzez(quizz.quizzId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (
              response &&
              response.status == ApiResponseType.SUCCESS &&
              response.body == true
            ) {
              this.toastr.Toast.fire({
                title: "Editura a fost stearsa.",
                icon: "success"
              });
              this.GetQuizzezs();
            } else {
              this.toastr.Swal.fire(
                "Eroare!",
                "A aparut o eroare la stergere, incearca din nou!",
                "error"
              );
            }
          });
      }
    });
  }

EditQuizzez(item: Quizz) {
    const dialogRef = this.dialog.open(AddEditQuizzComponent, {
      width: '40%',
      data: {
        type: "edit",
        model: Object.assign({}, item)
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.EditQuizzezConfirm(result, item);
      }
    });
  }

EditQuizzezConfirm(newQuizzez: Quizz, oldQuizzez: Quizz) {
    this.loadingService.start();

    this.quizzService
      .UpdateQuizzez(newQuizzez, newQuizzez.quizzId)
      .subscribe((response: ApiResponse<Quizz>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: "Quizzul a fost editat cu succes!",
            icon: "success"
          });

          const idxOld = this.quizzezArray.indexOf(oldQuizzez);
          this.quizzezArray[idxOld] = newQuizzez;
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            "Eroare!",
            "A aparut o eroare la editare, incearca din nou!",
            "error"
          );
        }
      });
  }

AddQuizzez() {
    const dialogRef = this.dialog.open(AddEditQuizzComponent, {
      width: '40%',
      data: {
        type: "add",
        model: this.quizzezArray
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddQuizzezConfirm(result);
      }
    });
  }

AddQuizzezConfirm(publisher: Quizz) {
    this.loadingService.start();
    this.quizzService.AddQuizzez(publisher).subscribe(
      (response: ApiResponse<Quizz>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: "Quizzul a fost adaugat cu succes",
            icon: "success"
          });
          this.quizzezArray.push(response.body);
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            "Eroare!",
            "A aparut o eroare la adaugare, incearca din nou!",
            "error"
          );
        }
      },
      error => {
        this.toastr.Swal.fire(
          "Eroare!",
          "A aparut o eroare la adaugare, incearca din nou!",
          "error"
        );
      }
    );
  }
}
