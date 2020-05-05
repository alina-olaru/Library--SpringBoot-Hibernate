import { ForgetPasswordService } from './forget-password.service';
import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GlobalVarService } from 'src/app/services/global-var.service';
import { CookieService } from 'ngx-cookie-service';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-forget-password',
  templateUrl: './forget-password.component.html',
  styleUrls: ['./forget-password.component.scss']
})
export class ForgetPasswordComponent implements OnInit {

  forgetPasswordForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private httpClient: HttpClient,
    private gloablVarService: GlobalVarService,
    private cookieService: CookieService,
    private router: Router,
    private toastr: ToastrService,
    private formBuilder: FormBuilder,
    private forgetPasswordService:ForgetPasswordService

  ) { }

  ngOnInit(): void {

    this.forgetPasswordForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]

  });

}

get form() {
  return this.forgetPasswordForm.controls;
}


submitRegister() {
  console.log(11);
  if (this.forgetPasswordForm.valid) {
    this.forgetPasswordService.SendMailForPassword(this.forgetPasswordForm.controls["email"].value).subscribe((response => {

      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Swal.fire({
          icon: "success",
          title: "Mail-ul pentru modificarea parolei a fost trimis cu succes!",
          allowOutsideClick: false,
          allowEscapeKey: false,
        }).then((result) => {
          if (result.value) {
            this.router.navigate(["/login"]);
          }
        });
      }
           else {
            this.toastr.Toast.fire({
              icon: "error",
              title: response.message,
            });
          }




    }

    ))

  }else{
    console.log(22);
  }

}
}

