import { ToastrService } from './../../services/toastr.service';
import { Observable } from "rxjs";
import { JwtResponse } from "./../../Models/general/jwt-response";
import { ApiResponse } from "./../../Models/general/api-response";
import { JwtRequest } from "./../../Models/general/jwt-request";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { GlobalVarService } from "src/app/services/global-var.service";
import { CookieService } from "ngx-cookie-service";
import { Router } from "@angular/router";
import { BookUser } from "src/app/Models/BookUser";
import { tap } from "rxjs/operators";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";

@Injectable({
  providedIn: "root"
})
export class LoginService {

  baseUrl: String;

  private user: BookUser = null;
  private _token: String = null;

  constructor(
    private httpClient: HttpClient,
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router
  ) {

    this.baseUrl = "";

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

  loginUser(request: JwtRequest): Observable<ApiResponse<JwtResponse>> {
    return this.httpClient
      .post<ApiResponse<JwtResponse>>(
        this.gloablVarService.globalUrl + this.baseUrl + "/authenticate",
        request
      )
      .pipe(
        tap(x => {
          if (x && x.status == ApiResponseType.SUCCESS) {
            this._token = x.body.jwttoken;

            let expiration: Date = new Date();
            expiration.setDate(expiration.getDate() + 1);
            this.cookieService.set(
              "auth-token",
              JSON.stringify(x.body.jwttoken),
              expiration,
              '/'
            );

            if (this.user == null) {
              this.user = x.body.bookUser;
              let expiration: Date = new Date();
              expiration.setDate(expiration.getDate() + 1);
              this.cookieService.set(
                "auth-user-info",
                JSON.stringify(x.body.bookUser),
                expiration,
                '/'
              );
            }
          }
        })
      );
  }

  logOutUser() {
    this.cookieService.delete("auth-user-info", "/");
    this.cookieService.delete("auth-token", "/");
    this.user = null;
    this.router.navigate(["login"]);
  }

  updateUser(user : BookUser){
    this.cookieService.delete("auth-token");
    let expiration: Date = new Date();
    expiration.setDate(expiration.getDate() + 1);
    this.cookieService.set(
      "auth-token",
      JSON.stringify(user),
      expiration,
      '/'
    );

  }
}
