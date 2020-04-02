import { Observable } from "rxjs";
import { getTestBed } from "@angular/core/testing";
import { GlobalVarService } from "./../../../../services/global-var.service";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ToastrService } from "src/app/services/toastr.service";
import { DomSanitizer } from "@angular/platform-browser";
import { TitleService } from "../../services/title.service";
import { MatDialog } from "@angular/material/dialog";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { threadId } from "worker_threads";
import { ApiResponse } from "src/app/Models/general/api-response";
import { BookUser } from 'src/app/Models/BookUser';

@Injectable({
  providedIn: 'root'
})
export class UserService {


  baseUrl: string;


  constructor(

    private http: HttpClient,
    private globalVarService: GlobalVarService,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private titleService: TitleService,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService


  )

  {

      this.baseUrl = "/public/api/admin/BookUsers";
   }




  GetUsers() {
    return this.http.get<ApiResponse<BookUser[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteUser(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddUser(BookUser: BookUser): Observable<ApiResponse<BookUser>> {
    return this.http.post<ApiResponse<BookUser>>(
      this.globalVarService.globalUrl + this.baseUrl,
      BookUser
    );
  }

  UpdateUser(BookUser: BookUser, id: number): Observable<ApiResponse<BookUser>> {
    return this.http.put<ApiResponse<BookUser>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      BookUser
    );
  }
}
