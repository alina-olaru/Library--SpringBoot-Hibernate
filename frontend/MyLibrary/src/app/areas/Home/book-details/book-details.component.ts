import { CartItemComponent } from './../cart-item/cart-item.component';
import { CartService } from './../cart/cart.service';
import { CookieService } from 'ngx-cookie-service';
import { PersonalBookService } from './personalBook.service';
import { WishlistService } from './wishlist.service';
import { BookUser } from 'src/app/Models/BookUser';
import { LoginService } from 'src/app/areas/login/login.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ApiResponse } from './../../../Models/general/api-response';
import { LandingBooksService } from './../welcome/LandingBooks.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Book } from 'src/app/Models/admin/BookModel';
import { ToastrService } from 'src/app/services/toastr.service';
import { switchMap, concat } from 'rxjs/operators';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { Wishlist } from 'src/app/Models/user/Wishlist';
import { PersonalBook } from 'src/app/Models/home/PersonalBook';
import { string } from '@amcharts/amcharts4/core';
import { Subscription } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';

@Component({
  selector: 'app-book-details',
  templateUrl: './book-details.component.html',
  styleUrls: ['./book-details.component.scss'],

})
export class BookDetailsComponent implements OnInit {

  wishlist: Wishlist;
  user: BookUser;
  alreadyInWishlist: Boolean = false;
  alreadyInMyBooks: Boolean = false;
  personalBook: PersonalBook;
  stringFinal = ' ';
  subscriptions: Subscription[] = [];
    //--------------------------popup---------------------------------------
    book: Book;
    bookId: number;
   quantity:number;

  //--------------------------cart items in cookies----------------------------------------
  booksAddedToCartArray : Book[] = [];
  //--------------------------responsive variables----------------------------------------

