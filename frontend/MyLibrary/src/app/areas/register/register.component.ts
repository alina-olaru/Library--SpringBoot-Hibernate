import { Router } from "@angular/router";
import { Subscription } from "rxjs";
import { LoadingService } from "./../../modules/loading-spinner/loading.service";
import { BookUser } from "./../../Models/BookUser";
import { Component, OnInit, OnDestroy } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ToastrService } from "../../services/toastr.service";
import { MustMatch } from "../../modules/validators/must-match";
import { RegisterService } from "./register.service";
import { error } from "protractor";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
//import { RegisterService } from '../register.service';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.scss"],
})
export class RegisterComponent implements OnInit, OnDestroy {
  registerForm: FormGroup;
  subscriptions: Subscription[] = [];

  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private registerService: RegisterService,
    private loading: LoadingService,
    private router: Router
  ) {}

  ngOnDestroy(): void {
    this.subscriptions.forEach((e) => e.unsubscribe());
  }

  ngOnInit() {
    this.registerForm = this.formBuilder.group(
      {
        firstName: ["", Validators.required],
        lastName: ["", Validators.required],
        username: ["", Validators.required],
        emailAdress: ["", [Validators.email, Validators.required]],
        phoneNumber: ["", Validators.required],
        password: ["", [Validators.required, Validators.minLength(5)]],
        checkpass: ["", [Validators.required, Validators.minLength(5)]],
        newsletter: [false],
      },
      {
        validator: MustMatch("password", "checkpass"),
      }
    );
  }

  get form() {
    return this.registerForm.controls;
  }

  submitRegister() {
    if (this.registerForm.valid) {
      let user: BookUser = new BookUser(this.registerForm.value);
      user.userPrivilege = true;

      this.loading.start();
      let subs = this.registerService.RegiserUser(user).subscribe(
        (response) => {
          this.loading.stop();
          if (response && response.status == ApiResponseType.SUCCESS) {
            this.toastr.Swal.fire({
              icon: "success",
              title: "Mail-ul pentru confirmare a fost trimis cu succes!",
              allowOutsideClick: false,
              allowEscapeKey: false,
            }).then((result) => {
              if (result.value) {
                this.router.navigate(["/login"]);
              }
            });
          } else {
            this.toastr.Toast.fire({
              icon: "error",
              title: response.message,
            });
          }
        },
        (error) => {
          this.loading.stop();
          this.toastr.Toast.fire({
            icon: "error",
            title: "A aparut o eroare!",
          });
        }
      );
      this.subscriptions.push(subs);
    }
  }
}
