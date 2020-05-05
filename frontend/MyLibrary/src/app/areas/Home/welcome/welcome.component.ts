import { Router } from '@angular/router';
import { getElement } from '@amcharts/amcharts4/core';
import { Category } from "src/app/Models/admin/CategoryModel";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { LandingBooksService } from "./LandingBooks.service";
import { Component, OnInit } from "@angular/core";
import { SliderComponent } from "../slider/slider.component";
import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
  faHeart
} from "@fortawesome/free-solid-svg-icons";
import { Book } from "src/app/Models/admin/BookModel";
import { ApiResponse } from "src/app/Models/general/api-response";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { FORMERR } from "dns";
@Component({
  selector: "app-welcome",
  templateUrl: "./welcome.component.html",
  styleUrls: ["./welcome.component.scss"],
})
export class WelcomeComponent implements OnInit {
  faUser = faUser;
  faShoppingCart = faShoppingCart;
  faBookOpen = faBookOpen;
  faGiftfaGift = faGift;
  faCarSide = faCarSide;
  faBiking = faBiking;
  faHeart = faHeart;
  open: boolean = false;
  SFBooks: Book[];

  /**
   *Categoriile existente in baza de date
   *
   */
  Categories: Category[];
  CategoriesTitles: string[] = [];
  displayColumn: Boolean = false;
  countOfBooks: number;

  constructor(
    public landingBookService: LandingBooksService,
    public loadingService: LoadingService,
    public router: Router
  ) {
    this.countOfBooks = 2;
  }

  ngOnInit(): void {
    this.GetCategories();
  }

  GetCategories() {
    this.loadingService.start();
    this.landingBookService
      .GetCategories()
      .subscribe((response: ApiResponse<Category[]>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.displayColumn = false;
            //Nu ai si tb scoasa din view coloana
          }
          this.Categories = response.body;



          this.Categories.forEach((element) => {
            this.CategoriesTitles.push(element.categoryTitle);
          });

          this.GetBooksForCategories();
        }
      });


  }
  GetBooksForCategories() {
    this.Categories.forEach((e) => {
      this.loadingService.start();
      this.landingBookService.GetBooksSF(e.categoryTitle, this.countOfBooks).subscribe(
        response => {
          if (response.status == ApiResponseType.SUCCESS){
            e.categoryBooks = response.body;
          }

          this.loadingService.stop();
        }
      )
    });
  }

  getUrlImageForBook(book: Book){
return "url('data:image/jpg;base64," + book.bookImage + "')";
  }





  AddToWhishlist(){


    var x = document.getElementsByClassName("add-to-whishlist-icon");
    $(".add-to-whishlist-icon").addClass("whislist-item");



  }

  viewDetails(bookId : number){

    this.router.navigate(['/home', 'book',  bookId]);
  }
}

/*

*/
