import { ApiResponse } from 'src/app/Models/general/api-response';
import { SearchService } from './search.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../welcome/LandingBooks.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoginService } from '../../login/login.service';
import { WishlistService } from '../book-details/wishlist.service';
import { PersonalBookService } from '../book-details/personalBook.service';
import { CartService } from '../cart/cart.service';
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';
import { FormBuilder } from '@angular/forms';
import { Book } from 'src/app/Models/admin/BookModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  query :string;
  books : Book[]=[];
  constructor(    private route: ActivatedRoute,
                  private toastr: ToastrService,
                  private landingBooksService: LandingBooksService,
                  private domSanitizer: DomSanitizer,
                  private auth: LoginService,
                  private wishService: WishlistService,
                  private router: Router,
                  private personalBookService: PersonalBookService,
                  private cartService: CartService,
                  public dialog: MatDialog,
                  public breakpointObserver: BreakpointObserver,
                  private formBuilder: FormBuilder,
                  private SearchService : SearchService
                 ) { }

  ngOnInit() {
    this.query =(this.route.snapshot.paramMap.get('query'));
    console.log("testtt" + this.query);
    this.search();
  }


  search(){

    this.SearchService.get(this.query).subscribe((response : ApiResponse<Book[]>) =>{
      if(response && response.status ==ApiResponseType.SUCCESS){

        this.books = response.body;
      }
      else {
        this.toastr.Swal.fire(
          'Eroare!',
         response.message,
          'error'
        );
      }
    })
  }
}
