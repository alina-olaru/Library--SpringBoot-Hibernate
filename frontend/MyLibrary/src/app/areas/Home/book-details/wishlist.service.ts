import { ApiResponse } from 'src/app/Models/general/api-response';
import { Injectable } from '@angular/core';
import { Wishlist } from 'src/app/Models/user/Wishlist';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {
baseUrl: string;

constructor(

  private httpClient: HttpClient,
  private globalVarService: GlobalVarService,
  private cookieService: CookieService

) {

  this.baseUrl="/api/user/Wishlist";
}


AddWishlist(wishlist:Wishlist ){
  return this.httpClient.post<ApiResponse<Wishlist>>(this.globalVarService.globalUrl + this.baseUrl, wishlist );
}
}
