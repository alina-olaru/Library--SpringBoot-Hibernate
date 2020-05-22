import { AuthorsService } from './../../admin/pages/authors/authors.service';
import { AuthorsComponent } from './../../admin/pages/authors/authors.component';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import { PublishersService } from './../../admin/pages/publishers/publishers.service';
import { Component, OnInit } from '@angular/core';
import { LandingBooksService } from '../welcome/LandingBooks.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Category } from 'src/app/Models/admin/CategoryModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { Author } from 'src/app/Models/admin/AuthorModel';

@Component({
  selector: 'app-SearchLayout',
  templateUrl: './SearchLayout.component.html',
  styleUrls: ['./SearchLayout.component.css']
})
export class SearchLayoutComponent implements OnInit {
  object = [
    {
      cod : 0,
      title: "Out of stock"
    },
    {
      cod: 1,
      title:"In stoc",
    },
    {
      cod: 2,
      title:"Reduceri",
    }
  ];

  prices = [
  {
    first : 0,
    last :10,
  },
  {
    first:10,
    last:20,
  },
  {
    first:20,
    last:30,
  },{
    first:30,
    last:40,
  },{
    first:40,
    last:50,
  },
  {
    first:50,
    last:100,
  },
  {
    first:100,
    last:1000,
  }
  ];


  disponibilitate : number = 1;
  minPrice:number;
  maxPrice:number;
  rating : number;
  categories : Category[] = [];
  chosenCategories : number[]=[];
  authors : Author[] = [];
  chosenAuthors : number[] =[];
  publishers : Publisher[] = [];
  chosenPublishers : number[] = [];

  constructor(
    public landingBookService: LandingBooksService,
    public loadingService: LoadingService,
    public router: Router,
    public dialog: MatDialog,
    public breakpointObserver: BreakpointObserver,
    public authorsService:AuthorsService,
    public publishersService : PublishersService

  ) { }

  ngOnInit() {
    this.GetCategories();
    this.getAuthors();
    this.getPublishers();
  }
  ngOnChanges()
  {
    console.log(this.disponibilitate);
  }

  GetCategories() {
    this.loadingService.start();
    this.landingBookService
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
}
