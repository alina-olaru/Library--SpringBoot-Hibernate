import { Params } from '@angular/router';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { TitleService } from '../../admin/services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { Book } from 'src/app/Models/admin/BookModel';

@Injectable({
  providedIn: 'root'
})
export class SearchService {
url : string;
constructor(
  private http: HttpClient,
  private globalVarService: GlobalVarService,
  private loadingService: LoadingService,
  public dialog: MatDialog,
  private titleService: TitleService,
  private sanitizer: DomSanitizer,
  private toastr: ToastrService

) {
  this.url = "/public/search";
}


get(query: string){
  let params = new HttpParams()
  .set("query",query.toString());
  return this.http.get<ApiResponse<Book[]>>(this.globalVarService.globalUrl + this.url ,{params : params});
}
}
