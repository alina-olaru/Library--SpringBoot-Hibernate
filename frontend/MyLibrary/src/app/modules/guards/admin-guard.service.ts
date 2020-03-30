import { LoginService } from './../../areas/login/login.service';
import { Injectable } from '@angular/core';
import { CanActivate, CanActivateChild, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminGuardService implements CanActivate, CanActivateChild {
  constructor(private auth: LoginService, private router: Router) {}

  canActivateChild(
    childRoute: import('@angular/router').ActivatedRouteSnapshot,
    state: import('@angular/router').RouterStateSnapshot
  ):
    | boolean
    | import('@angular/router').UrlTree
    | import('rxjs').Observable<boolean | import('@angular/router').UrlTree>
    | Promise<boolean | import('@angular/router').UrlTree> {
    if (this.auth.getUser() && this.auth.getUser().adminPrivilege == true) {
      return true;
    }
    this.router.navigate(['/login']);
    return false;
  }
  canActivate(
    route: import('@angular/router').ActivatedRouteSnapshot,
    state: import('@angular/router').RouterStateSnapshot
  ):
    | boolean
    | import('@angular/router').UrlTree
    | import('rxjs').Observable<boolean | import('@angular/router').UrlTree>
    | Promise<boolean | import('@angular/router').UrlTree> {
      if (this.auth.getUser() && this.auth.getUser().adminPrivilege == true) {
        return true;
      }
      this.router.navigate(['/login']);
      return false;
  }
}
