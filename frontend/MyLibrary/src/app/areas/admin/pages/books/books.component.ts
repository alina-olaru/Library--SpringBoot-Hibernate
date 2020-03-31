import { Book } from './../../../../Models/admin/BookModel';

import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';

import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';

import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { BookService } from './book.service';
import { AddEditBooksComponent } from './add-edit-books/add-edit-books.component';

@Component({
  selector: 'app-books',
  templateUrl: './books.component.html',
  styleUrls: ['./books.component.scss']
})
export class BooksComponent implements OnInit {


  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  Books: Book[] = [];
  _addBook: Book;
  displayedColumns: string[] = ['bookId', 'bookTitle', 'bookLanguage',
  'bookYear','numberOfPages','numberofVolumes','bookDescription','bookDimension',
  'bookWeight','bookPrice','coverType','numberOfReviews','bookRating','numberOfBoooks',
  'publisher','booksCategories',
  'actions'];
  dataSource: MatTableDataSource<Book> = new MatTableDataSource(this.Books);

  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
   public bookService:BookService
  ) {}

  ngOnInit() {
    this.GetBooks();
    this.titleService.setTitle('faGlobeEurope', 'Carti');
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
    this.bookService
      .GetBook()
      .subscribe((response: ApiResponse<Book[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: 'info',
              title: 'Nu exista carti in baza de date'
            });
          }

          this.Books = response.body;
          this.updateDataSouce();
        }
      });
  }

  DeleteBook(Book:Book) {
    this.toastr.Swal.fire({
      title: 'Esti sigur ca vrei sa stergi aceasta categorie?',
      html: `Id: <b>${Book.bookId}</b> - Nume: <b>${Book.bookTitle}
      - Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Da',
      cancelButtonText: 'Nu'
    }).then(result => {
      if (result.value) {
        this.loadingService.start();

        this.bookService
          .DeleteBook(Book.bookId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (response && response.status==ApiResponseType.SUCCESS && response.body == true) {
              this.toastr.Toast.fire({
                title: 'Cartea a fost stearsa!.',
                icon: 'success'
              });
              this.GetBooks();
            } else {
              this.toastr.Swal.fire(
                'Eroare!',
                'A aparut o eroare la stergere, incearca din nou!',
                'error'
              );
            }
          });
      }
    });
  }

  EditBook(item:Book) {
    const dialogRef=this.dialog.open(AddEditBooksComponent,{
      width: '400px',
      data: {
        type: 'edit',
        model: Object.assign({}, item)
      }

    });

    dialogRef.afterClosed().subscribe(result => {
      if(result != undefined && result !=null){
        this.EditBookConfirm(result,item);
      }
    })


  }

  EditBookConfirm(newBook:Book,oldBook:Book){
    this.loadingService.start();

    this.bookService.UpdateBook(newBook,newBook.bookId).subscribe((response:ApiResponse<Book>)=> {
      this.loadingService.stop();
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Toast.fire({
          title: 'Cartea a fost editata cu succes!',
          icon: 'success'

        });

        const idxOld=this.Books.indexOf(oldBook);
        this.Books[idxOld]=newBook;
        this.updateDataSouce();
      }

      else {
        this.toastr.Swal.fire(
          'Eroare!',
          'A aparut o eroare la editare, incearca din nou!',
          'error'
        );


      }
    },
  );
  }

  AddBook() {
    const dialogRef = this.dialog.open(AddEditBooksComponent, {
      width: '400px',
      data: {
        type: 'add',
        model: this._addBook
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
    this.bookService
      .AddBook(book)
      .subscribe((response: ApiResponse<Book>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Cartea a fost adaugata cu succes',
            icon: 'success'

          });
          this.Books.push(response.body);
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la adugare, incearca din nou!',
            'error'
          );


        }
      }, error => {
        this.toastr.Swal.fire(
          'Eroare!',
          'A aparut o eroare la adugare, incearca din nou!',
          'error'
        );
      });
  }
}
