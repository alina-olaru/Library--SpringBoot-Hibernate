import { LoginService } from './../../areas/login/login.service';
import { HttpInterceptor } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable()
export class ErrorInterceptorService implements HttpInterceptor {

  constructor(private auth: LoginService) { }
  intercept(req: import("@angular/common/http").HttpRequest<any>, next: import("@angular/common/http").HttpHandler): import("rxjs").Observable<import("@angular/common/http").HttpEvent<any>> {
    return next.handle(req).pipe(catchError(err => {
      if (err.status === 401) {
          // auto logout if 401 response returned from api
          this.auth.logOutUser();
          location.reload(true);
      }


      if(err.error!=undefined){
      const error = err.error.message || err.statusText;
      return throwError(error);
      }
      return throwError("eroare");
  }))
  }
}
