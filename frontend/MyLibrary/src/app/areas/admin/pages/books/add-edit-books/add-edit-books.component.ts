import { BooksCategories } from "./../../../../../Models/admin/BooksCategoriesModel";
import { BooksAuthors } from "./../../../../../Models/admin/BooksAuthorsModel";
import { ToastrService } from "src/app/services/toastr.service";
import { Router } from "@angular/router";

import { Component, OnInit, Inject } from "@angular/core";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { Category } from "src/app/Models/admin/CategoryModel";
import { Book } from "src/app/Models/admin/BookModel";
import { AuthorsService } from "../../authors/authors.service";
import { CategoryService } from "../../category/category.service";
import { PublishersService } from "../../publishers/publishers.service";
import { Author } from "src/app/Models/admin/AuthorModel";
import { Publisher } from "src/app/Models/admin/PublisherModel";
import { Subscription, Observable } from "rxjs";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { startWith, map } from "rxjs/operators";
import { MatAutocompleteSelectedEvent } from "@angular/material/autocomplete";
import { MatChipInputEvent } from "@angular/material/chips";

interface Data {
  type: string;
  model: Book;
  authors: Author[];
  publishers: Publisher[];
  categories: Category[];
}

@Component({
  selector: "app-add-edit-books",
  templateUrl: "./add-edit-books.component.html",
  styleUrls: ["./add-edit-books.component.scss"]
})
export class AddEditBooksComponent implements OnInit {
  localForm: FormGroup;
  fileName: string;
  base64: string;
  selectable: boolean = true;
  removable: boolean = true;
  selectedAuthors: Author[] = [];
  filteredOptionsAuthors: Observable<Author[]>;
  categories: Category[] = [];
  selectedCategories: Category[] = [];
  filteredOptionsCategories: Observable<Category[]>;

  subscriptions: Subscription[] = [];

  dropdownSelectedPublisher: Publisher;
  filteredOptionsPublisher: Observable<Publisher[]>;

  constructor(
    public dialogRef: MatDialogRef<AddEditBooksComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private authorsService: AuthorsService,
    private categoryService: CategoryService,
    private publishersService: PublishersService,
    private toastr: ToastrService
  ) {
    this.categories = data.categories;
    this.dropdownSelectedPublisher = this.data.model?.publisher;
    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Book();
    } else {
      this.dropdownSelectedPublisher = this.data.model.publisher;
      this.selectedAuthors = this.data.model.bookAuthor.map(e => e.authorId);
      this.selectedCategories = this.data.model.booksCategories.map(
        e => e.categories
      );
    }
    this.localForm = this.formBuilder.group({
      /*







        */
      bookId: [{ value: this.data.model.bookId, disabled: true }],
      bookTitle: [this.data.model.bookTitle, Validators.required],
      bookLanguage: [this.data.model.bookLanguage],
      bookPrice: [this.data.model.bookPrice, Validators.required],
      bookWeight: [this.data.model.bookWeight],
      bookYear: [this.data.model.bookTitle],
      numberOfPages: [this.data.model.numberOfPages],
      numberofVolumes: [this.data.model.numberofVolumes],
      bookDescription: [this.data.model.bookDescription],
      bookDimension: [this.data.model.bookDimension],
      numberOfBoooks: [this.data.model.numberOfBoooks, Validators.required],
      publisher: [this.dropdownSelectedPublisher, Validators.required],
      local_autori: [this.selectedAuthors],
      local_categories: [this.selectedCategories],
      coverType: [this.data.model.coverType]
    });

