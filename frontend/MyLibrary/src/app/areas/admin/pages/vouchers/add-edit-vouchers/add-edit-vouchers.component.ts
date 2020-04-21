import { Component, OnInit, Inject } from "@angular/core";
import { Author } from "src/app/Models/admin/AuthorModel";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators,
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Voucher } from "src/app/Models/admin/VoucherModel";
import { MatDatepickerModule } from "@angular/material/datepicker";
import { VoucherService } from ".././voucher.service";
import { ApiResponse } from "src/app/Models/general/api-response";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { ToastrService } from "src/app/services/toastr.service";
import { Observable, Subscription } from 'rxjs';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import { Category } from 'src/app/Models/admin/CategoryModel';
interface Data {
  type: string;
  model: Voucher;
}

@Component({
  selector: "app-add-edit-vouchers",
  templateUrl: "./add-edit-vouchers.component.html",
  styleUrls: ["./add-edit-vouchers.component.scss"],
})
export class AddEditVouchersComponent implements OnInit {
  localForm: FormGroup;

  myControl = new FormControl();
  fileName: string;
  minDate: Date;
  maxDate: Date;
  vouchers: Voucher[];
  fd: Date;
  lD: Date;
  base64: string;
  selectable: boolean = true;
  removable: boolean = true;




  constructor(
    public dialogRef: MatDialogRef<AddEditVouchersComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private voucherService: VoucherService,
    private toastr: ToastrService
  ) {
    const currentYear = new Date().getFullYear();
    const currentDay = new Date().getDate();
    const currentMonth = new Date().getMonth();
    this.minDate = new Date(currentYear, currentMonth, currentDay);
    this.maxDate = new Date(currentYear, currentMonth + 6, currentDay);
  }

  ngOnInit(): void {
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Voucher();
    }

    this.localForm = this.formBuilder.group({
      voucherId: [{ value: this.data.model.voucherId, disabled: true }],
      voucherTitle: [this.data.model.voucherTitle, Validators.required],
      voucherDescription: [this.data.model.voucherDescription],
      voucherMaximumUses: [
        this.data.model.voucherMaximumUses,
        Validators.required,
      ],
      voucherPrice: [this.data.model.voucherPrice, Validators.required],
      voucherStartDate: [this.data.model.voucherStartDate, Validators.required],
      voucherEndDate: [this.data.model.voucherEndDate, Validators.required],
      authorlastName: [this.data.model.voucherEndDate],
      language: [this.data.model.voucherEndDate],
      Publisher: [this.data.model.voucherEndDate],
    });


    if (this.data.type == "edit") {
      this.localForm.controls["voucherStartDate"].setValue(new Date(this.data.model.voucherStartDate));
      this.localForm.controls["voucherEndDate"].setValue(new Date(this.data.model.voucherEndDate));
    }
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

}
