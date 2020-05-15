
import { Injectable, Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { Observable } from 'rxjs';
import { Quizz } from 'src/app/Models/admin/QuizzModel';


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
       this.baseUrl="/api/admin/Quizz";

     }




  GetQuizzez() {
    return this.http.get<ApiResponse<Quizz[]>>(
      this.globalVarService.globalUrl + this.baseUrl
    );
  }

  DeleteQuizzez(id: number): Observable<ApiResponse<boolean>> {
    return this.http.delete<ApiResponse<boolean>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id
    );
  }

  AddQuizzez(quizz: Quizz): Observable<ApiResponse<Quizz>> {
    return this.http.post<ApiResponse<Quizz>>(
      this.globalVarService.globalUrl + this.baseUrl,
      quizz
    );
  }

  UpdateQuizzez(publisher:Quizz ,id: number): Observable<ApiResponse<Quizz>> {
    return this.http.put<ApiResponse<Quizz>>(
      this.globalVarService.globalUrl + this.baseUrl + "/" + id,
      publisher
    );
  }
}

