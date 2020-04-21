import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { LandingBooksService } from './LandingBooks.service';
import { Component, OnInit } from "@angular/core";
import { SliderComponent } from "../slider/slider.component";
import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
} from "@fortawesome/free-solid-svg-icons";
import { Book } from 'src/app/Models/admin/BookModel';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { FORMERR } from 'dns';
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
  open:boolean = false;
  SFBooks:Book[];

  constructor(
    public landingBookService : LandingBooksService,
    public loadingService:LoadingService) {



  }

  ngOnInit(): void {
    this.GetBooks();
  }

  GetSF(){


    this.loadingService.start();

    this.landingBookService
    .GetBooksSF()
    .subscribe((response : ApiResponse<Book[]>) => {
      this.loadingService.stop();

      // tslint:disable-next-line: triple-equals
      if(response && response.status==ApiResponseType.SUCCESS){

        if(response.body.length==0){
//Nu ai si tb scoasa din view coloana
        }
        this.SFBooks=response.body;
        console.log("body " + response.body+ " SFBOOKS"+ this.SFBooks);
        this.SFBooks.forEach(Book => { console.log(Book);


        });

      }
    });
  }

  GetBooks(){
    this.GetSF();
    console.log(this.SFBooks);
  }

}

/*

*/
