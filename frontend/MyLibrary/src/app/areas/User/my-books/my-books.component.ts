import { NgZone } from '@angular/core';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { LoginService } from 'src/app/areas/login/login.service';
import { PersonalBookService } from './../../Home/book-details/personalBook.service';
import { MyLibraryService } from './my-library.service';
import { AddBookViaOCRComponent } from './../add-book-via-ocr/add-book-via-ocr.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import { PersonalBook } from 'src/app/Models/home/PersonalBook';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../../Home/welcome/LandingBooks.service';
import { Book } from 'src/app/Models/admin/BookModel';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.scss']
})
export class MyBooksComponent implements OnInit {

  UrlOCR:string;
  width:number;
  heigth:number;
  isSmallScreen:Boolean;
  isLargeScreen:Boolean;
  isMediumeScreen:Boolean;
  x:string;
  y:string;
  booksbyLibrary:Book[]=[];
  booksbyUser : Book[] = [];
  subscriptions: Subscription[] = [];


  constructor(
    public dialog: MatDialog,
    public breakpointObserver:BreakpointObserver,
   public libraryService:MyLibraryService,
   public personalBookS:PersonalBookService,
   public auth:LoginService,
   public router:Router,
   private toastr: ToastrService,
   private landingBooksService: LandingBooksService,
   private zone : NgZone
  ) {
    this.UrlOCR="https://api.ocr.space/parse/image";
    this.isSmallScreen = breakpointObserver.isMatched('(max-width: 599px)');
    this.isLargeScreen = breakpointObserver.isMatched('(min-width: 1000px)');
    this.isMediumeScreen = breakpointObserver.isMatched('(min-width: 600px)');


   }

ngOnInit(): void {

    if(this.isLargeScreen==true){

      this.width=1100;
      this.heigth=800;
    }

    if(this.isSmallScreen==true){

      this.width=300;
      this.heigth=400;
    }
    if((this.isMediumeScreen==true)&&(this.isLargeScreen==false)){

      this.width=350;
      this.heigth=440;
    }

    // this.GetText();
    /**
 *
 *Aduc cartile pe rand,prima data le aduc pe cele adaugate din interfata(existente in librarie)
 *
 */

    this.getMyBooksFromLibrary();
    this.getBooksByUser();
  }




ngOnDestroy(): void {
  this.subscriptions.forEach((e) => {
    e.unsubscribe();
  });
}


  AddBook(){
    console.log(this.heigth +  "  "+ this.width);
    this.x=this.width+"px";
    this.y=this.heigth+"px";
    console.log(this.x);

    console.log(this.y);
    this.zone.run(() =>{
    const dialogRef = this.dialog.open(AddBookViaOCRComponent, {
      width: this.x,
      height: this.y
      // data: {
      //   type: 'edit',
      //   model: Object.assign({}, item)
      // }
    });
  })
  }

    // GetText(){
    //   this.libraryService.GetText().subscribe((response) => {
    //     console.log(response);
    //   });

    // }

    getMyBooksFromLibrary(){
    //TODO ADD LOADING PESTE TOT
   const getBooks=  this.personalBookS.getBooks(1,this.auth.getUser().userId).subscribe((response:ApiResponse<PersonalBook[]>) => {


    if (response && response.status == ApiResponseType.SUCCESS) {

      if(response.body.length>0){

    // response.body.forEach(element => {
    //   this.booksbyLibrary.push(element.book);
    // });

    this.booksbyLibrary=response.body.map(e => e.book);


  }

else{
  console.log("nu exista carti in biblioteca pers");
  //TODO DE PUS  O IMG SAU CEVA IN HTML
}
}
         else {
          {//TODO ADD POPUPUL ASTA GENERAL PESTE TOT
            this.toastr.Swal.fire({
              icon: "error",
              title: "Au aparut probleme cu serverul nostru.Contacteaza-ne pentru detalii!",
              allowOutsideClick: false,
              allowEscapeKey: false,
            }).then((result) => {
              if (result.value) {
                this.router.navigate(["/home/welcome"]);
              }
            });
          }
        }




      })

      this.subscriptions.push(getBooks);
    }

    getBooksByUser(){
      //TODO ADD LOADING PESTE TOT
     const getBooks=  this.personalBookS.getBooks(2,this.auth.getUser().userId).subscribe((response:ApiResponse<PersonalBook[]>) => {


      if (response && response.status == ApiResponseType.SUCCESS) {

        if(response.body.length>0){

      // response.body.forEach(element => {
      //   this.booksbyLibrary.push(element.book);
      // });

      this.booksbyUser=response.body.map(e => e.book);


    }

  else{
    console.log("nu exista carti in biblioteca pers");
    //TODO DE PUS  O IMG SAU CEVA IN HTML
  }
  }
           else {
            {//TODO ADD POPUPUL ASTA GENERAL PESTE TOT
              this.toastr.Swal.fire({
                icon: "error",
                title: "Au aparut probleme cu serverul nostru.Contacteaza-ne pentru detalii!",
                allowOutsideClick: false,
                allowEscapeKey: false,
              }).then((result) => {
                if (result.value) {
                  this.router.navigate(["/home/welcome"]);
                }
              });
            }
          }




        })

        this.subscriptions.push(getBooks);
      }

  getUrlImageForBook(book: Book){
    if (book.bookImageDb != null) {
      return "url('data:image/jpg;base64," + book.bookImageDb + "')";
    } else {
      return "url('../../../../assets/no.png')";
    }      }

      viewDetails(bookId : number){

        this.router.navigate(['/home', 'book',  bookId]);
      }
    }




