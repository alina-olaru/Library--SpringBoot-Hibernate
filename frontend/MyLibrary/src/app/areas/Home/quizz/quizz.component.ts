import { QuizzUser } from "./../../../Models/user/QuizzUser";
import { LoginService } from "src/app/areas/login/login.service";
import { BookUser } from "src/app/Models/BookUser";
import { QuizzPublicService } from "./QuizzPublic.service";
import { Component, OnInit } from "@angular/core";
import { QuizzService } from "../../admin/pages/quizzez/quizz.service";
import { DomSanitizer } from "@angular/platform-browser";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { MatDialog } from "@angular/material/dialog";
import { ActivatedRoute, Router } from "@angular/router";
import { Quizz } from "src/app/Models/admin/QuizzModel";
import { ToastrService } from "src/app/services/toastr.service";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { ApiResponse } from "src/app/Models/general/api-response";
import { monitorEventLoopDelay } from "perf_hooks";
@Component({
  selector: "app-quizz",
  templateUrl: "./quizz.component.html",
  styleUrls: ["./quizz.component.scss"],
})
export class QuizzComponent implements OnInit {
  quizzezArray: Quizz[] = [];
  Day: any;
  Month: any;
  Year: any;
  chosenAnswer: string = null;
  user: BookUser = null;
  myQuizzez: Quizz[] = [];
  alreadyAnswered: boolean = false;
  currentDate: Date;
  2;

  constructor(
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private quizzPubService: QuizzPublicService,
    private activatedRoute: ActivatedRoute,
    private auth: LoginService,
    private router: Router
  ) {
    this.Day = new Date().getDay;
    this.Month = new Date().getMonth;
    this.Year = new Date().getFullYear;
    this.user = this.auth.getUser();
    this.currentDate = new Date();
  }

  ngOnInit(): void {
    this.loadingService.start();
    this.quizzPubService
      .GetQuizzez()
      .subscribe((response: ApiResponse<Quizz[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          console.log(response);
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: "info",
              title: "Nu exista quizzuri la care sa ai acces momentan.",
            });
          }

          this.quizzezArray = response.body;

          response.body.forEach((el)=>{
            if(el.quizzStartDate<this.currentDate && el.quizzEndDate>this.currentDate){
              this.myQuizzez.push(el);
            }
          })
          console.log(this.quizzezArray);
        }
      });
  }

  checkQuizz(id: number) {
    this.alreadyAnswered = false;
    this.myQuizzez.forEach((e) => {
      if (e.quizzId == id) {
        this.alreadyAnswered = true;
      }
    });
  }

  AnswerToQuizz(quizz: Quizz, answer: string) {
    if (this.user == null || this.user == undefined) {
      this.toastr.Swal.fire({
        title: "Trebuie sa te loghezi pentru a aduna vouchere!!",
        html: ``,
        // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da",
        cancelButtonText: "Nu",
      }).then((result) => {
        if (result.value) {
          this.router.navigate(["/login"]);
        }
      });
    } else {
      this.quizzPubService
        .GetQuizzezForUser(this.user.userId)
        .subscribe((response) => {
          if ((response.status = ApiResponseType.SUCCESS)) {
            this.myQuizzez = response.body;
            this.checkQuizz(quizz.quizzId);
            if (this.alreadyAnswered == false) {
              let model = new QuizzUser();
              model.userForQuizz = this.user;
              model.quizzForUser = quizz;
              this.quizzPubService
                .AddQuizzToUser(model,answer)
                .subscribe((response: ApiResponse<QuizzUser>) => {
                  if (response && response.status == ApiResponseType.SUCCESS) {
                    if (
                      quizz.quizzCorrectAnswer.toLowerCase == answer.toLowerCase
                    ) {
                      this.toastr.Swal.fire({
                        title: "Ati raspuns corect!!",
                        width: 600,
                        allowOutsideClick: true,
                        allowEscapeKey: true,
                        padding: "3em",
                        background:
                          "#fff url(https://themotherofallnerds.com/wp-content/uploads/2018/05/2-qscLHLb.gif)",
                        backdrop: `
                          rgba(0,0,0,0.396)
                          url("https://i.pinimg.com/originals/8d/8d/c0/8d8dc0ac8e4d6decf1b45a0a717088fa.gif")
                         center top
                          no-repeat
                        `,
                      });
                    } else {
                      this.toastr.Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Raspuns gresit!",
                        footer: "Raspuns gresit!",
                      });
                    }
                  }
                });
            } else {
              this.toastr.Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Ati raspuns deja la intrebare",
                footer: " Ati raspuns deja la intrebare",
              });
            }
          } else {
            this.toastr.Swal.fire({
              icon: "error",
              title: "Oops...",
              text: response.message,
              footer: "Raspuns gresit!",
            });
            //           }
          }

          //     }
          // );
        });
    }
  }
}
