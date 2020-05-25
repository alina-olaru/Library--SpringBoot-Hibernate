import { Router } from '@angular/router';
import { Route } from '@angular/compiler/src/core';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { BookUser } from 'src/app/Models/BookUser';
import { RecommendationsService } from './recommendations.service';
import { Component, OnInit, Renderer2 } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { LoginService } from '../../login/login.service';
import { PersonalBookService } from '../../Home/book-details/personalBook.service';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../../Home/welcome/LandingBooks.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ReviewService } from '../../Home/book-details/review.service';
import { Book } from 'src/app/Models/admin/BookModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.scss']
})
export class RecommendationsComponent implements OnInit {

  user : BookUser = null;
  books : Book[] = [];
  constructor(
    private renderer: Renderer2,
    private formBuilder: FormBuilder,
    private auth: LoginService,
    private persBookService: PersonalBookService,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private reviewService : ReviewService,
    private RecommendationsService : RecommendationsService,
    private router : Router

  ) {
    this.user = this.auth.getUser();

  }

  ngOnInit(): void {
    this.getRecommendations();
  }

  getRecommendations(){
    if(this.user != null && this.user != undefined){
    this.RecommendationsService.getRecommendations(this.user.userId).subscribe((response : ApiResponse<Book[]>)=>{

      if(response && response.status == ApiResponseType.SUCCESS){
        this.books = response.body;
      }
    })

    }
  }

  getUrlImageForBook(book : Book)
    {
      if (book.bookImageDb != null) {
        return "url('data:image/jpg;base64," + book.bookImageDb + "')";
      } else {
        return "url('../../../../assets/no.png')";
      }    }
    viewDetails(bookId : number){

      this.router.navigate(['/home', 'book',  bookId]);
    }

}
