import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-book-via-ocr',
  templateUrl: './add-book-via-ocr.component.html',
  styleUrls: ['./add-book-via-ocr.component.scss']
})
export class AddBookViaOCRComponent implements OnInit {

  state:number;


  constructor() {
    this.state=0;

  }

  ngOnInit(): void {
  }


  openCamera(){
    this.state=1;
    console.log("Camera will open");
  }
}