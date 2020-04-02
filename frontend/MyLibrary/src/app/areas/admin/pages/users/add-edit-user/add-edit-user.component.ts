import { BookUser } from './../../../../../Models/BookUser';
import { Component, OnInit, Inject } from "@angular/core";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

interface Data {
  type: string;
  model: BookUser;
}


@Component({
  selector: "app-add-edit-user",
  templateUrl: "./add-edit-user.component.html",
  styleUrls: ["./add-edit-user.component.scss"]
})

export class AddEditUserComponent implements OnInit {
  localForm: FormGroup;

  myControl = new FormControl();
  fileName: string;

  constructor(
    public dialogRef: MatDialogRef<AddEditUserComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new BookUser();
    }
    this.localForm = this.formBuilder.group({
      userId: [{ value: this.data.model.userId, disabled: true }],
      firstName: [this.data.model.firstName, Validators.required],
      lastName: [this.data.model.lastName, Validators.required],
      emailAdress: [this.data.model.emailAdress, Validators.required],
      phoneNumber: [this.data.model.phoneNumber, Validators.required],
      newsletter: [this.data.model.newsletter, Validators.required],
      userPrivilege: [this.data.model.userPrivilege, Validators.required],
      adminPrivilege: [this.data.model.adminPrivilege, Validators.required],
      blocked: [this.data.model.blocked, Validators.required],
      password: [this.data.model.password, Validators.required],
      username: [this.data.model.username, Validators.required],



    });
  }

  get form() {
    return this.localForm.controls;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  DeleteFile() {
    this.fileName = null;
    this.data.model.firstName = null;
    this.data.model.lastName = null;
  }

  SubmitForm() {
    if (this.localForm.valid) {
      console.log(this.localForm.value);
      let model: BookUser = new BookUser(this.localForm.value);
      model.userId = this.data.model.userId;
      this.dialogRef.close(model);
    }
  }
}
