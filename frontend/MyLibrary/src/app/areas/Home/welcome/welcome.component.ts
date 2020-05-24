import { AbonarepopComponent } from './../abonarepop/abonarepop.component';
import { Router } from "@angular/router";
import { getElement } from "@amcharts/amcharts4/core";
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
  faHeart,
} from "@fortawesome/free-solid-svg-icons";
import { Book } from "src/app/Models/admin/BookModel";
import { ApiResponse } from "src/app/Models/general/api-response";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { FORMERR } from "dns";
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';
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
  open = false;
  SFBooks: Book[];
    //--------------------------responsive variables----------------------------------------

    width: number;
    heigth: number;
    isSmallScreen: Boolean;
    isLargeScreen: Boolean;
    isMediumeScreen: Boolean;
    isXL:boolean;
    x: string;
    y: string;


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
    public router: Router,
    public dialog: MatDialog,
    public breakpointObserver: BreakpointObserver
  ) {
    this.countOfBooks = 2;
    this.isSmallScreen = breakpointObserver.isMatched("(max-width: 599px)");
    this.isLargeScreen = breakpointObserver.isMatched("(min-width: 1000px)");
    this.isXL = breakpointObserver.isMatched("(min-width: 1300px)");
    this.isMediumeScreen = breakpointObserver.isMatched("(min-width: 600px)");
  }

  ngOnInit(): void {
    // if(this.isXL==true  && this.isMediumeScreen == false && this.isSmallScreen == false  && this.isLargeScreen==false ){
    // this.width=1700;
    // this.heigth= 1100;
    // }
    // if (this.isLargeScreen == true && this.isMediumeScreen == false && this.isSmallScreen == false  && this.isXL == false) {
    //   this.width = 1100;
    //   this.heigth = 800;
    // }

    // if (this.isSmallScreen == true && this.isLargeScreen == false && this.isXL == false && this.isMediumeScreen == false) {
    //   this.width = 300;
    //   this.heigth = 400;
    // }
    // if (this.isMediumeScreen == true && this.isLargeScreen == false && this.isXL == false  && this.isSmallScreen == false) {
    //   this.width = 350;
    //   this.heigth = 440;
    // }

    if (this.isLargeScreen == true) {
      this.width = 1100;
      this.heigth = 800;
    }

    if (this.isSmallScreen == true) {
      this.width = 300;
      this.heigth = 400;
    }
    if (this.isMediumeScreen == true && this.isLargeScreen == false) {
      this.width = 350;
      this.heigth = 440;
    }

    this.GetCategories();
  }

  ngAfterContentInit(){
    setTimeout (() => {
      this.openDialog();
   }, 10000);

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
            // Nu ai si tb scoasa din view coloana
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
      this.landingBookService
        .GetBooksSF(e.categoryTitle, this.countOfBooks)
        .subscribe((response) => {
          if (response.status == ApiResponseType.SUCCESS) {
            e.categoryBooks = response.body;
          }

          this.loadingService.stop();
        });
    });
  }

  getUrlImageForBook(book: Book) {
    if(book.bookImageDb!=null){
    return "url('data:image/jpg;base64," + book.bookImageDb + "')";
    }
    else{
      return "url('../../../../assets/no.png')";
    }

  }

  AddToWhishlist() {
    let x = document.getElementsByClassName("add-to-whishlist-icon");
    $(".add-to-whishlist-icon").addClass("whislist-item");
  }

  viewDetails(bookId: number) {
    this.router.navigate(["/home", "book", bookId]);
  }


  RedirectToCategory(categoryTitle: string) {
    this.router.navigate(["/home", "category", categoryTitle]);
  }


  openDialog(){
    this.x=this.width+"px";
    this.y=this.heigth+"px";

    this.dialog.open(AbonarepopComponent, {
      width: this.x,
      height: this.y
    });
  }
}

/*

*/
