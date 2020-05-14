import { BookUser } from 'src/app/Models/BookUser';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { VoucherUser } from './../../../Models/admin/VoucherUserModel';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { TitleService } from '../../admin/services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { Voucher } from 'src/app/Models/admin/VoucherModel';

@Injectable({
  providedIn: 'root'
})
export class VoucherUserService {

  baseUrl : string;
constructor(
  private http: HttpClient,
  private globalVarService: GlobalVarService,
  private loadingService: LoadingService,
  public dialog: MatDialog,
  private titleService: TitleService,
  private sanitizer: DomSanitizer,
  private toastr: ToastrService

) {

  this.baseUrl = "/api/vouchers/user";
 }


 addVoucherToUser(voucherUser : VoucherUser){

  return this.http.post<ApiResponse<VoucherUser>>(this.globalVarService.globalUrl+this.baseUrl , voucherUser);
}

getVouchersForUser(user : BookUser)  {
  return this.http.post<ApiResponse<Voucher[]>>(this.globalVarService.globalUrl+this.baseUrl+"/user" , user);
}

getUserVouchers(user : BookUser)  {
  return this.http.post<ApiResponse<VoucherUser[]>>(this.globalVarService.globalUrl+this.baseUrl+"/user/all" , user);
}
}
