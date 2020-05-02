import { Injectable } from '@angular/core';
import { CanActivateChild, CanActivate, Router } from '@angular/router';
import { LoginService } from 'src/app/areas/login/login.service';

@Injectable({
  providedIn: 'root'
})
export class UserGuardService implements CanActivate, CanActivateChild {

  constructor(private auth: LoginService, private router: Router) {}
  canActivateChild(childRoute: import('@angular/router').ActivatedRouteSnapshot, state: import('@angular/router').RouterStateSnapshot): boolean | import('@angular/router').UrlTree | import('rxjs').Observable<boolean | import('@angular/router').UrlTree> | Promise<boolean | import('@angular/router').UrlTree> {
    if (this.auth.getUser() && this.auth.getUser().userPrivilege == true) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;

  }
  canActivate(route: import('@angular/router').ActivatedRouteSnapshot, state: import('@angular/router').RouterStateSnapshot): boolean | import('@angular/router').UrlTree | import('rxjs').Observable<boolean | import('@angular/router').UrlTree> | Promise<boolean | import('@angular/router').UrlTree> {
    if (this.auth.getUser() && this.auth.getUser().userPrivilege == true) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
