import { UserAddressAdd } from './../../../Models/user/UserAddressAdd';
import { ApiResponse } from './../../../Models/general/api-response';
import { BookOrder } from './../../../Models/cart/Order';
import { CartBook } from './../../../Models/cart/CartBookModel';
import { Book } from 'src/app/Models/admin/BookModel';
import { CookieService } from 'ngx-cookie-service';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private _books: CartBook[] = [];
  cartBooks: BehaviorSubject<CartBook[]>;

  constructor(private cookieService: CookieService,
    private httpClient: HttpClient,
    private globalVarService: GlobalVarService) {
    const cachedBooks = localStorage.getItem('cart-books');
    if (cachedBooks != null && cachedBooks !== '') {
      this._books = JSON.parse(cachedBooks) as CartBook[];
    } else {
      this._books = [];
    }
    this.cartBooks = new BehaviorSubject<CartBook[]>(this._books);
  }

  AddToCart(book: Book) {
    if (book != null) {
      const bookIdx = this._books
        .map((e) => e.book.bookId)
        .indexOf(book.bookId);

      if (bookIdx >= 0) {
        this._books[bookIdx].quantity++;
      } else {
        const newCartBook = {
          book,
          quantity: 1,
        } as CartBook;
        this._books.push(newCartBook);
      }
      localStorage.setItem('cart-books', JSON.stringify(this._books));
      this.cartBooks.next(this._books);
    }
  }

  UpdateBook(book: Book , newQuantity : number){
    if (book != null) {
      const bookIdx = this._books
        .map((e) => e.book.bookId)
        .indexOf(book.bookId);

      if (bookIdx >= 0) {
        this._books[bookIdx].quantity=newQuantity;
      }
      localStorage.setItem('cart-books', JSON.stringify(this._books));
      this.cartBooks.next(this._books);
    }
  }
  RemoveFromCart(book: Book) {
    if (book != null) {
      const index = this._books.map((e) => e.book.bookId).indexOf(book.bookId);
      if (index >= 0) {
        this._books.splice(index, 1);
        localStorage.setItem('cart-books', JSON.stringify(this._books));
        this.cartBooks.next(this._books);
      }
    }
  }

  RemoveAll(){
    this._books.forEach((e)=>{
      this.RemoveFromCart(e.book);
    })
  }
  getQuantity(idBook : number) : number{

    this._books.forEach((e) => {
      if(e.book.bookId==idBook){
        return e.quantity;
      }
    });
    return 1;
  }
  getBooks(){
    return this._books;
  }


  sendOrder(order : BookOrder){

    console.log(order);
    console.log(JSON.stringify(order));
    return this.httpClient.post<ApiResponse<BookOrder>>(this.globalVarService.globalUrl+"/api/order",
    order)
  }

  getTotalQuantity() : number{
    let total=0;
    this._books.forEach((e) =>{

      total=total+e.quantity;
    })
    return total;
  }

  getOrderForUser(userId : number){
    let params = new HttpParams()
    .set("userId",userId.toString());
    return this.httpClient.get<ApiResponse<BookOrder[]>>(this.globalVarService.globalUrl + "/api/order" , { params:params });
  }

}