  width:number;
  heigth:number;
  isSmallScreen:Boolean;
  isLargeScreen:Boolean;
  isMediumeScreen:Boolean;
  x:string;
  y:string;


  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private auth: LoginService,
    private wishService: WishlistService,
    private router: Router,
    private personalBookService: PersonalBookService,
    private cartService: CartService,
    public dialog: MatDialog,
    public breakpointObserver:BreakpointObserver
  ) {


      this.isSmallScreen = breakpointObserver.isMatched('(max-width: 599px)');
      this.isLargeScreen = breakpointObserver.isMatched('(min-width: 1000px)');
      this.isMediumeScreen = breakpointObserver.isMatched('(min-width: 600px)');
  }

  ngOnInit(): void {

    if(this.isLargeScreen==true){

      this.width=1100;
      this.heigth=800;
    }

    if(this.isSmallScreen==true){

      this.width=300;
      this.heigth=400;
    }
    if((this.isMediumeScreen==true)&&(this.isLargeScreen==false)){

      this.width=350;
      this.heigth=440;
    }



    this.bookId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.GetBookById(this.bookId);
    this.user = this.auth.getUser();
    this.checkIfWish();
    this.checkIPers();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((e) => {
      e.unsubscribe();
    });
  }




  // ---------------------------------BOOK-----------------------------------------------------------




  GetBookById(id: number) {
    this.bookId = id;
    console.info(id);

    const getBooks = this.landingBooksService.GetDetails(id).subscribe(
      (response: ApiResponse<Book>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.book = response.body;

          if (this.book.bookImage) {
            this.book.bookImageSrc = this.domSanitizer.bypassSecurityTrustResourceUrl(
              'data:image/jpg;base64,' + this.book.bookImage
            );
          }
          console.log(this.book);
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: 'A aparut o eroare',
          });
        }
      },
      (error) => {
        this.toastr.Toast.fire({
          icon: 'error',
          title: 'A aparut o eroare',
        });
      }
    );

    this.subscriptions.push(getBooks);
  }

  getUrlImageForBook() {
    {
      return 'url(\'data:image/jpg;base64,' + this.book.bookImage + '\')';
    }
  }




  // ---------------------------------WISHLIST-----------------------------------------------------------





  AddToWhishlist() {
    console.log('here');
    this.wishlist = new Wishlist();
    this.wishlist.userwishlist = this.user;
    this.wishlist.bookwishlist = this.book;
    const addtowish = this.wishService
      .AddWishlist(this.wishlist)
      .subscribe((response: ApiResponse<Wishlist>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Swal.fire({
            icon: 'success',
            title: 'Cartea a fost adaugata cu succes in wishlist',
            allowOutsideClick: false,
            allowEscapeKey: false,
          }).then((result) => {
            if (result.value) {
              this.router.navigate(['/cont/wishlist']);
            }
          });
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: response.message,
          });
        }
      });

    console.log('here');
    this.subscriptions.push(addtowish);
  }

  checkIfWish() {
    console.log('here');
    const checkIfisWish = this.wishService
      .checkIfWish(this.user.userId, this.bookId)
      .subscribe((response: ApiResponse<Boolean>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          response.body == true
            ? (this.alreadyInWishlist = false)
            : (this.alreadyInWishlist = true);
          console.log('dupa request: ' + this.alreadyInWishlist);
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: response.message,
          });
        }
      });

    this.subscriptions.push(checkIfisWish);
    console.log('here');
  }

  DeleteFromWishlist() {
    // TODO DE FACUT STERGEREA + BACKEND

    const del = this.wishService
      .DeleteWishlist(this.user.userId, this.bookId)
      .subscribe((response: ApiResponse<boolean>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Swal.fire({
            icon: 'success',
            title: 'Cartea a fost stersa cu succes din wishlist',
            allowOutsideClick: false,
            allowEscapeKey: false,
          }).then((result) => {
            this.alreadyInWishlist = !this.alreadyInWishlist;
          });
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: response.message,
          });
        }
      });
  }



  // ---------------------------------PERSONALBOOK-----------------------------------------------------------





  checkIPers() {
    const checkIPerss = this.personalBookService
      .checkIfBookIsAlreadyPersonal(this.user.userId, this.bookId)
      .subscribe((response: ApiResponse<Boolean>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          response.body == true
            ? (this.alreadyInMyBooks = false)
            : (this.alreadyInMyBooks = true);
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: response.message,
          });
        }
      });

    this.subscriptions.push(checkIPerss);
  }

  AddToPersonal() {
    console.log('bookid ' + this.bookId);
    this.personalBook = new PersonalBook();
    this.personalBook.book = this.book;
    this.personalBook.user = this.user;
    this.personalBook.persBTitle = this.book.bookTitle;

    // this.personalBook.persBAuthor=this.book.bookAuthor[1].authorId.firstName
    this.personalBook.book.bookAuthor.forEach((element) => {
      this.stringFinal =
        this.stringFinal +
        element.authorId.firstName +
        ' ' +
        element.authorId.lastName +
        ' ';
    });

    this.personalBook.persBAuthor = this.stringFinal;
    this.personalBook.bookImage = this.book.bookImage;
    this.personalBook.bookImageSrc = this.book.bookImageSrc;
    //  console.log("datele care se trimit " + this.personalBook.book);
    //  console.log("datele care se trimit " + this.personalBook.bookImage);
    //  console.log("datele care se trimit " + this.personalBook.bookImageSrc);
    //  console.log("datele care se trimit " + this.personalBook.persBAuthor);
    //  console.log("datele care se trimit " + this.personalBook.persBTitle);

    const addpersb = this.personalBookService
      .addBook(this.personalBook)
      .subscribe((response: ApiResponse<PersonalBook>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Swal.fire({
            icon: 'success',
            title: 'Cartea a fost adaugata in biblioteca dvs personala!',
            allowOutsideClick: false,
            allowEscapeKey: false,
          }).then((result) => {
            if (result.value) {
              this.router.navigate(['/cont/bibiotecapersonala']);
            }
          });
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: response.message,
          });
        }
      });

    this.subscriptions.push(addpersb);
  }

  DeleteFromPersonal() {
    // TODO DE FACUT STERGEREA + BACKEND

    const del = this.personalBookService
      .deletePers(this.user.userId, this.bookId)
      .subscribe((response: ApiResponse<boolean>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Swal.fire({
            icon: 'success',
            title: 'Cartea a fost stersa cu succes din biblioteca personala',
            allowOutsideClick: false,
            allowEscapeKey: false,
          }).then((result) => {
            if (result.value) {
              // this.router.navigate(['/cont/wishlist']);
              // todo refresh pagina or smth
            }
          });
        } else {
          this.toastr.Toast.fire({
            icon: 'error',
            title: response.message,
          });
        }
      });
    location.reload();
  }





  // --------------------------------------------------cart----------------------------------------------------------




  AddtoCart(){

    this.x=this.width+"px";
    this.y=this.heigth+"px";

    this.quantity =this.cartService.getQuantity(this.bookId);
    this.cartService.AddToCart(this.book);
    let dialogRef = this.dialog.open(CartItemComponent, {
      width: this.x,
      height: this.y,
      data : {
        model : this.book,
        quantity :this.quantity
      }
    });

  }

}
