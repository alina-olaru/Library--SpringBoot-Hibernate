import { AddressServiceService } from './../AddressService.service';
import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'src/app/services/toastr.service';
import { Address } from 'src/app/Models/Address';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CookieService } from 'ngx-cookie-service';
import { BookUser } from 'src/app/Models/BookUser';



interface Data {
  type: string;
  model: Address;
}



@Component({
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.scss']
})
export class AddAddressComponent implements OnInit {

  localForm: FormGroup;
  private user: BookUser = null;
  private _token: String = null;

  constructor(
    public dialogRef: MatDialogRef<AddAddressComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private addAddress:AddressServiceService,
    private cookieService: CookieService,
  ) {


    let cachedUser = this.cookieService.get("auth-user-info");
    if (cachedUser != null && cachedUser != "") {
      this.user = JSON.parse(cachedUser) as BookUser;
    }

    let cachedToken = this.cookieService.get("auth-token");
    if (cachedToken != null && cachedToken != "")
     {
      this._token = JSON.parse(cachedToken);


   }
  }

  ngOnInit(): void
   {

    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Address();
    }

    this.localForm=this.formBuilder.group({
      addressId: [{ addressId: this.data.model.addressId, disabled: true }],
      country: [this.data.model.country, Validators.required],
      province: [this.data.model.province, Validators.required],
      city: [this.data.model.city, Validators.required],
      streetName: [this.data.model.streetName, Validators.required],
      streetNumber: [this.data.model.streetNumber, Validators.required],
      floor: [this.data.model.floor, Validators.required],
      block: [this.data.model.block, Validators.required],
      appartmentNumber: [this.data.model.appartmentNumber, Validators.required]


    });


  }

  get form() {
    return this.localForm.controls;
  }
  SubmitForm() {
    console.log(this.localForm);
    if (this.localForm.valid) {
      console.log(this.localForm.value);
      let model: Address = new Address(this.localForm.value);
      model.addressId = this.data.model.addressId;
      console.log(model+"hhhhhhhhhhhhhhhhhhhhhhhh");
      this.dialogRef.close(model);
    }
  }


}
