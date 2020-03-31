

import { Component, OnInit, Inject } from "@angular/core";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Category } from 'src/app/Models/admin/CategoryModel';
import { Book } from 'src/app/Models/admin/BookModel';


interface Data {
  type: string;
  model: Book;
}



@Component({
  selector: 'app-add-edit-books',
  templateUrl: './add-edit-books.component.html',
  styleUrls: ['./add-edit-books.component.scss']
})
export class AddEditBooksComponent implements OnInit {
  localForm: FormGroup;
  myControl = new FormControl();
  fileName: string;

  constructor(
    public dialogRef: MatDialogRef<AddEditBooksComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder
  ) {}

  ngOnInit(): void {
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Book();
    }
    this.localForm = this.formBuilder.group({

      /*

        bookId:number;
  bookTitle:String;
  bookLanguage:String;
  bookYear:number;
  numberOfPages:number;
  numberofVolumes:number;
  bookDescription:String;
  bookDimension:String;
  bookWeight:number;
  bookPrice:number;
  coverType:String;
  numberOfReviews:number;
  bookRating:number;
  numberOfBoooks:number;
  publisher:Publisher;
  persB:any;
  wishBooks:any;
  items:any;
  booksCategories:Category
  bookAuthor:any;
  bookR:any;

      */
      bookId: [{ value: this.data.model.bookId, disabled: true }],
      bookTitle: [this.data.model.bookTitle, Validators.required],
      bookLanguage: [this.data.model.bookLanguage],

      bookYear: [this.data.model.bookTitle],
      numberOfPages: [this.data.model.numberOfPages],
      numberofVolumes: [this.data.model.numberofVolumes],
      bookDescription: [this.data.model.bookDescription],
      bookDimension: [this.data.model.bookDimension, Validators.required],
      publisher: [this.data.model.publisher.publisherTitle, Validators.required],
      bookPrice: [this.data.model.bookTitle, Validators.required],
      numberOfBoooks: [this.data.model.numberOfBoooks, Validators.required],
      booksCategories: [this.data.model.booksCategories.categoryTitle, Validators.required],
      bookAuthor: [this.data.model.bookAuthor.authorId.firstName, Validators.required]

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
      let model: Book = new Book(this.localForm.value);
      model.bookId = this.data.model.bookId;
      this.dialogRef.close(model);
    }
  }
}
