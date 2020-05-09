import { CartBook } from './../../../Models/cart/CartBookModel';
import { CartService } from './cart.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  pairBooks : CartBook[]=[];
  options : number[] =[];
  quantity : number;
  constructor(public cartService:CartService) { }

  ngOnInit(): void {
    this.getBooks();
  }



  getBooks(){
    this.pairBooks = this.cartService.getBooks();
    this.pairBooks.forEach((e) =>{
     this.quantity = e.quantity;
     e.book.bookPrice=e.book.bookPrice*this.quantity;
     this.options=[];
     for (let index = -1; index < 4; index++) {
       this.options.push(index+this.quantity);

     }

    });
    this.pairBooks.forEach((e) =>{
    this.calculatePrice(e.quantity,e.book.bookId);
    });

  }
    calculatePrice(quantity : number,id:Number){
      console.log(quantity);
      this.pairBooks.forEach((e) =>{
        if(e.book.bookId==id){
        let last_price=e.book.bookPrice/e.quantity;
        e.book.bookPrice=e.book.bookPrice +(quantity -e.quantity) * last_price;
        e.quantity=quantity;
        }
      });
    }



}
