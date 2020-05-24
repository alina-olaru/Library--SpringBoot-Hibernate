import { LoginService } from './../../login/login.service';
import { NewsletterServiceService } from './NewsletterService.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { BookUser } from 'src/app/Models/BookUser';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { AddEditUserComponent } from '../../admin/pages/users/add-edit-user/add-edit-user.component';
import { UserService } from '../../admin/pages/users/user.service';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { MatDialog } from '@angular/material/dialog';

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
    private newsletterService:NewsletterServiceService,
    private authService: LoginService,
    public usersService: UserService,
    private loadingService: LoadingService,
    public dialog: MatDialog,


)
  {
  this.GetUser();

    if(this.user.newsletter==true){
      this.yesNewsletter=true;
    }


   }

  ngOnInit(): void {
  }


  GetUser(){
    this.user = this.authService.getUser();

  }


  YesToNews(){

    this.newsletterService.YesToNews().subscribe((response)=>
    {


    if (response && response.status == ApiResponseType.SUCCESS)
    {
      this.authService.updateUser(this.user);
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
this.GetUser();
}

NoToNews(){


  this.newsletterService.NoToNews().subscribe((response)=>
  {


  if (response && response.status == ApiResponseType.SUCCESS)
  {
    this.authService.updateUser(this.user);
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
this.GetUser();
}


edit(){
  const dialogRef = this.dialog.open(AddEditUserComponent, {
    width: '40&',
    data: {
      type: 'edit',
      model: Object.assign({}, this.user)
    }
  });

  dialogRef.afterClosed().subscribe(result => {
    if (result != undefined && result != null) {
      this.EditUserConfirm(result, this.user);
    }
  });
}

EditUserConfirm(newUser: BookUser, oldUser: BookUser) {
  this.loadingService.start();

  this.usersService
    .UpdateUser(newUser, newUser.userId)
    .subscribe((response: ApiResponse<BookUser>) => {
      this.loadingService.stop();
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.authService.updateUser(response.body);
        this.GetUser();
        this.toastr.Toast.fire({
          title: 'Userul a fost editat cu succes!',
          icon: 'success'
        });


      } else {
        this.toastr.Swal.fire(
          'Eroare!',
          'A aparut o eroare la editare, incearca din nou!',
          'error'
        );
      }
    });
}

}
