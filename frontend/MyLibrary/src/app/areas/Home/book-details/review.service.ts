import { Params } from '@angular/router';
import { ApiResponse } from './../../../Models/general/api-response';
import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { TitleService } from '../../admin/services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { Review } from 'src/app/Models/home/Review';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  baseUrl:string;
constructor(
  private http: HttpClient,
  private globalVarService: GlobalVarService,
  private loadingService: LoadingService,
  public dialog: MatDialog,
  private titleService: TitleService,
  private sanitizer: DomSanitizer,
  private toastr: ToastrService,

  ) {
  this.baseUrl= "/public/api/review";
}


addReview(review : Review){

  return this.http.post<ApiResponse<Review>>(this.globalVarService.globalUrl +"/public/api/review",review);
}

getReviewsForUser(userId : number){
  let paramsPass =new HttpParams()
  .set("userId" , userId.toString());
  return this.http.get<ApiResponse<Review[]>>(this.globalVarService.globalUrl + this.baseUrl + "/user", { params : paramsPass});
}


getReviewsForBook(bookId : number){
  let paramsPass =new HttpParams()
  .set("bookId" , bookId.toString());
  return this.http.get<ApiResponse<Review[]>>(this.globalVarService.globalUrl + this.baseUrl + "/book", { params : paramsPass});
}

}
