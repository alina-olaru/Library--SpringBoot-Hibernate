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
import { User } from "../../../../Models/admin/UserModel";
import { threadId } from "worker_threads";
import { ApiResponse } from "src/app/Models/general/api-response";

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

      this.baseUrl = "/public/api/admin/users";
   }




  GetUsers() {
    return this.http.get<ApiResponse<User[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteUser(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddUser(user: User): Observable<ApiResponse<User>> {
    return this.http.post<ApiResponse<User>>(
      this.globalVarService.globalUrl + this.baseUrl,
      user
    );
  }

  UpdateUser(user: User, id: number): Observable<ApiResponse<User>> {
    return this.http.put<ApiResponse<User>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      user
    );
  }
}
