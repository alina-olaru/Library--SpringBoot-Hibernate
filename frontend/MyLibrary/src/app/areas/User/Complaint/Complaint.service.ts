import { ApiResponse } from 'src/app/Models/general/api-response';
import { Complaint } from './../../../Models/user/Complaint';
import { Injectable } from '@angular/core';
import { ToastrService } from 'src/app/services/toastr.service';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ComplaintService {
baseUrl:string;
constructor(
  private httpClient: HttpClient,
  private gloablVarService: GlobalVarService,
  private cookieService: CookieService,
  private router: Router,
  private toastr: ToastrService

) {
  this.baseUrl = "/public/api/cereri";
}


addComplaint(model: Complaint){
  return this.httpClient.post<ApiResponse<Complaint>>(this.gloablVarService.globalUrl + this.baseUrl , model);
}

getComsbyId(id :number){
  return this.httpClient.get<ApiResponse<Complaint[]>>(this.gloablVarService.globalUrl + this.baseUrl + "/" + id);
}
}
