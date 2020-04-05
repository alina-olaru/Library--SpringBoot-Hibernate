import { Observable } from 'rxjs';
import { ApiResponse } from './../../../../Models/general/api-response';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { timingSafeEqual } from 'crypto';
import { Voucher } from 'src/app/Models/admin/VoucherModel';

@Injectable({
  providedIn: 'root'
})
export class VoucherService {
baseUrl:string;


  constructor(

    private http: HttpClient,
    private globalVarService: GlobalVarService

  ) {
    this.baseUrl="/api/admin/voucher";
  }


  GetVouchers(){


    return this.http.get<ApiResponse<Voucher[]>>(this.globalVarService.globalUrl + this.baseUrl);
  }



  DeleteVoucher(id:number):Observable<ApiResponse<boolean>>{
    return this.http.delete<ApiResponse<boolean>>
    (this.globalVarService.globalUrl + this.baseUrl + "/" + id);
  }



  AddVoucher(voucher : Voucher){

    return this.http.post<ApiResponse<Voucher>>(this.globalVarService.globalUrl + this.baseUrl , voucher);
  }


  UpdateVoucher(voucher:Voucher,id:number):Observable<ApiResponse<Voucher>>
  {
    return this.http.put<ApiResponse<Voucher>>(this.globalVarService.globalUrl + this.baseUrl + "/" + id , voucher);
  }



}
