import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class PublishersService {

  baseUrl:String;


  constructor(
    private http: HttpClient,
    private globalVarService: GlobalVarService
  ) {
    this.baseUrl = "/api/edituri";

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
