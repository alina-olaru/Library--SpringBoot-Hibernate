import { Category } from 'src/app/Models/admin/CategoryModel';
import { HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { TitleService } from '../../admin/services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Book } from 'src/app/Models/admin/BookModel';

@Injectable({
  providedIn: 'root'
})
export class LandingBooksService {
  baseUrl:string;
  baseUrlCat:string;

constructor(

  private http: HttpClient,
  private globalVarService: GlobalVarService,
  private loadingService: LoadingService,
  public dialog: MatDialog,
  private titleService: TitleService,
  private sanitizer: DomSanitizer,
  private toastr: ToastrService


)   {
  this.baseUrl="/public/api/landing/book";
  this.baseUrlCat="/api/admin/Category";
}


GetCategories(){

  return this.http.get <ApiResponse<Category[]>>(this.globalVarService.globalUrl +this.baseUrlCat);
}
GetBooksSF(title: string, count: number){

  // const params1=new HttpParams();
  // const body=null;
  // params1.set('title' , 'SF');

  let params1 = new HttpParams().
  set("title", title)
  .append("count", count.toString());

  return this.http.get<ApiResponse<Book[]>>(this.globalVarService.globalUrl + this.baseUrl + '/test' ,
  { params: params1 });

}

}
