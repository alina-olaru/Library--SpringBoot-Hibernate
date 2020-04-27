import { AddBookViaOCRComponent } from './../add-book-via-ocr/add-book-via-ocr.component';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';

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


  constructor(
    public dialog: MatDialog,
    public breakpointObserver:BreakpointObserver
  ) {
    this.UrlOCR="https://api.ocr.space/parse/image";
    this.isSmallScreen = breakpointObserver.isMatched('(max-width: 599px)');
    this.isLargeScreen = breakpointObserver.isMatched('(min-width: 1000px)');
    this.isMediumeScreen = breakpointObserver.isMatched('(min-width: 600px)');


   }

  ngOnInit(): void {

    if(this.isLargeScreen==true){

      this.width=800;
      this.heigth=600;
    }

    if(this.isSmallScreen==true){

      this.width=300;
      this.heigth=400;
    }
    if((this.isMediumeScreen==true)&&(this.isLargeScreen==false)){

      this.width=350;
      this.heigth=440;
    }
  }

  AddBook(){
    console.log(this.heigth +  "  "+ this.width);
    this.x=this.width+"px";
    this.y=this.heigth+"px";
    console.log(this.x);

    console.log(this.y);
    const dialogRef = this.dialog.open(AddBookViaOCRComponent, {
      width: this.x,
      height: this.y
      // data: {
      //   type: 'edit',
      //   model: Object.assign({}, item)
      // }
    });


  }
}
