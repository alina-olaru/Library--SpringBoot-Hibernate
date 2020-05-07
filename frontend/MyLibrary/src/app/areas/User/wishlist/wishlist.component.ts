import { Router } from '@angular/router';
import { Book } from 'src/app/Models/admin/BookModel';
import { BookUser } from 'src/app/Models/BookUser';
import { LoginService } from 'src/app/areas/login/login.service';
import { LoginComponent } from './../../login/login.component';
import { WishlistService } from './../../Home/book-details/wishlist.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.scss']
})
export class WishlistComponent implements OnInit {
user:BookUser;
books:Book[];

  constructor(public wishlistService:WishlistService,
              public auth:LoginService,
              public router:Router) { }

  ngOnInit(): void {
    this.user=this.auth.getUser();
    this.GetWhishlist();
  }

  GetWhishlist(){
   this.wishlistService.GetWishlistById(this.user.userId).subscribe((response=>{
      console.log(response);
      this.books =response.body;

    }))

  }

  getUrlImageForBook(book: Book){
    return "url('data:image/jpg;base64," + book.bookImage + "')";
      }

      viewDetails(bookId : number){

        this.router.navigate(['/home', 'book',  bookId]);
      }


}
