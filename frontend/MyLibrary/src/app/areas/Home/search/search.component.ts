import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
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
import { Category } from 'src/app/Models/admin/CategoryModel';
import { Author } from 'src/app/Models/admin/AuthorModel';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import { AuthorsService } from '../../admin/pages/authors/authors.service';
import { PublishersService } from '../../admin/pages/publishers/publishers.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.scss']
})
export class SearchComponent implements OnInit {

  query :string;
  books : Book[]=[];
  categories : Category[] = [];
  chosenCategories : number[]=[];
  authors : Author[] = [];
  chosenAuthors : number[] =[];
  publishers : Publisher[] = [];
  chosenPublishers : number[] = [];
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
                  private SearchService : SearchService,
                  private loadingService : LoadingService,
                  public authorsService:AuthorsService,
                  public publishersService : PublishersService
                 ) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
      let query = params['query'];
      if(query && this.query!=query){
        this.query = query;
        this.search();
      }
    });


  }


  search(){

    this.SearchService.get(this.query).subscribe((response : ApiResponse<Book[]>) =>{
      if(response && response.status ==ApiResponseType.SUCCESS){

        this.books = response.body;
        console.log("carti " + this.books);
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

  GetCategories() {
    this.loadingService.start();
    this.landingBooksService
      .GetCategories()
      .subscribe((response: ApiResponse<Category[]>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
         //   this.displayColumn = false;
            // Nu ai si tb scoasa din view coloana
          }
          this.categories = response.body;




        }
      });
  }

  selectCategory(id:number){
    this.chosenCategories.push(id);
  }

  selectAuthor(id:number){
    this.chosenAuthors.push(id);
  }
  selectPublisher(id:number){
    this.chosenPublishers.push(id);
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


  getUrlImageForBook(book: Book) {
    return "url('data:image/jpg;base64," + book.bookImageDb + "')";
  }



  viewDetails(bookId: number) {
    this.router.navigate(["/home", "book", bookId]);
  }
}
