import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-books',
  templateUrl: './my-books.component.html',
  styleUrls: ['./my-books.component.scss']
})
export class MyBooksComponent implements OnInit {

  UrlOCR:string;
  constructor() {
    this.UrlOCR="https://api.ocr.space/parse/image";
   }

  ngOnInit(): void {
  }

  AddBook(){

  }
}
