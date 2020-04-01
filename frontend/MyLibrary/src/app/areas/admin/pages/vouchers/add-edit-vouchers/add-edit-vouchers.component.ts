import { Component, OnInit, Inject } from "@angular/core";
import { Author } from "src/app/Models/admin/AuthorModel";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Voucher } from 'src/app/Models/admin/VoucherModel';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { VoucherService} from ".././voucher.service";
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ToastrService } from 'src/app/services/toastr.service';
interface Data {
  type: string;
  model: Voucher;
}




@Component({
  selector: 'app-add-edit-vouchers',
  templateUrl: './add-edit-vouchers.component.html',
  styleUrls: ['./add-edit-vouchers.component.scss']
})
export class AddEditVouchersComponent implements OnInit {
  localForm: FormGroup;

  myControl = new FormControl();
  fileName: string;
  minDate: Date;
  maxDate: Date;
  vouchers:Voucher[];
  fd:Date;
  lD:Date;

  constructor(
    public dialogRef: MatDialogRef<AddEditVouchersComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private voucherService : VoucherService,
    private toastr: ToastrService
  ) {
    const currentYear = new Date().getFullYear();
    const currentDay = new Date().getDate();
    const currentMonth = new Date().getMonth();
    this.minDate = new Date(currentYear,currentMonth,currentDay);
    this.maxDate = new Date(currentYear ,currentMonth+6,currentDay);
  }


  ngOnInit(): void {
    this.GetVoucher();
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Voucher();
  }

    this.localForm = this.formBuilder.group({
    voucherId: [{ value: this.data.model.voucherId, disabled: true }],
    voucherTitle: [this.data.model.voucherTitle, Validators.required],
    voucherDescription: [this.data.model.voucherDescription],
    voucherMaximumUses: [this.data.model.voucherMaximumUses, Validators.required],
    voucherPrice: [this.data.model.voucherPrice, Validators.required],
    voucherStartDate: [this.data.model.voucherStartDate, Validators.required],
    voucherEndDate: [this.data.model.voucherStartDate, Validators.required]

  });

}
get form() {
  return this.localForm.controls;
}



onNoClick(): void {
  this.dialogRef.close();
}


SubmitForm() {
  console.log(this.localForm);
  if (this.localForm.valid) {
    console.log(this.localForm.value);
    let model: Voucher = new Voucher(this.localForm.value);
    model.voucherId = this.data.model.voucherId;
    this.dialogRef.close(model);
  }
}




GetVoucher(){



  this.voucherService
  .GetVouchers()
  .subscribe((response : ApiResponse<Voucher[]>) => {


    if(response && response.status==ApiResponseType.SUCCESS){

      if(response.body.length==0){
        this.toastr.Toast.fire({
          icon: 'info',
          title: 'Nu exista vouchere in baza de date'
        });
      }
      this.vouchers=response.body;


    }

  });


}


}
