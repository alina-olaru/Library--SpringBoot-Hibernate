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
  faHeart=faHeart;
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
    public loadingService: LoadingService
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
          console.log(this.Categories + "OBIECTE SUS");

          this.Categories.forEach((element) => {
            this.CategoriesTitles.push(element.categoryTitle);
          });

          this.GetBooksForCategories();
        }
      });

    console.log(this.Categories + "OBIECTE JOS");
  }
  GetBooksForCategories() {
    this.Categories.forEach((e) => {
      this.loadingService.start();
      this.landingBookService.GetBooksSF(e.categoryTitle, this.countOfBooks).subscribe(
        response => {
          if(response.status == ApiResponseType.SUCCESS){
            e.categoryBooks = response.body;
          }

          this.loadingService.stop();
        }
      )
    });
  }

  getUrlImageForBook(book: Book){
return "url('data:image/jpg;base64,"+book.bookImage+"')";
  }

  // GetSF() {
  //   this.loadingService.start();

  //   this.landingBookService
  //     .GetBooksSF()
  //     .subscribe((response: ApiResponse<Book[]>) => {
  //       this.loadingService.stop();

  //       // tslint:disable-next-line: triple-equals
  //       if (response && response.status == ApiResponseType.SUCCESS) {
  //         if (response.body.length == 0) {
  //           //Nu ai si tb scoasa din view coloana
  //         }
  //         this.SFBooks = response.body;
  //         // console.log("body " + response.body+ " SFBOOKS"+ this.SFBooks);
  //         this.SFBooks.forEach((Book) => {
  //           console.log(Book);
  //         });
  //       }
  //     });
  // }

  // GetBooks() {
  //   this.GetSF();
  //   // console.log(this.SFBooks);
  // }

  t() {
    console.log("OBIECTELE: " + this.Categories.length);
    this.Categories.forEach((element) => {
      console.log(element);
    });
    console.log("TITLURILE: " + this.CategoriesTitles.length);
    this.CategoriesTitles.forEach((element) => {
      console.log(element);
    });
  }


  AddToWhishlist(){

    console.log("click");
    var x = document.getElementsByClassName("add-to-whishlist-icon");
    $(".add-to-whishlist-icon").addClass("whislist-item");

1



  }
}

/*

*/
