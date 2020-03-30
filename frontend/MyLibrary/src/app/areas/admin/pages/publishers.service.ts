import { Observable } from "rxjs";
import { getTestBed } from "@angular/core/testing";
import { GlobalVarService } from "src/app/services/global-var.service";
import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ToastrService } from "src/app/services/toastr.service";
import { DomSanitizer } from "@angular/platform-browser";
import { TitleService } from "src/app/areas/admin/services/title.service";
import { MatDialog } from "@angular/material/dialog";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { threadId } from "worker_threads";
import { ApiResponse } from "src/app/Models/general/api-response";
import { Publisher } from 'src/app/Models/admin/PublisherModel';

@Injectable({
  providedIn: 'root'
})
export class PublishersService {

  baseUrl:String;


  constructor(
    private http: HttpClient,
    private globalVarService: GlobalVarService,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private titleService: TitleService,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService
  ) {
    this.baseUrl = "/public/api/edituri";

  }

  GetPublishers() {
    return this.http.get<ApiResponse<Publisher[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeletePublisher(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddPublisher(publisher: Publisher): Observable<ApiResponse<Publisher>> {
    return this.http.post<ApiResponse<Publisher>>(
      this.globalVarService.globalUrl + this.baseUrl,
      publisher
    );
  }

  UpdatePublisher(publisher:Publisher ,id: number): Observable<ApiResponse<Publisher>> {
    return this.http.put<ApiResponse<Publisher>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      publisher
    );
  }
}
