import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Book } from 'src/app/Models/admin/BookModel';
import { timeout } from 'rxjs/operators';


interface Data {
  model: Book;
  quantity : number;
}

@Component({
  selector: 'app-cart-item',
  templateUrl: './cart-item.component.html',
  styleUrls: ['./cart-item.component.scss']
})
export class CartItemComponent implements OnInit {
  subtotal:number;
  book : Book=null;
  quantity:number=1;
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: Data
    ) {
      if (this.data.model == null || this.data.model == undefined) {
        this.data.model = new Book();
        this.data.quantity=1;
      }
      else{
        this.book=this.data.model;
        this.quantity=this.data.quantity;
        this.subtotal=this.quantity*this.book.bookPrice;
      }
      console.log(this.quantity);
      console.log(this.book);
    }


  ngOnInit(): void {
    console.log(this.quantity);
    console.log(this.book);


  }

}
