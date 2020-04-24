import { NewsletterServiceService } from './NewsletterService.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { BookUser } from 'src/app/Models/BookUser';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

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
    private toastr: ToastrService,
    private newsletterService:NewsletterServiceService

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


  YesToNews(){

    this.newsletterService.YesToNews().subscribe((response)=>
    {


    if (response && response.status == ApiResponseType.SUCCESS)
    {
      this.toastr.Swal.fire({
        icon: "success",
        title: "Abonare facuta cu succes!",
        allowOutsideClick: false,
        allowEscapeKey: false,
      });


  }
  else{
    this.toastr.Swal.fire({
      icon: "error",
      title: response.message,
      allowOutsideClick: false,
      allowEscapeKey: false,
    });
  }


},
error=>{
  this.toastr.Swal.fire({
    icon: "error",
    title:"A aparut o eroare",
    allowOutsideClick: false,
    allowEscapeKey: false,
  });

}
);
}

NoToNews(){


  this.newsletterService.NoToNews().subscribe((response)=>
  {


  if (response && response.status == ApiResponseType.SUCCESS)
  {
    this.toastr.Swal.fire({
      icon: "success",
      title: "Te-ai dezabonat cu succes!",
      allowOutsideClick: false,
      allowEscapeKey: false,
    });


}
else{
  this.toastr.Swal.fire({
    icon: "error",
    title: response.message,
    allowOutsideClick: false,
    allowEscapeKey: false,
  });
}


},
error=>{
this.toastr.Swal.fire({
  icon: "error",
  title:"A aparut o eroare",
  allowOutsideClick: false,
  allowEscapeKey: false,
});

}
);
}
}
