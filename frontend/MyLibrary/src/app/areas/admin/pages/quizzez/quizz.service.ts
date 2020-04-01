
import { Injectable, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Observable } from 'rxjs';
import { Quizzez } from 'src/app/Models/admin/QuizzezModel';

@Injectable({
  providedIn: 'root'
})
export class QuizzService {
  baseUrl:String;

  constructor(

    private http: HttpClient,
    private globalVarService: GlobalVarService

    )
     {
       this.baseUrl="/public/api/admin/Quizz";

     }




  GetQuizzez() {
    return this.http.get<ApiResponse<Quizzez[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteQuizzez(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddQuizzez(publisher: Quizzez): Observable<ApiResponse<Quizzez>> {
    return this.http.post<ApiResponse<Quizzez>>(
      this.globalVarService.globalUrl + this.baseUrl,
      publisher
    );
  }

  UpdateQuizzez(publisher:Quizzez ,id: number): Observable<ApiResponse<Quizzez>> {
    return this.http.put<ApiResponse<Quizzez>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      publisher
    );
  }
}