    this.ConstructFilterOptionsPublishers();
    this.ConstructFilterOptionsAutori();
    this.ConstructFilterOptionsCategories();
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(e => {
      e.unsubscribe();
    });
  }

  ngOnInit(): void {}

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
      model.bookAuthor = this.selectedAuthors.map(
        e => <BooksAuthors>{ authorId: Object.assign({},e), bookId: Object.assign({}, model) }
      );
      model.booksCategories = this.selectedCategories.map(
        e => <BooksCategories>{ categories: Object.assign({},e), booksC: Object.assign({}, model) }
      );
      model.bookImage = this.base64
        ? this.base64.replace(/^data:image\/[a-z]+;base64,/, "")
        : null;
      model.bookImageSrc = this.base64;
      this.dialogRef.close(model);
    }
  }

  displayFnPublisher(tip?: Publisher): string | undefined {
    return tip ? tip.publisherTitle : undefined;
  }

  private _filterPublisher(name: string): Publisher[] {
    const filterValue = name.toLowerCase();

    return this.data.publishers.filter(
      option => option.publisherTitle.toLowerCase().indexOf(filterValue) === 0
    );
  }

  getAuthors() {
    let autSubscriber = this.authorsService.GetAuthors().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.data.authors = response.body;
      }
    });
    this.subscriptions.push(autSubscriber);
  }

  getPublishers() {
    let pubSubscriber = this.publishersService
      .GetPublishers()
      .subscribe(response => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.data.publishers = response.body;
        }
      });
    this.subscriptions.push(pubSubscriber);
  }

  getCategories() {
    let catSubscriber = this.categoryService
      .GetCategory()
      .subscribe(response => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.categories = response.body;
        }
      });
    this.subscriptions.push(catSubscriber);
  }

  RefreshPublishers() {
    this.publishersService.GetPublishers().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.data.publishers = response.body;
        this.ConstructFilterOptionsPublishers();
        this.toastr.Toast.fire({
          icon: "success",
          title: "Datele au fost actualizate cu succes!"
        });
      } else {
        this.toastr.Toast.fire({
          icon: "error",
          title: "A aparut o eroare la preluarea datelor depsre edituri!"
        });
      }
    });
  }

  RefreshAuthors() {
    this.authorsService.GetAuthors().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.data.authors = response.body;
        this.selectedAuthors = this.selectedAuthors.filter(
          e => this.data.authors.map(z => z.authorId).indexOf(e.authorId) >= 0
        );
        this.data.authors = this.data.authors.filter(
          e => this.selectedAuthors.map(z => z.authorId).indexOf(e.authorId) < 0
        );
        this.ConstructFilterOptionsAutori();
        this.toastr.Toast.fire({
          icon: "success",
          title: "Datele au fost actualizate cu succes!"
        });
      } else {
        this.toastr.Toast.fire({
          icon: "error",
          title: "A aparut o eroare la preluarea datelor depsre autori!"
        });
      }
    });
  }

  RefreshCategories() {
    this.categoryService.GetCategory().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.data.categories = response.body;
        this.selectedCategories = this.selectedCategories.filter(
          e =>
            this.data.categories.map(z => z.categoryId).indexOf(e.categoryId) >=
            0
        );
        this.data.categories = this.data.categories.filter(
          e =>
            this.selectedCategories
              .map(z => z.categoryId)
              .indexOf(e.categoryId) < 0
        );
        this.ConstructFilterOptionsCategories();
        this.toastr.Toast.fire({
          icon: "success",
          title: "Datele au fost actualizate cu succes!"
        });
      } else {
        this.toastr.Toast.fire({
          icon: "error",
          title: "A aparut o eroare la preluarea datelor depsre categorii!"
        });
      }
    });
  }

  ConstructFilterOptionsPublishers() {
    this.filteredOptionsPublisher = this.localForm.controls[
      "publisher"
    ].valueChanges.pipe(
      startWith(""),
      map(value => (typeof value === "string" ? value : value.name)),
      map(name =>
        name ? this._filterPublisher(name) : this.data.publishers.slice()
      )
    );
  }

  RemoveAutor(author: Author) {
    const index = this.selectedAuthors.indexOf(author);

    if (index >= 0) {
      this.data.authors.unshift(this.selectedAuthors[index]);
      this.selectedAuthors.splice(index, 1);
      this.localForm.controls["local_autori"].setValue("");
    }
  }

  RemoveCategory(category: Category) {
    const index = this.selectedCategories.indexOf(category);

    if (index >= 0) {
      this.data.categories.unshift(this.selectedCategories[index]);
      this.selectedCategories.splice(index, 1);
      this.localForm.controls["local_categories"].setValue("");
    }
  }

  ConstructFilterOptionsAutori() {
    this.filteredOptionsAuthors = this.localForm.controls[
      "local_autori"
    ].valueChanges.pipe(
      startWith(""),
      map(value => (typeof value === "string" ? value : value.name)),
      map(name =>
        name ? this._filterAuthors(name) : this.data.authors.slice()
      )
    );
  }

  ConstructFilterOptionsCategories() {
    this.filteredOptionsCategories = this.localForm.controls[
      "local_categories"
    ].valueChanges.pipe(
      startWith(""),
      map(value => (typeof value === "string" ? value : value.name)),
      map(name =>
        name ? this._filterCategories(name) : this.data.categories.slice()
      )
    );
  }

  private _filterAuthors(name: string): Author[] {
    const filterValue = name.toLowerCase();

    return this.data.authors.filter(
      option =>
        option.firstName.toLowerCase().indexOf(filterValue) === 0 ||
        option.lastName.toLowerCase().indexOf(filterValue) === 0
    );
  }

  private _filterCategories(name: string): Category[] {
    const filterValue = name.toLowerCase();

    return this.data.categories.filter(
      option => option.categoryTitle.toLowerCase().indexOf(filterValue) === 0
    );
  }

  selectedAuthor(
    event: MatAutocompleteSelectedEvent,
    ele: HTMLInputElement
  ): void {
    ele.value = "";
    ele.blur();
    this.selectedAuthors.push(event.option.value);
    let index = this.data.authors.indexOf(event.option.value);
    this.data.authors.splice(index, 1);
    this.localForm.controls["local_autori"].setValue("");
  }

  selectedCategory(
    event: MatAutocompleteSelectedEvent,
    ele: HTMLInputElement
  ): void {
    ele.value = "";
    ele.blur();
    this.selectedCategories.push(event.option.value);
    let index = this.data.categories.indexOf(event.option.value);
    this.data.categories.splice(index, 1);
    this.localForm.controls["local_categories"].setValue("");
  }

  UploadFile() {
    const fileUpload = document.getElementById(
      "modal-file-upload-input"
    ) as HTMLInputElement;
    fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
        const file = fileUpload.files[index];
        var myReader: FileReader = new FileReader();
        this.fileName = file.name;
        myReader.onloadend = e => {
          console.log(myReader.result);
          this.data.model.bookImage = <string>myReader.result;
          this.data.model.bookImageSrc = myReader.result;
          this.base64 = <string>myReader.result;
        };
        myReader.readAsDataURL(file);
      }
    };
    fileUpload.click();
  }

  DeleteFile() {
    this.fileName = null;
    this.data.model.bookImage = null;
    this.data.model.bookImageSrc = null;
    this.base64 = null;
  }
}
