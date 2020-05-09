import { CartBook } from './../../../Models/cart/CartBookModel';
import { Book } from 'src/app/Models/admin/BookModel';
import { CookieService } from 'ngx-cookie-service';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private _books: CartBook[] = [];
  cartBooks: BehaviorSubject<CartBook[]>;

  constructor(private cookieService: CookieService) {
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

  RemoveFromCart(book: Book) {
    if (book != null) {
      const index = this._books.map((e) => e.book.bookId).indexOf(book.bookId);
      if (index >= 0) {
        this._books.slice(index, 1);
        localStorage.setItem('cart-books', JSON.stringify(this._books));
        this.cartBooks.next(this._books);
      }
    }
  }


  getBooks(){
    return this._books;
  }

}
