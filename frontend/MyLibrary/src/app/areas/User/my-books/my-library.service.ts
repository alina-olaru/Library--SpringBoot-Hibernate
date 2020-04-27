import { Observable } from 'rxjs';
import { HttpParams, HttpHeaders } from '@angular/common/http';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MyLibraryService {

  Urll: string;
  constructor(
    private http: HttpClient
             )
   {
    this.Urll = "https://api.ocr.space/parse/image";
   }



   GetText() : Observable<any>{

    const body = new HttpParams()
    .set('url', 'https://static.cartepedia.ro/image/170894/brida-editia-produs_imagine.jpg')
    .set('language', 'eng')
    .set('scale', 'true')
    .set('isOverlayRequired', 'true')
    .set('detectOrientation', 'true')
    .set('isTable', 'false')
    .set('OCREngine', '2')
    .set('filetype','jpg')

    const headers = new HttpHeaders({
      apikey: "51c8a39e0888957"
    })


    return this.http.post<any>(this.Urll, body,{headers});
   }
}


