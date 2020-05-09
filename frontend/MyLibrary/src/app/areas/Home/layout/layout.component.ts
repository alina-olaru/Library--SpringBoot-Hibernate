import { Observable } from 'rxjs';
import { CartService } from './../cart/cart.service';
import { Component, OnInit } from '@angular/core';
import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
} from '@fortawesome/free-solid-svg-icons';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { BookUser } from 'src/app/Models/BookUser';
import { Book } from 'src/app/Models/admin/BookModel';

@Component({
  selector: 'app-home-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss'],
})
export class LayoutComponent implements OnInit {
  faUser = faUser;
  faShoppingCart = faShoppingCart;
  faBookOpen = faBookOpen;
  faGiftfaGift = faGift;
  faCarSide = faCarSide;
  faBiking = faBiking;
  open = false;

  isLogged = false;
  private user: BookUser = null;
  private _token: String = null;
  cartBooks$: Observable<Book[]>;

  constructor(
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService,
    private cartService: CartService
  ) {
    const cachedUser = this.cookieService.get('auth-user-info');
    if (cachedUser != null && cachedUser != '') {
      this.user = JSON.parse(cachedUser) as BookUser;
      console.log(this.user);
      if (this.user) {
        this.isLogged = true;
      }
    }

    const cachedToken = this.cookieService.get('auth-token');
    if (cachedToken != null && cachedToken != '') {
      this._token = JSON.parse(cachedToken);
    }

    this.cartBooks$ = this.cartService.cartBooks;
  }

  ngOnInit() {}

  test() {
    if (this.open == false) {
      this.open = true;
    } else {
      this.open = false;
    }
  }
}
