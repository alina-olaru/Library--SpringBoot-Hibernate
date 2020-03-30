import { Component, OnInit, Inject } from '@angular/core';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import {MatIconModule} from '@angular/material/icon';
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
  model: Publisher;
}



@Component({
  selector: 'app-add-edit-publisher',
  templateUrl: './add-edit-publisher.component.html',
  styleUrls: ['./add-edit-publisher.component.scss']
})
export class AddEditPublisherComponent implements OnInit {
  localForm: FormGroup;
  myControl = new FormControl();


  constructor(
    public dialogRef: MatDialogRef<AddEditPublisherComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Publisher();
    }
    this.localForm = this.formBuilder.group({
      authorId: [{ value: this.data.model.publisherId, disabled: true }],
      publisherTitle: [this.data.model.publisherTitle, Validators.required]

    });
  }

  get form() {
    return this.localForm.controls;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  DeleteFile() {

    this.data.model.publisherTitle = null;

  }

  SubmitForm() {
    if (this.localForm.valid) {
      console.log(this.localForm.value);
      let model: Publisher = new Publisher(this.localForm.value);
      model.publisherId = this.data.model.publisherId;
      this.dialogRef.close(model);
    }
  }
}
