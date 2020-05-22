import { Subscription } from "rxjs";
import { Category } from "src/app/Models/admin/CategoryModel";
import { Publisher } from "src/app/Models/admin/PublisherModel";
import { Author } from "src/app/Models/admin/AuthorModel";
import { PublishersService } from "./../publishers/publishers.service";
import { CategoryService } from "./../category/category.service";
import { AuthorsService } from "./../authors/authors.service";
import { Book } from "./../../../../Models/admin/BookModel";

import { MatTableDataSource } from "@angular/material/table";
import { TitleService } from "./../../services/title.service";
import { Component, OnInit, ViewChild, OnDestroy } from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { ToastrService } from "src/app/services/toastr.service";
import { MatPaginator } from "@angular/material/paginator";
import { MatSort } from "@angular/material/sort";

import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { MatDialog } from "@angular/material/dialog";

import { ApiResponse } from "src/app/Models/general/api-response";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { BookService } from "./book.service";
import { AddEditBooksComponent } from "./add-edit-books/add-edit-books.component";

@Component({
  selector: "app-books",
  templateUrl: "./books.component.html",
  styleUrls: ["./books.component.scss"]
})
export class BooksComponent implements OnInit, OnDestroy {
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  Books: Book[] = [];
  authors: Author[] = [];
  publishers: Publisher[] = [];
  categories: Category[] = [];

  _addBook: Book;
  displayedColumns: string[] = [
    "bookId",
    "bookTitle",
    "bookLanguage",
    "bookYear",
    "numberOfPages",
    "numberofVolumes",
    "bookDescription",
    "bookDimension",
    "bookWeight",
    "bookPrice",
    "lastPrice",
    "coverType",
    "numberOfBoooks",
    "publisher",
    "bookAuthor",
    "booksCategories",
    "bookImage",
    "actions"
  ];
  dataSource: MatTableDataSource<Book> = new MatTableDataSource(this.Books);
  subscriptions: Subscription[] = [];

  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    private dialog: MatDialog,
    private bookService: BookService,
    private authorsService: AuthorsService,
    private categoryService: CategoryService,
    private publishersService: PublishersService
  ) {}
  ngOnDestroy(): void {
    this.subscriptions.forEach(e => {
      e.unsubscribe();
    });
  }

  ngOnInit() {
    this.GetBooks();
    this.titleService.setTitle("faBook", "Carti");
    this.getAuthors();
    this.getPublishers();
    this.getCategories();
  }

  getAuthors() {
    let autSubscriber = this.authorsService.GetAuthors().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.authors = response.body;
      }
    });
    this.subscriptions.push(autSubscriber);
  }

  getPublishers() {
    let pubSubscriber = this.publishersService
      .GetPublishers()
      .subscribe(response => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.publishers = response.body;
        }
      });
    this.subscriptions.push(pubSubscriber);
  }

  getCategories() {
    let catSubscriber = this.categoryService
      .GetCategory()
      .subscribe(response => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.categories = response.body;
        }
      });
    this.subscriptions.push(catSubscriber);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.Books);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }
  GetBooks() {
    this.loadingService.start();
    this.bookService.GetBook().subscribe((response: ApiResponse<Book[]>) => {
      this.loadingService.stop();

      if (response && response.status == ApiResponseType.SUCCESS) {
        if (response.body.length == 0) {
          this.toastr.Toast.fire({
            icon: "info",
            title: "Nu exista carti in baza de date"
          });
          return;
        }
        this.Books = response.body;
        this.Books.forEach((e) => {
          if (e.bookImageDb) {
            const objectURL = 'data:image/png;base64,' + e.bookImageDb;
            e.bookImageSrc = this.sanitizer.bypassSecurityTrustResourceUrl(
              objectURL
            );
          }
        });


        this.updateDataSouce();
      }
    });
  }

  DeleteBook(Book: Book) {
    this.toastr.Swal.fire({
      title: "Esti sigur ca vrei sa stergi aceasta categorie?",
      html: `Id: <b>${Book.bookId}</b> - Nume: <b>${Book.bookTitle}`,
      // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
      icon: "warning",
      showCancelButton: true,
      confirmButtonColor: "#3085d6",
      cancelButtonColor: "#d33",
      confirmButtonText: "Da",
      cancelButtonText: "Nu"
    }).then(result => {
      if (result.value) {
        this.loadingService.start();

        this.bookService
          .DeleteBook(Book.bookId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (
              response &&
              response.status == ApiResponseType.SUCCESS &&
              response.body == true
            ) {
              this.toastr.Toast.fire({
                title: "Cartea a fost stearsa!.",
                icon: "success"
              });
              this.GetBooks();
            } else {
              this.toastr.Swal.fire(
                "Eroare!",
                "A aparut o eroare la stergere, incearca din nou!",
                "error"
              );
            }
          });
      }
    });
  }

  EditBook(item: Book) {
    const dialogRef = this.dialog.open(AddEditBooksComponent, {
      width: "50%",
      data: {
        type: "edit",
        model: Object.assign({}, item),
        authors: this.authors,
        publishers: this.publishers,
        categories: this.categories
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.EditBookConfirm(result, item);
      }
    });
  }

  EditBookConfirm(newBook: Book, oldBook: Book) {
    this.loadingService.start();
    this.bookService
      .UpdateBook(newBook, newBook.bookId)
      .subscribe((response: ApiResponse<Book>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: "Cartea a fost editata cu succes!",
            icon: "success"
          });

          const idxOld = this.Books.indexOf(oldBook);
          this.Books[idxOld] = newBook;
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            "Eroare!",
            "A aparut o eroare la editare, incearca din nou!",
            "error"
          );
        }
      });
  }

  AddBook() {
    const dialogRef = this.dialog.open(AddEditBooksComponent, {
      width: "50%",
      data: {
        type: "add",
        model: this._addBook,
        authors: this.authors,
        publishers: this.publishers,
        categories: this.categories
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddBookConfirm(result);
      }
    });
  }

  AddBookConfirm(book: Book) {
    this.loadingService.start();
    this.bookService.AddBook(book).subscribe(
      (response: ApiResponse<Book>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: "Cartea a fost adaugata cu succes",
            icon: "success"
          });
          if (response.body.bookImageDb) {
            const objectURL =
              'data:image/png;base64,' + response.body.bookImageDb;
            response.body.bookImageSrc = this.sanitizer.bypassSecurityTrustResourceUrl(
              objectURL
            );
          }
          this.Books.unshift(response.body);
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            "Eroare!",
            "A aparut o eroare la adugare, incearca din nou!",
            "error"
          );
        }
      },
      error => {
        console.log(error);
        this.toastr.Swal.fire(
          "Eroare!",
          "A aparut o eroare la adugare, incearca din nou!",
          "error"
        );
      }
    );
  }
}
