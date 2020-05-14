import { ThemeNames } from './../../../modules/theme-selector/theme-names.enum';
import { ThemeSelectorService } from './../../../modules/theme-selector/theme-selector.service';
import { Observable, concat } from 'rxjs';
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
import { CartBook } from 'src/app/Models/cart/CartBookModel';

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
  currentTheme: string;

  isLogged = false;
  private user: BookUser = null;
  private _token: String = null;
  cartBooks:CartBook[]=[];

  numberItems : number;
  constructor(
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService,
    private cartService: CartService,
    private themeSelectorService: ThemeSelectorService
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

    this.cartService.cartBooks.subscribe(books => {
      this.cartBooks = books;
    });

  }

  ngOnInit() {
    this.themeSelectorService.curentTheme.subscribe(e=>{
      this.currentTheme = e;
    });
   this.getNumberItems();

  }


  ngAfterContentChecked() {
    this.getNumberItems();

  }


  getNumberItems(){
  this.numberItems = this.cartService.getTotalQuantity();
  }


  GetHomeMainClass(){

    switch(this.currentTheme) {
      case ThemeNames.dark1:
      case ThemeNames.dark2:
        return "background-color-primary-900 color-primary-500-contrast";
      default:
        return "";

        // case ThemeNames.light1:
        //   case ThemeNames.light2:
        //     return "color-primary-500-contrast";
    }
  }



}
