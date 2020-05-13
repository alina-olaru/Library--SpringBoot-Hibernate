import { Router } from '@angular/router';
import { LoadingService } from './../../../modules/loading-spinner/loading.service';
import { AddressServiceService } from './../../User/addresses-book/AddressService.service';
import { Address } from './../../../Models/Address';
import { LoginService } from './../../login/login.service';
import { BookUser } from './../../../Models/BookUser';
import { CartBook } from './../../../Models/cart/CartBookModel';
import { CartService } from './cart.service';
import { Component, OnInit } from '@angular/core';
import { ThemeSelectorService } from 'src/app/modules/theme-selector/theme-selector.service';
import { ThemeNames } from 'src/app/modules/theme-selector/theme-names.enum';
import { Book } from 'src/app/Models/admin/BookModel';
import { ActivatedRoute } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../welcome/LandingBooks.service';
import { DomSanitizer } from '@angular/platform-browser';
import { BookOrder } from 'src/app/Models/cart/Order';
import { orderItem } from 'src/app/Models/cart/orderItem';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {
  pairBooks : CartBook[]=[];
  options : number[] =[];
  isLinear = false;

  quantity : number;
  currentTheme: string;

  //-------------------order details-------------------------
  user : BookUser;
  address : Address;
  order : BookOrder;
  constructor(public cartService:CartService,
    private themeSelectorService: ThemeSelectorService,
    private auth : LoginService,
    private addressService  : AddressServiceService,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private loadingService : LoadingService,
    private router : Router
    ) { }

  ngOnInit(): void {
    this.themeSelectorService.curentTheme.subscribe(e=>{
      this.currentTheme = e;
    });
    this.getBooks();
    this.user=this.auth.getUser();
    this.order=new BookOrder();
    this.order.ordersUser=this.user;
    this.getAddress();
  }

  GetHomeMainClass(){
    switch(this.currentTheme) {
      case ThemeNames.dark1:
      case ThemeNames.dark2:
        return "background-color-primary-900";
      default:
        return "";
    }
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
          this.cartService.UpdateBook(e.book,quantity);
        let last_price=e.book.bookPrice/e.quantity;
        e.book.bookPrice=e.book.bookPrice +(quantity -e.quantity) * last_price;
        e.quantity=quantity;
        }
      });
    }

    deleteItemCart(book : Book){
      this.cartService.RemoveFromCart(book);



    }

    deleteAddress(address:Address){
//todo de completat asta
    }

    getAddress(){
      if((this.user==null)||(this.user==undefined)){
        this.toastr.Swal.fire({
          title: "Trebuie sa te loghezi pentru a comanda!",
          html: `Ai <b>${this.pairBooks.length}</b> -  <b>produse in cos`,
          // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
          icon: "warning",
          showCancelButton: true,
          confirmButtonColor: "#3085d6",
          cancelButtonColor: "#d33",
          confirmButtonText: "Da",
          cancelButtonText: "Nu"
        }).then(result => {
          if (result.value) {
            this.router.navigate(['/login']);
          }
    })
}
}

chooseAddress(add : Address){
  console.log(add.streetName + "parametru");
  this.address = new Address();
  this.address=add;
  this.order=new BookOrder();
  this.order.idLocatie=add.addressId;
  console.log("order" + this.order.idLocatie);
  //todo ceva disable pe cealalta
}
}

