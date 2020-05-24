import { PublishersService } from './../../admin/pages/publishers/publishers.service';
import { AuthorsService } from './../../admin/pages/authors/authors.service';
import { Author } from 'src/app/Models/admin/AuthorModel';
import { ThemeNames } from "./../../../modules/theme-selector/theme-names.enum";
import { ThemeSelectorService } from "./../../../modules/theme-selector/theme-selector.service";
import { Observable, concat } from "rxjs";
import { CartService } from "./../cart/cart.service";
import { Component, OnInit, Renderer2 } from "@angular/core";
import {
  faUser,
  faShoppingCart,
  faBookOpen,
  faGift,
  faCarSide,
  faBiking,
} from "@fortawesome/free-solid-svg-icons";
import { GlobalVarService } from "src/app/services/global-var.service";
import { CookieService } from "ngx-cookie-service";
import { Router, ActivatedRoute } from "@angular/router";
import { ToastrService } from "src/app/services/toastr.service";
import { BookUser } from "src/app/Models/BookUser";
import { Book } from "src/app/Models/admin/BookModel";
import { CartBook } from "src/app/Models/cart/CartBookModel";
import { LandingBooksService } from '../welcome/LandingBooks.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Category } from 'src/app/Models/admin/CategoryModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { Publisher } from 'src/app/Models/admin/PublisherModel';

@Component({
  selector: "app-home-layout",
  templateUrl: "./layout.component.html",
  styleUrls: ["./layout.component.scss"],
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
  cartBooks: CartBook[] = [];
  searchKey: string = null;

  numberItems: number;
  categories : Category[]=[];
  authors : Author[]=[];
  publishers : Publisher[]=[];

  //----------------------------------------------menu ->toggle at hover ------------------------------------------------------------------------------
  modulesList: Array<any>;
  enteredButton = false;
  isMatMenuOpen = false;
  isMatMenu2Open = false;
  prevButtonTrigger;
  constructor(
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService,
    private cartService: CartService,
    private themeSelectorService: ThemeSelectorService,
    private ren: Renderer2,
    private route: ActivatedRoute,
    public landingBookService: LandingBooksService,
    public loadingService: LoadingService,
    public authorsService:AuthorsService,
    private publishersService:PublishersService
  ) {


    const cachedUser = this.cookieService.get("auth-user-info");
    if (cachedUser != null && cachedUser != "") {
      this.user = JSON.parse(cachedUser) as BookUser;
      console.log(this.user);
      if (this.user) {
        this.isLogged = true;
      }
    }

    const cachedToken = this.cookieService.get("auth-token");
    if (cachedToken != null && cachedToken != "") {
      this._token = JSON.parse(cachedToken);
    }

    this.cartService.cartBooks.subscribe((books) => {
      this.cartBooks = books;
    });
  }

  ngOnInit() {
    this.route.firstChild?.firstChild?.params.subscribe(params => {
      let query = params['query'];
      if(query){
        this.searchKey = query;
      }
    });
    this.themeSelectorService.curentTheme.subscribe((e) => {
      this.currentTheme = e;
    });
    this.getNumberItems();
    this.GetCategories();
    this.getAuthors();
    this.getPublishers();
  }

  ngAfterContentChecked() {
    this.getNumberItems();
  }

  getNumberItems() {
    this.numberItems = this.cartService.getTotalQuantity();
  }

  GetHomeMainClass() {
    switch (this.currentTheme) {
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

  search() {
    console.log(this.searchKey);
    this.router.navigate(["/home", "search", this.searchKey]);
  }
  GetCategories() {
    this.loadingService.start();
    this.landingBookService
      .GetCategories()
      .subscribe((response: ApiResponse<Category[]>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {

          this.categories = response.body;

        }
      });
  }

  getAuthors() {
    const autSubscriber = this.authorsService
      .GetAuthors()
      .subscribe((response) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.authors = response.body;
        }
      });
    //..this.subscriptions.push(autSubscriber);
  }

  getPublishers() {
    const pubSubscriber = this.publishersService
      .GetPublishers()
      .subscribe((response) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.publishers = response.body;
        }
      });
    //  this.subscriptions.push(pubSubscriber);
  }

  sendit(varr: any) {
    console.log(varr);
  }

  //----------------------------------------------menu ->toggle at hover ------------------------------------------------------------------------------

  allbooks() {
    this.router.navigate(["/home", "search", "empty"]);
  }



  toggleMenu() {
    if (this.open == false) {
      this.open = true;
    } else {
      this.open = false;
    }
    console.log(this.open);
  }

  menuenter() {
    this.isMatMenuOpen = true;
    if (this.isMatMenu2Open) {
      this.isMatMenu2Open = false;
    }
  }

  menuLeave(trigger, button) {
    setTimeout(() => {
      if (!this.isMatMenu2Open && !this.enteredButton) {
        this.isMatMenuOpen = false;
        trigger.closeMenu();
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-focused"
        );
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-program-focused"
        );
      } else {
        this.isMatMenuOpen = false;
      }
    }, 80);
  }

  menu2enter() {
    this.isMatMenu2Open = true;
  }

  menu2Leave(trigger1, trigger2, button) {
    setTimeout(() => {
      if (this.isMatMenu2Open) {
        trigger1.closeMenu();
        this.isMatMenuOpen = false;
        this.isMatMenu2Open = false;
        this.enteredButton = false;
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-focused"
        );
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-program-focused"
        );
      } else {
        this.isMatMenu2Open = false;
        trigger2.closeMenu();
      }
    }, 100);
  }

  buttonEnter(trigger) {
    setTimeout(() => {
      if (this.prevButtonTrigger && this.prevButtonTrigger != trigger) {
        this.prevButtonTrigger.closeMenu();
        this.prevButtonTrigger = trigger;
        this.isMatMenuOpen = false;
        this.isMatMenu2Open = false;
        trigger.openMenu();
        this.ren.removeClass(
          trigger.menu.items.first["_elementRef"].nativeElement,
          "cdk-focused"
        );
        this.ren.removeClass(
          trigger.menu.items.first["_elementRef"].nativeElement,
          "cdk-program-focused"
        );
      } else if (!this.isMatMenuOpen) {
        this.enteredButton = true;
        this.prevButtonTrigger = trigger;
        trigger.openMenu();
        this.ren.removeClass(
          trigger.menu.items.first["_elementRef"].nativeElement,
          "cdk-focused"
        );
        this.ren.removeClass(
          trigger.menu.items.first["_elementRef"].nativeElement,
          "cdk-program-focused"
        );
      } else {
        this.enteredButton = true;
        this.prevButtonTrigger = trigger;
      }
    });
  }

  buttonLeave(trigger, button) {
    setTimeout(() => {
      if (this.enteredButton && !this.isMatMenuOpen) {
        trigger.closeMenu();
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-focused"
        );
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-program-focused"
        );
      }
      if (!this.isMatMenuOpen) {
        trigger.closeMenu();
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-focused"
        );
        this.ren.removeClass(
          button["_elementRef"].nativeElement,
          "cdk-program-focused"
        );
      } else {
        this.enteredButton = false;
      }
    }, 100);
  }

  RedirectToCategory(categoryTitle: string) {
    this.router.navigate(["/home", "category", categoryTitle]);
  }


  RedirectToAuthors(authorId: string) {
    this.router.navigate(["/home", "search", authorId]);
  }

  RedirectToPublisher(publisherTitle: string){
    this.router.navigate(["/home", "search", publisherTitle]);
  }
}
