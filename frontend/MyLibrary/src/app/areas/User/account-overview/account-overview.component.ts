import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { BookUser } from 'src/app/Models/BookUser';

@Component({
  selector: 'app-account-overview',
  templateUrl: './account-overview.component.html',
  styleUrls: ['./account-overview.component.scss']
})
export class AccountOverviewComponent implements OnInit {


  public user: BookUser = null;
  private _token: String = null;
  public yesNewsletter:Boolean=false;

  constructor(


    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService

)

  {
    let cachedUser = this.cookieService.get("auth-user-info");
    if (cachedUser != null && cachedUser != "") {
      this.user = JSON.parse(cachedUser) as BookUser;
      console.log(this.user);
    }

    let cachedToken = this.cookieService.get("auth-token");
    if (cachedToken != null && cachedToken != "") {
      this._token = JSON.parse(cachedToken);
    }

    if(this.user.newsletter==true){
      this.yesNewsletter=true;
    }


   }

  ngOnInit(): void {
  }


  GetUser(){


  }
}
