import { Observable } from 'rxjs';
import { getTestBed } from '@angular/core/testing';
import { GlobalVarService } from './../../../../services/global-var.service';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ToastrService } from 'src/app/services/toastr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { TitleService } from '../../services/title.service';
import { MatDialog } from '@angular/material/dialog';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { Author} from '../../../../Models/admin/AuthorModel';
import { threadId } from 'worker_threads';
@Injectable({
  providedIn: 'root'
})
export class AuthorsService {
  baseUrl: string;

  constructor(private http:HttpClient,
    private globalVarService:GlobalVarService,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private titleService: TitleService,
    private sanitizer: DomSanitizer,
    private toastr: ToastrService
    ) {

      this.baseUrl="/api/authors";
    }


    GetAuthors(){
      this.
      return this.http.get<Author[]>(this.globalVarService.globalUrl+this.baseUrl);
    }

    Delete Author(id:number):Observable<boolean>
    {
      return this.http.delete<boolean>(this.globalVarService.globalUrl+this.baseUrl+'/'+id
      );
    }
}
