import { PricePipe } from "./../../../modules/pipes/price.pipe";
import { error } from "protractor";
import { ApiResponse } from "./../../../Models/general/api-response";
import { Router } from "@angular/router";
import { LoadingService } from "./../../../modules/loading-spinner/loading.service";
import { AddressServiceService } from "./../../User/addresses-book/AddressService.service";
import { Address } from "./../../../Models/Address";
import { LoginService } from "./../../login/login.service";
import { BookUser } from "./../../../Models/BookUser";
import { CartBook } from "./../../../Models/cart/CartBookModel";
import { CartService } from "./cart.service";
import { Component, OnInit } from "@angular/core";
import { ThemeSelectorService } from "src/app/modules/theme-selector/theme-selector.service";
import { ThemeNames } from "src/app/modules/theme-selector/theme-names.enum";
import { Book } from "src/app/Models/admin/BookModel";
import { ActivatedRoute } from "@angular/router";
import { ToastrService } from "src/app/services/toastr.service";
import { LandingBooksService } from "../welcome/LandingBooks.service";
import { DomSanitizer } from "@angular/platform-browser";
import { BookOrder } from "src/app/Models/cart/Order";
import { orderItem } from "src/app/Models/cart/orderItem";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { Voucher } from "src/app/Models/admin/VoucherModel";
import { VoucherUserService } from "../VouchersUser/VoucherUser.service";
import { VoucherUser } from "src/app/Models/admin/VoucherUserModel";
import { LowerCasePipe } from "@angular/common";
import { AddAddressComponent } from '../../User/addresses-book/add-address/add-address.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: "app-cart",
  templateUrl: "./cart.component.html",
  styleUrls: ["./cart.component.scss"],
})
export class CartComponent implements OnInit {
  pairBooks: CartBook[] = [];
  options: number[] = [];
  isLinear = false;

  quantity: number;
  currentTheme: string;

  //-------------------order details-------------------------

  address: Address;
  order: BookOrder;
  items: orderItem[] = [];
  finalOrder: BookOrder;
  refreshCount: number = 0;
  vouchers: Voucher[] = [];
  user: BookUser;
  empty: boolean = false;
  addresses : Address[]=[];

  voucherUsers: VoucherUser[] = [];
  newVoucherUser : VoucherUser;

  sendedOrder : boolean=false;

  constructor(
    public cartService: CartService,
    private themeSelectorService: ThemeSelectorService,
    private auth: LoginService,
    private addressService: AddressServiceService,
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private loadingService: LoadingService,
    private router: Router,
    private voucherUserService: VoucherUserService,
        public dialog: MatDialog,
  ) {}

  ngOnInit(): void {
    this.themeSelectorService.curentTheme.subscribe((e) => {
      this.currentTheme = e;
    });
    this.getBooks();
    this.user = this.auth.getUser();
    this.getAddress();
    this.order = new BookOrder();
    this.order.ordersUser = this.user;
    this.getAddress();
    this.getVouchers();
    this.getUserVouchers();
  }

  ngAfterContentChecked() {}

  GetHomeMainClass() {
    switch (this.currentTheme) {
      case ThemeNames.dark1:
      case ThemeNames.dark2:
        return "background-color-primary-900";
      default:
        return "";
    }
  }

  getBooks() {
    this.pairBooks = this.cartService.getBooks();
    this.pairBooks.forEach((e) => {
      this.quantity = e.quantity;
      //  e.book.bookPrice = e.book.bookPrice * this.quantity;
      this.options = [];
      for (let index = -1; index < 4; index++) {
        this.options.push(index + this.quantity);
      }
    });
    this.pairBooks.forEach((e) => {
      this.calculatePrice(e.quantity, e);
    });
  }
  calculatePrice(quantity: number, b: CartBook) {
    let price = 0;
    b.quantity = quantity;
    this.cartService.UpdateBook(b.book, quantity);
  }

  deleteItemCart(book: Book) {
    this.cartService.RemoveFromCart(book);
  }

  deleteAddress(address: Address) {
    //todo de completat asta
  }

