import { LoginService } from "../../areas/login/login.service";
import { Injectable } from "@angular/core";
import { HttpInterceptor } from "@angular/common/http";

@Injectable()
export class BearerAuthInterceptorService implements HttpInterceptor {
  constructor(private auth: LoginService) {}
  intercept(
    req: import("@angular/common/http").HttpRequest<any>,
    next: import("@angular/common/http").HttpHandler
  ): import("rxjs").Observable<import("@angular/common/http").HttpEvent<any>> {
    let token = this.auth.token;
    console.log(token);
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`
        }
      });
    }
    return next.handle(req);
  }
}
