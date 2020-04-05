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
import { Category } from './../../../../Models/admin/CategoryModel';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  baseUrl: string;


  constructor(
    private http: HttpClient,
    private globalVarService: GlobalVarService,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private titleService: TitleService,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService

  ) {
    this.baseUrl ="/api/admin/Category";
  }

  GetCategory() {
    return this.http.get<ApiResponse<Category[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteCategory(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddCategory(Category: Category): Observable<ApiResponse<Category>> {
    return this.http.post<ApiResponse<Category>>(
      this.globalVarService.globalUrl + this.baseUrl,
      Category
    );
  }

  UpdateCategory(Category: Category, id: number): Observable<ApiResponse<Category>> {
    return this.http.put<ApiResponse<Category>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      Category
    );
  }
}
