import { ApiResponse } from './../../../../Models/general/api-response';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { MatDialog } from '@angular/material/dialog';
import { TitleService } from '../../services/title.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { PersonalBook } from 'src/app/Models/home/PersonalBook';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {
baseUrl:string;

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
    this.baseUrl="/api/admin/dashboard";
  }


  getCategoriesWithNumberBooks(){
    return this.http.get<ApiResponse<any[]>>(this.globalVarService.globalUrl + this.baseUrl+"/categories/count");
    }

    getAuthorsNumberBooks(){
      return this.http.get<ApiResponse<any[]>>(this.globalVarService.globalUrl + this.baseUrl+"/authors/count");

    }
}

