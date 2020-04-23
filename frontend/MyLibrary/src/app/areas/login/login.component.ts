import { JwtResponse } from './../../Models/general/jwt-response';
import { JwtRequest } from './../../Models/general/jwt-request';
import { Component, OnInit } from '@angular/core';
import { BookUser } from 'src/app/Models/BookUser';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from './login.service';
import { ToastrService } from 'src/app/services/toastr.service';
import { CookieService } from 'ngx-cookie-service';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService,
    private cookieService: CookieService
  ) {}

  ngOnInit() {
    if (this.loginService.getUser() != null) {
      this.redirectToPage(this.loginService.getUser());
    }

    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  get form() {
    return this.loginForm.controls;
  }

  onSubmit() {
    if (this.loginForm.valid) {
      const reqeust: JwtRequest = new JwtRequest(this.loginForm.value);
      this.loginService
        .loginUser(reqeust)
        .subscribe((response: ApiResponse<JwtResponse>) => {
          if (response && response.status == ApiResponseType.SUCCESS) {
            this.toastr.Toast.fire({
              icon: 'success',
              title: 'Autentificare cu succes!'
            });
            this.redirectToPage(response.body.bookUser);
          } else {
            this.toastr.Toast.fire({
              icon: 'error',
              title: 'Utilizator sau parola incorecte'
            });
          }
        });
    }

    // this.submitted = true;

    // if (this.loginForm.invalid) {
    //   console.log('invalid');
    //   return;
    // }
    // this.loading = true;
    // auth service
  }

  redirectToPage(user: BookUser) {
    if (user.adminPrivilege == true) {
      this.router.navigate(['admin']);
    }
    else {
      this.router.navigate(['/cont/accountOverview']);
    }
  }
}
