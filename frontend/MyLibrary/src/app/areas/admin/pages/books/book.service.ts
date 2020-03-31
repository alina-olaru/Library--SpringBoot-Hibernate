
import { Observable } from "rxjs";
import { getTestBed } from "@angular/core/testing";
import { GlobalVarService } from "./../../../../services/global-var.service";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ToastrService } from "src/app/services/toastr.service";
import { DomSanitizer } from "@angular/platform-browser";
import { TitleService } from "../../services/title.service";
import { MatDialog } from "@angular/material/dialog";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { threadId } from "worker_threads";
import { ApiResponse } from "src/app/Models/general/api-response";
import { Book } from './../../../../Models/admin/BookModel';
@Injectable({
  providedIn: 'root'
})
export class BookService {
  baseUrl: string;


  constructor(
    private http: HttpClient,
    private globalVarService: GlobalVarService,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private titleService: TitleService,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService

  ) {
    this.baseUrl ="/public/api/books";
  }

  GetBook() {
    return this.http.get<ApiResponse<Book[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteBook(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddBook(Book: Book): Observable<ApiResponse<Book>> {
    return this.http.post<ApiResponse<Book>>(
      this.globalVarService.globalUrl + this.baseUrl,
      Book
    );
  }

  UpdateBook(Book: Book, id: number): Observable<ApiResponse<Book>> {
    return this.http.put<ApiResponse<Book>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      Book
    );
  }
}
