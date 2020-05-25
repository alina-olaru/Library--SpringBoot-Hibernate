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


  object = [
    {
      cod: 0,
      title: "Out of stock",
    },
    {
      cod: 1,
      title: "In stoc",
    },
    {
      cod: 2,
      title: "Reduceri",
    },
  ];

  prices = [
    {
      first: 0,
      last: 10,
    },
    {
      first: 10,
      last: 20,
    },
    {
      first: 20,
      last: 30,
    },
    {
      first: 30,
      last: 40,
    },
    {
      first: 40,
      last: 50,
    },
    {
      first: 50,
      last: 100,
    },
    {
      first: 100,
      last: 1000,
    },
  ];
  query :string;
  books : Book[]=[];
  categories : Category[] = [];
  chosenCategories : number[]=[];
  authors : Author[] = [];
  chosenAuthors : number[] =[];
  publishers : Publisher[] = [];
  chosenPublishers : number[] = [];
  disponibilitate: number = 2;
  minPrice: number = -1;
  maxPrice: number = -1;
  rating: number=-1;

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
                 ) {
                  this.router.routeReuseStrategy.shouldReuseRoute = () => false;
                  this.route.paramMap.subscribe(params => {
                    //fetch your new parameters here, on which you are switching the routes and call ngOnInit()
                    this.ngOnInit();
                   });


                  }

  ngOnInit() {

    this.route.params.subscribe(params => {
      let query = params['query'];
      if(query && this.query!=query){
        this.query = query;
        this.search();
      }
    });
    this.GetCategories();
    this.getAuthors();
    this.getPublishers();
  }

ngOnChanges(){
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

  setPrice(item: any) {


    this.minPrice = item.first;
    this.maxPrice = item.last;
  }
  filter() {
    this.loadingService.start();
    console.log(this.disponibilitate + "disp");
    console.log(this.chosenAuthors + "autori");
    console.log(this.chosenCategories + "cat");
    console.log(this.chosenPublishers + "publisjers");
    console.log(this.rating + "rating");
    console.log(this.minPrice + "min-max " + this.maxPrice);
    console.log("layout");
    console.log(this.query);
    this.SearchService.filterwithWord(this.disponibilitate , this.minPrice , this.maxPrice , this.rating , this.chosenAuthors , this.chosenCategories , this.chosenPublishers,this.query).subscribe((Response : ApiResponse<Book[]>) =>{
      if(Response && Response.status == ApiResponseType.SUCCESS)
      {
        this.loadingService.stop();
        this.books = Response.body;
      }

    })

  }

}
