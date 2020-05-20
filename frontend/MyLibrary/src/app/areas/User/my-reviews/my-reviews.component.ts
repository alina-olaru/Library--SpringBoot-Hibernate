import { ApiResponse } from './../../../Models/general/api-response';
import { BookUser } from './../../../Models/BookUser';
import { ReviewService } from './../../Home/book-details/review.service';
import { Review } from 'src/app/Models/home/Review';
import { Component, OnInit, Renderer2 } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { AddBookViaOCRComponent } from '../add-book-via-ocr/add-book-via-ocr.component';
import { FormBuilder } from '@angular/forms';
import { LoginService } from '../../login/login.service';
import { PersonalBookService } from '../../Home/book-details/personalBook.service';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../../Home/welcome/LandingBooks.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Book } from 'src/app/Models/admin/BookModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-my-reviews',
  templateUrl: './my-reviews.component.html',
  styleUrls: ['./my-reviews.component.scss']
})
export class MyReviewsComponent implements OnInit {

  reviews:Review[]=[];
  user : BookUser=null;
  constructor(

    private renderer: Renderer2,
    private formBuilder: FormBuilder,
    private auth: LoginService,
    private persBookService: PersonalBookService,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private reviewService : ReviewService

  ) { }

  ngOnInit(): void {
   // if(this.auth.getUser()!=null && this.auth.getUser()!=undefined){
      this.user = this.auth.getUser();
      console.log(this.user + " ");
   // }
  //  else{
     // this.user = null;
 //   }
    this.getReviews();
  }


  getReviews(){
    if(this.user!=null && this.user != undefined){
    this.reviewService.getReviewsForUser(this.user.userId).subscribe((Response:ApiResponse<Review[]>)=>{

      if(Response && Response.status == ApiResponseType.SUCCESS){
        this.reviews = Response.body;
      }
    })
    }

  }
}
