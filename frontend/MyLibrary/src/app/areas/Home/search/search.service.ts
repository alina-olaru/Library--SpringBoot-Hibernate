import { Params } from '@angular/router';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Injectable, Optional } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { TitleService } from '../../admin/services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { Book } from 'src/app/Models/admin/BookModel';
import { identifierModuleUrl } from '@angular/compiler';

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


filter(disponibility : number , minPrice : number , maxPrice : number ,ratingMin : number ,
  authorsIds : number[] , categoriesIds : number[] , publishersIds : number[]){

    let params = new HttpParams();
    if(disponibility!=4){
      params.set("disponibility",disponibility.toString());
      if(minPrice!=-1){
        params.append("minPrice",minPrice.toString())
        .append("maxPrice",maxPrice.toString());
      }
      if(ratingMin!=-1){
        params.append("ratingMin",ratingMin.toString());
      }
    }


    if(authorsIds.length>0){
      params.append("authors",authorsIds.join(","));
    }
    if(categoriesIds.length>0){
      params.append("categories",categoriesIds.join(","));
    }
    if(categoriesIds.length>0){
      params.append("publishers",publishersIds.join(","));
    }

    return this.http.post<ApiResponse<Book[]>>(this.globalVarService.globalUrl + this.url + "/filter" ,{ },{params : params });
}

}
