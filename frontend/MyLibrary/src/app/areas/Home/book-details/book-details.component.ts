import { WishlistService } from './wishlist.service';
import { BookUser } from 'src/app/Models/BookUser';
import { LoginService } from 'src/app/areas/login/login.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ApiResponse } from "./../../../Models/general/api-response";
import { LandingBooksService } from "./../welcome/LandingBooks.service";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap, Router } from "@angular/router";
import { Book } from "src/app/Models/admin/BookModel";
import { ToastrService } from "src/app/services/toastr.service";
import { switchMap } from "rxjs/operators";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { Wishlist } from 'src/app/Models/user/Wishlist';

@Component({
  selector: "app-book-details",
  templateUrl: "./book-details.component.html",
  styleUrls: ["./book-details.component.scss"],
})
export class BookDetailsComponent implements OnInit {
  book: Book;
  bookId: number;
  wishlist : Wishlist;
  user : BookUser;
  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private auth:LoginService,
    private wishService:WishlistService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.bookId = parseInt(this.route.snapshot.paramMap.get("id"));
    this.GetBookById(this.bookId);
    this.user=this.auth.getUser();

  }
  GetBookById(id: number) {
    this.bookId = id;
    console.info(id);

    this.landingBooksService
      .GetDetails(id)
      .subscribe((response: ApiResponse<Book>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {

          this.book = response.body;

          if(this.book.bookImage){
            this.book.bookImageSrc = this.domSanitizer.bypassSecurityTrustResourceUrl(
              "data:image/jpg;base64,"+this.book.bookImage
            )
          }
          console.log(this.book);
        } else {
          this.toastr.Toast.fire({
            icon: "error",
            title: "A aparut o eroare",
          });
        }
      },
      (error) => {
        this.toastr.Toast.fire({
          icon: "error",
          title: "A aparut o eroare",
        });
      });
  }


  getUrlImageForBook(){
    {
      return "url('data:image/jpg;base64," + this.book.bookImage + "')";
        }

  }

  AddToWhishlist(){

    this.wishlist = new Wishlist();
    this.wishlist.userwishlist=this.user;
    this.wishlist.bookwishlist=this.book;
    this.wishService.AddWishlist(this.wishlist).subscribe((response:ApiResponse<Wishlist>)=>{


      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Swal.fire({
          icon: "success",
          title: "Cartea a fost adaugata cu succes in wishlist",
          allowOutsideClick: false,
          allowEscapeKey: false,
        }).then((result) => {
          if (result.value) {
            this.router.navigate(['/cont/bibiotecapersonala']);
          }
        });
      }
           else {
            this.toastr.Toast.fire({
              icon: "error",
              title: response.message,
            });
          }


    })


  }
}
