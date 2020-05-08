import { ApiResponse } from 'src/app/Models/general/api-response';
import { PersonalBook } from './../../../Models/home/PersonalBook';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class PersonalBookService {
  baseUrl: string;

  constructor(
    private httpClient: HttpClient,
    private globalVarService: GlobalVarService,
    private cookieService: CookieService
  ) {
    this.baseUrl = '/api/user/personalBook';
  }


  addBook(book:PersonalBook){
    return this.httpClient.post<ApiResponse<PersonalBook>>(this.globalVarService.globalUrl+this.baseUrl,book);
  }

  getBooks(type:number,id:number){

    let params=new HttpParams()
    .set("type",type.toString())
    .append("userId",id.toString());
    return this.httpClient.get<ApiResponse<PersonalBook[]>>(this.globalVarService.globalUrl+this.baseUrl,
      {params : params});
  }



  checkIfBookIsAlreadyPersonal(userId:number,bookId:number){

    let params=new HttpParams()
    .set("bookId",bookId.toString())
    .append("userId",userId.toString());
    return this.httpClient.get<ApiResponse<Boolean>>(this.globalVarService.globalUrl + this.baseUrl + "/my",
      {params : params});
  }

  deletePers(userId:number,bookId:number){

    let params=new HttpParams()
    .set("bookId",bookId.toString())
    .append("userId",userId.toString());
    return this.httpClient.delete<ApiResponse<Boolean>>(this.globalVarService.globalUrl + this.baseUrl + "/delete",
      {params : params});
  }
}
