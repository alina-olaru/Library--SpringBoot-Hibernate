import { Book } from 'src/app/Models/admin/BookModel';
import { CookieService } from 'ngx-cookie-service';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CartService {
  private _books: Book[] = [];
  cartBooks: BehaviorSubject<Book[]>;

  constructor(private cookieService: CookieService) {
    const cachedBooks = localStorage.getItem('cart-books');
    if (cachedBooks != null && cachedBooks !== '') {
      this._books = JSON.parse(cachedBooks) as Book[];
    } else {
      this._books = [];
    }
    this.cartBooks = new BehaviorSubject<Book[]>(this._books);
  }

  AddToCart(book: Book) {
    if (book != null) {
      this._books.push(book);
      localStorage.setItem('cart-books', JSON.stringify(this._books));
      this.cartBooks.next(this._books);
    }
  }

  RemoveFromCart(book: Book) {
    if (book != null) {
      const index = this._books.map((e) => e.bookId).indexOf(book.bookId);
      this._books.slice(index, 1);
      localStorage.setItem('cart-books', JSON.stringify(this._books));
      this.cartBooks.next(this._books);
    }
  }
}
