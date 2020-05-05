import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class ForgetPasswordService {
  baseUrl: string;

  constructor(
    private httpClient: HttpClient,
    private globalVarService: GlobalVarService,
    private cookieService: CookieService
  ) {
    this.baseUrl = "/public/api/user/password";
  }

 SendMailForPassword(email:string) :any
{

  let params1 = new HttpParams().
  set("email", email);


  return this.httpClient.post(this.globalVarService.globalUrl + this.baseUrl+"/sendMail", null,
  { params: params1 });
}


changePassword(token : string , newPassword : string){
  let paramsPass = new HttpParams()
  .set("password",newPassword)
  .set("token",token);
  return this.httpClient.post(this.globalVarService.globalUrl+this.baseUrl+"/change-password",null,
  { params : paramsPass});
}

}
