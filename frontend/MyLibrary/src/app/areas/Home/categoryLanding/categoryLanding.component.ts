import { LandingBooksService } from 'src/app/areas/Home/welcome/LandingBooks.service';
import { Component, OnInit } from '@angular/core';
import { TitleService } from '../../admin/services/title.service';
import { ToastrService } from 'src/app/services/toastr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { VoucherService } from '../../admin/pages/vouchers/voucher.service';
import { AuthorsService } from '../../admin/pages/authors/authors.service';
import { CategoryService } from '../../admin/pages/category/category.service';
import { PublishersService } from '../../admin/pages/publishers/publishers.service';
import { Book } from 'src/app/Models/admin/BookModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-categoryLanding',
  templateUrl: './categoryLanding.component.html',
  styleUrls: ['./categoryLanding.component.scss']
})
export class CategoryLandingComponent implements OnInit {
  categoryTitle:string;
  books : Book[]=[];
  constructor(  private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private voucherService: VoucherService,
    private authorsService: AuthorsService,
    private categoryService: CategoryService,
    private publishersService: PublishersService,
    private landingService :LandingBooksService,
    private route: ActivatedRoute,
    private router:Router
    )
    {

    }

  ngOnInit() {
    this.categoryTitle = this.route.snapshot.paramMap.get("idb");
    this.getBooks();
  }

  getBooks(){
    this.landingService.GetBooksSF(this.categoryTitle,1000).subscribe((response)=>{
      if(response && response.status ==ApiResponseType.SUCCESS){
        this.books = response.body;
      }
    })
  }


  getUrlImageForBook(book: Book) {
    return "url('data:image/jpg;base64," + book.bookImageDb + "')";
  }



  viewDetails(bookId: number) {
    this.router.navigate(["/home", "book", bookId]);
  }
}


