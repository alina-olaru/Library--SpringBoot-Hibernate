import { Component, OnInit } from '@angular/core';
import { QuizzService } from '../../admin/pages/quizzez/quizz.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { Quizzez } from 'src/app/Models/admin/QuizzezModel';
import { ToastrService } from 'src/app/services/toastr.service';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ApiResponse } from 'src/app/Models/general/api-response';
@Component({
  selector: 'app-quizz',
  templateUrl: './quizz.component.html',
  styleUrls: ['./quizz.component.scss']
})
export class QuizzComponent implements OnInit {

  quizzezArray: Quizzez[] = [];
  Day:any;
  Month:any;
  Year:any;


  constructor(
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private quizzService: QuizzService,
    private activatedRoute: ActivatedRoute
  ) {
    this.Day = new Date().getDay;
    this.Month = new Date().getMonth;
    this.Year = new Date().getFullYear;
  }

  ngOnInit(): void {

    this.loadingService.start();
    this.quizzService
      .GetQuizzez()
      .subscribe((response: ApiResponse<Quizzez[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          console.log(response);
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: "info",
              title: "Nu exista quizzuri la care sa ai acces momentan."
            });
          }

          this.quizzezArray = response.body;
          console.log(this.quizzezArray);
          }
      });

  }

}
