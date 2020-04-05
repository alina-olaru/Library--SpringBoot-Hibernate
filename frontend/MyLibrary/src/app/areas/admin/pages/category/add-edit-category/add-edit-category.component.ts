
import { Component, OnInit, Inject } from "@angular/core";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Category } from 'src/app/Models/admin/CategoryModel';


interface Data {
  type: string;
  model: Category;
}


@Component({
  selector: 'app-add-edit-category',
  templateUrl: './add-edit-category.component.html',
  styleUrls: ['./add-edit-category.component.scss']
})
export class AddEditCategoryComponent implements OnInit {
  localForm: FormGroup;
  myControl = new FormControl();
  fileName: string;

  constructor(
    public dialogRef: MatDialogRef<AddEditCategoryComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Category();
    }
    this.localForm = this.formBuilder.group({
      categoryId: [{ value: this.data.model.categoryId, disabled: true }],
      categoryTitle: [this.data.model.categoryTitle, Validators.required],
      categoryDescription: [this.data.model.categoryDescription]
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
      let model: Category = new Category(this.localForm.value);
      model.categoryId = this.data.model.categoryId;
      this.dialogRef.close(model);
    }
  }
}
