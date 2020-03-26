import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from '../../../services/toastr.service';
import { MustMatch } from '../../../moduls/material/validators/must-match';
//import { RegisterService } from '../register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;

  constructor
  (
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
   // private registerService: RegisterService

  ) { }

  ngOnInit() {

    this.registerForm=this.formBuilder.group({

      firstName: ['', Validators.required],
      // tslint:disable-next-line: ban-types
      lastName: ['', Validators.required],
      username: ['', Validators.required],
      emailAdress: ['', [Validators.email,Validators.required]],
      phoneNumber: ['', Validators.required],
      country: ['', Validators.required],
      province: ['', Validators.required],
      city: ['', Validators.required],
      streetName: ['', Validators.required],
      streetNumber: ['', Validators.required],
      block: ['', Validators.required],
      floor: ['', Validators.required],
      password: ['', Validators.required],
      checkpass: ['', [Validators.required,Validators.minLength(5)]]



    },
{
    validator:MustMatch('password','checkpass')
}

    );
  }



  get form(){
    return this.registerForm.controls;
  }


  submitRegister(){
    // TODO termina de completat
  }

}
