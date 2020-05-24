import { Quizz } from "src/app/Models/admin/QuizzModel";
import { ApiResponse } from "src/app/Models/general/api-response";
import { Injectable } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "src/app/services/toastr.service";
import { LandingBooksService } from "../welcome/LandingBooks.service";
import { DomSanitizer } from "@angular/platform-browser";
import { LoginService } from "../../login/login.service";
import { WishlistService } from "../book-details/wishlist.service";
import { PersonalBookService } from "../book-details/personalBook.service";
import { CartService } from "../cart/cart.service";
import { MatDialog } from "@angular/material/dialog";
import { BreakpointObserver } from "@angular/cdk/layout";
import { FormBuilder } from "@angular/forms";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { AuthorsService } from "../../admin/pages/authors/authors.service";
import { PublishersService } from "../../admin/pages/publishers/publishers.service";
import { HttpClient, HttpParams } from "@angular/common/http";
import { GlobalVarService } from "src/app/services/global-var.service";
import { TitleService } from "../../admin/services/title.service";
import { QuizzUser } from "src/app/Models/user/QuizzUser";

@Injectable({
  providedIn: "root",
})
export class QuizzPublicService {
  url: string;
  constructor(
    private http: HttpClient,
    private globalVarService: GlobalVarService,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private titleService: TitleService,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService
  ) {
    this.url = "/public/api/quizz/user";
  }

  GetQuizzez() {
    return this.http.get<ApiResponse<Quizz[]>>(
      this.globalVarService.globalUrl + this.url + "/get/all"
    );
  }
  GetQuizzezForUser(userId: number) {
    let params = new HttpParams().set("userId", userId.toString());

    return this.http.post<ApiResponse<Quizz[]>>(
      this.globalVarService.globalUrl + this.url + "/get/user",
      null,
      { params: params }
    );
  }

  AddQuizzToUser(quizzUser: QuizzUser , answer:String) {
    let params = new HttpParams().set("response",answer.toString());
    return this.http.post<ApiResponse<QuizzUser>>(
      this.globalVarService.globalUrl + this.url,
      quizzUser,{params:params}
    );
  }
}
