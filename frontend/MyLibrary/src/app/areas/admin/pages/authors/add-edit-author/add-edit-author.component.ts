import { Component, OnInit, Inject } from "@angular/core";
import { Author } from "src/app/Models/admin/AuthorModel";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

interface Data {
  type: string;
  model: Author;
}

@Component({
  selector: "app-add-edit-author",
  templateUrl: "./add-edit-author.component.html",
  styleUrls: ["./add-edit-author.component.scss"]
})
export class AddEditAuthorComponent implements OnInit {
  localForm: FormGroup;
  myControl = new FormControl();
  fileName: string;

  constructor(
    public dialogRef: MatDialogRef<AddEditAuthorComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Author();
    }
    this.localForm = this.formBuilder.group({
      authorId: [{ value: this.data.model.authorId, disabled: true }],
      firstName: [this.data.model.firstName, Validators.required],
      lastName: [this.data.model.lastName, Validators.required]
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
      let model: Author = new Author(this.localForm.value);
      model.authorId = this.data.model.authorId;
      this.dialogRef.close(model);
    }
  }
}
