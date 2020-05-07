import { UserAddressAdd } from "./../../../Models/user/UserAddressAdd";
import { Address } from "./../../../Models/Address";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { GlobalVarService } from "src/app/services/global-var.service";
import { CookieService } from "ngx-cookie-service";
import { Router } from "@angular/router";
import { ToastrService } from "src/app/services/toastr.service";
import { ApiResponse } from "src/app/Models/general/api-response";
import { BookUser } from "src/app/Models/BookUser";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root",
})
export class AddressServiceService {
  private baseUrl: string;
  private user: BookUser = null;
  private _token: String = null;

  constructor(
    private httpClient: HttpClient,
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService
  ) {
    this.baseUrl = "/public/api/users/address";
  }

  addAddress(address: Address, user: BookUser): Observable<ApiResponse<BookUser>> {
    let body = {
      user,
      address,
    } as UserAddressAdd;
    return this.httpClient.post<ApiResponse<BookUser>>(
      this.gloablVarService.globalUrl + this.baseUrl,
      body
    );
  }


}
