import { Observable } from "rxjs";
import { BookUser } from "./../../Models/BookUser";
import { GlobalVarService } from "./../../services/global-var.service";
import { Injectable } from "@angular/core";
import { HttpClient, HttpParams } from "@angular/common/http";
import { tap } from "rxjs/operators";
import { CookieService } from "ngx-cookie-service";
import { ApiResponse } from "src/app/Models/general/api-response";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";

@Injectable({
  providedIn: "root"
})
export class RegisterService {
  baseUrl: string;
  constructor(
    private httpClient: HttpClient,
    private globalVarService: GlobalVarService,
    private cookieService: CookieService
  ) {
    this.baseUrl = "/public/register";
  }

  RegiserUser(user: BookUser): Observable<ApiResponse<BookUser>> {
    return this.httpClient
      .post<ApiResponse<BookUser>>(
        this.globalVarService.globalUrl + this.baseUrl + "/do",
        user
      )
      .pipe(
        tap(x => {
          if (x && x.status == ApiResponseType.SUCCESS) {
            let expiration: Date = new Date();
            expiration.setDate(
              expiration.getDate() + this.globalVarService.cookieDaysExpiration
            );
            // this.cookieService.set(
            //   "auth-user-info",
            //   JSON.stringify(x.body),
            //   expiration
            // );
          }
        })
      );
  }



  ConfirmUser(token:string): Observable<ApiResponse<BookUser>> {

    return this.httpClient.post<ApiResponse<BookUser>>(this.globalVarService.globalUrl+this.baseUrl + "/confirm-account" , token);


  }
}
