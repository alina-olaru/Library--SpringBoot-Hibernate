import { ApiResponse } from 'src/app/Models/general/api-response';
import { Injectable } from '@angular/core';
import { Wishlist } from 'src/app/Models/user/Wishlist';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Book } from 'src/app/Models/admin/BookModel';

@Injectable({
  providedIn: 'root',
})
export class WishlistService {
  baseUrl: string;

  constructor(
    private httpClient: HttpClient,
    private globalVarService: GlobalVarService,
    private cookieService: CookieService
  ) {
    this.baseUrl = '/api/user/Wishlist';
  }

  AddWishlist(wishlist: Wishlist) {
    return this.httpClient.post<ApiResponse<Wishlist>>(
      this.globalVarService.globalUrl + this.baseUrl,
      wishlist
    );
  }

  DeleteWishlist(userId: number, bookId: number) {
    const params = new HttpParams()
    .set('bookId', bookId.toString())
    .append('userId', userId.toString());

  return this.httpClient.delete<ApiResponse<boolean>>(
    this.globalVarService.globalUrl + this.baseUrl ,
    { params }
  );

  }

  GetWishlistById(id: number) {
    return this.httpClient.get<ApiResponse<Book[]>>(
      this.globalVarService.globalUrl + this.baseUrl + '/' + id
    );
  }

  checkIfWish(userId: number, bookId: number) {
    const params = new HttpParams()
      .set('bookId', bookId.toString())
      .append('userId', userId.toString());

    return this.httpClient.get<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + '/exists',
      { params }
    );
  }
}
