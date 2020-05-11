import { Observable } from 'rxjs';
import { getTestBed } from '@angular/core/testing';
import { GlobalVarService } from './../../../../services/global-var.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'src/app/services/toastr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { TitleService } from '../../services/title.service';
import { MatDialog } from '@angular/material/dialog';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { Author } from '../../../../Models/admin/AuthorModel';
import { threadId } from 'worker_threads';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Book } from 'src/app/Models/admin/BookModel';
@Injectable({
  providedIn: 'root'
})
export class AuthorsService {
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
    this.baseUrl = '/api/author';
  }

  GetAuthors() {
    return this.http.get<ApiResponse<Author[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteAuthor(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + '/' + id
    );
  }



  AddAuthor(author: Author): Observable<ApiResponse<Author>> {
    return this.http.post<ApiResponse<Author>>(
      this.globalVarService.globalUrl + this.baseUrl,
      author
    );
  }

  UpdateAuthor(author: Author, id: number): Observable<ApiResponse<Author>> {
    return this.http.put<ApiResponse<Author>>(
      this.globalVarService.globalUrl + this.baseUrl + '/' + id,
      author
    );
  }
}
