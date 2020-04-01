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


  constructor(
    public dialogRef: MatDialogRef<AddEditVouchersComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder
  ) { }

  ngOnInit(): void {

    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Voucher();
  }

    this.localForm = this.formBuilder.group({
    voucherId: [{ value: this.data.model.voucherId, disabled: true }],
    voucherTitle: [this.data.model.voucherTitle, Validators.required],
    voucherDescription: [this.data.model.voucherDescription],
    voucherMaximumUses: [this.data.model.voucherMaximumUses, Validators.required],
    voucherPrice: [this.data.model.voucherPrice, Validators.required]

  });

}
get form() {
  return this.localForm.controls;
}



onNoClick(): void {
  this.dialogRef.close();
}


SubmitForm() {
  if (this.localForm.valid) {
    console.log(this.localForm.value);
    let model: Voucher = new Voucher(this.localForm.value);
    model.voucherId = this.data.model.voucherId;
    this.dialogRef.close(model);
  }
}
}
