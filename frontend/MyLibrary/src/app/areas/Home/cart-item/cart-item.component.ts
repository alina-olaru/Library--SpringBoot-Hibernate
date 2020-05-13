import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog } from '@angular/material/dialog';
import { Book } from 'src/app/Models/admin/BookModel';
import { timeout } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../welcome/LandingBooks.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoginService } from '../../login/login.service';
import { WishlistService } from '../book-details/wishlist.service';
import { PersonalBookService } from '../book-details/personalBook.service';
import { CartService } from '../cart/cart.service';
import { BreakpointObserver } from '@angular/cdk/layout';


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
    @Inject(MAT_DIALOG_DATA) public data: Data,
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

  redirectToCart(){

    this.closePop();
    this.dialog.afterAllClosed.subscribe((response) =>{
      this.router.navigate(['/home/cart']);
    }
    )

  }

  closePop(){
    this.dialog.closeAll();
  }
}
