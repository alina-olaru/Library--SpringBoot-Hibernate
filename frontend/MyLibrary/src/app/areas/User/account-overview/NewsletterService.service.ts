import { Book } from './../../../Models/admin/BookModel';
import { ApiResponse } from './../../../Models/general/api-response';
import { Injectable } from '@angular/core';
import { BookUser } from 'src/app/Models/BookUser';
import { ToastrService } from 'src/app/services/toastr.service';
import { Router } from '@angular/router';
import { CookieService } from 'ngx-cookie-service';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { HttpClient } from '@angular/common/http';

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
    private toastr: ToastrService

){

  this.baseUrl = "/public/api/admin/user/details";

  let cachedUser = this.cookieService.get("auth-user-info");
  if (cachedUser != null && cachedUser != "") {
    this.user = JSON.parse(cachedUser) as BookUser;
  }

  let cachedToken = this.cookieService.get("auth-token");
  if (cachedToken != null && cachedToken != "") {
    this._token = JSON.parse(cachedToken);
  }


}



getUser() {
  return this.user;
}

get token() {
  return this._token;
}


YesToNews(){
  return this.httpClient.post<ApiResponse<BookUser>>(this.gloablVarService.globalUrl+this.baseUrl+"/yes",this.user.userId);
}


NoToNews(){
  return this.httpClient.post<ApiResponse<BookUser>>(this.gloablVarService.globalUrl+this.baseUrl+"/no",this.user.userId);
}



}