  getAddress() {
    if (this.user == null || this.user == undefined) {
      this.toastr.Swal.fire({
        title: "Trebuie sa te loghezi pentru a comanda!",
        html: `Ai <b>${this.pairBooks.length}</b> -  <b>produse in cos`,
        // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da",
        cancelButtonText: "Nu",
      }).then((result) => {
        if (result.value) {
          this.router.navigate(["/login"]);
        }
      });
    }


    this.addressService.getAddresses(this.user.userId).subscribe((Response) =>{
      if(Response.status==ApiResponseType.SUCCESS){
        this.addresses = Response.body;
      }
    })
  }

  chooseAddress(add: Address) {
    console.log(add.streetName + "parametru");
    this.address = new Address();
    this.address = add;
    this.order = new BookOrder();
    this.order.idLocatie = add.addressId;
    console.log("order" + this.order.idLocatie);
    //todo ceva disable pe cealalta
  }

  AddAddress(){
    console.log("S-a intrat in functie");
    const dialogRef = this.dialog.open(AddAddressComponent, {
      width: '40%',
      data: {
        type: 'add'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddConfirm(result);
      }
    });
  }

  AddConfirm(address:Address) {
    //this.loadingService.start();

    this.addressService.addAddress(address, this.auth.getUser()).subscribe((response:ApiResponse<BookUser>) =>{
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Toast.fire({
          title: 'Adresa a fost adaugata cu succes!',
          icon: 'success'
        });
        this.getAddress();
    }
        else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la editare, incearca din nou!',
            'error'
          );
        }
      });
  }


  getVouchers() {
    if (this.user == null || this.user == undefined) {
      this.toastr.Swal.fire({
        title: "Trebuie sa te loghezi pentru a aduna vouchere!!",
        html: ``,
        // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da",
        cancelButtonText: "Nu",
      }).then((result) => {
        if (result.value) {
          this.router.navigate(["/login"]);
        }
      });
    }

    this.voucherUserService
      .getVouchersForUser(this.user)
      .subscribe((response: ApiResponse<Voucher[]>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.vouchers = response.body;
          console.log(this.vouchers);
        } else {
          this.empty = true;
          //todo handling in front sa iti apara ceva mesaj
        }
      });
  }

  getUserVouchers() {
    if (this.user == null || this.user == undefined) {
      this.toastr.Swal.fire({
        title: "Trebuie sa te loghezi pentru a aduna vouchere!!",
        html: ``,
        // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da",
        cancelButtonText: "Nu",
      }).then((result) => {
        if (result.value) {
          this.router.navigate(["/login"]);
        }
      });
    }

    this.voucherUserService
      .getUserVouchers(this.user)
      .subscribe((response: ApiResponse<VoucherUser[]>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.voucherUsers = response.body;
          console.log(this.voucherUsers);
        } else {
          //  this.empty=true;
          //todo handling in front sa iti apara ceva mesaj
        }
      });
  }

  chooseVoucher(voucher: Voucher) {
    console.log("Send method called");
    let subt = 0;
    let numberItems = 0;
    this.pairBooks.forEach((e) => {
      let item = new orderItem();
      item.booksorder = e.book;
      item.quantity = e.quantity;
      item.order = null;
      this.items.push(item);
      subt = subt + e.quantity * e.book.bookPrice;
      numberItems = numberItems + e.quantity;
    });

    this.order.ordersUser = this.user;
    this.order.items = this.items;
    this.order.shipping = 15;
    this.order.subtotal = subt;
    this.order.total = this.order.shipping + this.order.subtotal;

    this.order.numberItems = numberItems;
    const date = new Date();
    this.order.orderD = date;

    this.order.voucherDiscount = 0;
    this.order.vouchersForUser = [];

    let reducere = 0;
    let type = 0;
    if (voucher.author_voucher != null) {
      type = 1;
      console.log(this.order);
      console.log("type" + type);
      this.order.items.forEach((e) => {
        e.booksorder.bookAuthor.forEach((autor) => {
          if (autor.authorId == voucher.author_voucher) {
            let reducere_locala = 0;
            reducere_locala = (e.booksorder.bookPrice % voucher.voucherPrice)*e.quantity;
            this.order.subtotal = this.order.subtotal - reducere_locala;
            this.order.total = this.order.subtotal + this.order.shipping;
            reducere = reducere + reducere_locala;
          }
        });
      });

    }
    if (voucher.language != null) {
      type = 2;
      this.order.items.forEach((e) => {
        if (
          (e.booksorder.bookLanguage = voucher.language)
        ) {
          let reducere_locala = 0;
          reducere_locala = (e.booksorder.bookPrice % voucher.voucherPrice)*e.quantity;
          this.order.subtotal = this.order.subtotal - reducere_locala;
          reducere = reducere + reducere_locala;
        }
      });
    }

    this.order.voucherDiscount = reducere;

    if (type != 0) {
      this.toastr.Swal.fire({
        title: "FINALIZEAZA COMANDA!",
        html: `Id: AI${reducere} REDUCERE DACA COMANZI ACUM!</b>`,
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da",
        cancelButtonText: "Nu",
      }).then((result) => {
        if (result.value) {
          this.send();
          this.addVoucherUser();
          this.updateVoucherDetails();
        }
      });
    }
  }

  send() {
    // console.log("Send method called");
    // let subt = 0;
    // let numberItems = 0;
    // this.pairBooks.forEach((e) => {
    //   let item = new orderItem();
    //   item.booksorder = e.book;
    //   item.quantity = e.quantity;
    //   item.order =null;
    //   this.items.push(item);
    //   subt = subt + e.quantity * e.book.bookPrice;
    //   numberItems=numberItems+e.quantity;
    // });

    //TODO PAGINA DE FINALIZARE COMANDA OR SMTH
    this.toastr.Swal.fire({
      title: "ESTI SIGUR CA VREI SA FINALIZEZI COMANDA?",
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Da",
      cancelButtonText: "Nu",
    }).then((result) => {
      if (result.value) {
        this.sendedOrder = true;
        // this.order.items = this.items;
        // this.order.shipping = 15;
        // this.order.subtotal = subt;
        // this.order.total = this.order.shipping + this.order.subtotal;
        // this.order.ordersUser = this.user;
        // this.order.numberItems = numberItems;
        // const date = new Date();
        // this.order.orderD = date;

        // this.order.voucherDiscount=0;
        // this.order.vouchersForUser=[];

        console.log(this.order.idLocatie);
        console.log(this.order.ordersUser.userId);
        console.log(this.order.orderD);
        console.log(this.order.items.length);
        this.cartService
          .sendOrder(this.order)
          .subscribe((response: ApiResponse<BookOrder>) => {
            console.log("Send method finished");
            if (response && response.status == ApiResponseType.SUCCESS) {
              this.finalOrder = response.body;
              this.cartService.mailForOrder(response.body.orderId).subscribe((response:ApiResponse<boolean>)=>{
                if(response && response.status==ApiResponseType.SUCCESS){

                }
                else{
                  this.toastr.Toast.fire({
                    icon: "error",
                    title: "A aparut o eroare",
                  });

                }
              });



              console.log(this.finalOrder);
            } else {
              this.toastr.Toast.fire({
                icon: "error",
                title: "A aparut o eroare",
              });
            }
          }),
          // tslint:disable-next-line: no-unused-expression
          (error) => {
            console.error(error);
            this.toastr.Toast.fire({
              icon: "error",
              title: "A aparut o eroare in frontend",
            });
          };
      } else {
        //
      }
      this.cartService.RemoveAll();
    });
  }

  addVoucherUser(){

    this.newVoucherUser = new VoucherUser();
    this.newVoucherUser.orderWithVouchers=[];
    this.newVoucherUser.orderWithVouchers.push(this.order);
    this.newVoucherUser.used=false;
    this.newVoucherUser.usersWithVouchers=this.user;
    this.voucherUserService.addVoucherToUser(this.newVoucherUser).subscribe();

  }


  // usersWithVouchers: BookUser;
  // vouchers: Voucher;
  // used: boolean;
  // orderWithVouchers:BookOrder[];

  updateVoucherDetails(){

    this.voucherUserService.updateVoucherUser(this.newVoucherUser).subscribe();
  }
}
