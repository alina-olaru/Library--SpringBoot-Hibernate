import { LoginService } from './../../login/login.service';
import { Book } from './../../../Models/admin/BookModel';
import { ApiResponse } from './../../../Models/general/api-response';
import { Injectable } from '@angular/core';
import { BookUser } from 'src/app/Models/BookUser';
import { ToastrService } from 'src/app/services/toastr.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class NewsletterServiceService {
  baseUrl: String;

  private user: BookUser = null;
  private _token: String = null;

constructor(
  private httpClient: HttpClient,
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService,
    private auth : LoginService

){


  this.user = this.auth.getUser();
  console.log(this.user);
  this.baseUrl="/public/api/admin/user/details";

}




YesToNews(){

  let params1 = new HttpParams().
  set("id", this.user.userId.toString());
  return this.httpClient.post<ApiResponse<BookUser>>(this.gloablVarService.globalUrl+this.baseUrl+"/yes", null ,
  {params : params1});
}


NoToNews(){


  let params1 = new HttpParams().
  set("id", this.user.userId.toString());

  return this.httpClient.post<ApiResponse<BookUser>>(this.gloablVarService.globalUrl + this.baseUrl+ "/no" , null ,
  {params : params1});
}



}
