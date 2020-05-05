import { ApiResponse } from './../../Models/general/api-response';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';
import { ToastrService } from 'src/app/services/toastr.service';
import { ActivatedRoute } from '@angular/router';
import { RegisterService } from '../register/register.service';
import { ForgetPasswordService } from '../forget-password/forget-password.service';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { MustMatch } from 'src/app/modules/validators/must-match';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.scss']
})
export class ChangePasswordComponent implements OnInit {
  token: string = "";
  forgetPasswordForm: FormGroup;


  constructor(
               private registerService: RegisterService,
              private route: ActivatedRoute,
              private router:Router,
              private toastr: ToastrService,
              private cookieService: CookieService,
              private formBuilder: FormBuilder,
              private forgetPasswordService:ForgetPasswordService
  )
  {
  }

  ngOnInit(): void {

    this.forgetPasswordForm = this.formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(4)]],
      checkpass: ['', [Validators.required, Validators.minLength(4)]]

  },
  {
    validator: MustMatch("password", "checkpass"),
  }
  );



    this.route.queryParams.subscribe(params => {
      if(params != undefined)
      {
        this.token = params["token"];
      }

      if(!this.token){
        this.toastr.Toast.fire({
          icon: "error",
          title: "Token-ul este gol!"
        });
      }else
      {

      }
    });


    console.log("token "+this.token);
  }


  get form() {
    return this.forgetPasswordForm.controls;
  }
/**
 *Sending token and new password  , with unique token I find user and I'm changing the password.
 *
 *
 */
submitRegister(){
  if (this.forgetPasswordForm.valid) {
  this.forgetPasswordService.changePassword(this.token,this.forgetPasswordForm.controls["password"].value).subscribe((response:ApiResponse<Boolean>) => {

    if (response && response.status == ApiResponseType.SUCCESS) {
      this.toastr.Swal.fire({
        icon: "success",
        title: "Parola s-a schimbat.",
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
            title:"Eroare de sistem , contactati-ne!"
          });
        }




  }
  );


  }
  else{
    //popup de eroare
  }
}
}
