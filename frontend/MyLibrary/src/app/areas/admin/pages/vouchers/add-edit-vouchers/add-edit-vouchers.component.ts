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
import { AuthorsService } from '../../authors/authors.service';
import { CategoryService } from '../../category/category.service';
import { PublishersService } from '../../publishers/publishers.service';
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { startWith, map } from 'rxjs/operators';
import * as _ from 'lodash';
interface Data {
  type: string;
  model: Voucher;
  authors: Author[];
  publishers: Publisher[];
  categories: Category[];
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

  dropdownSelectedAuthor: Author;
  filteredOptionsAuthor: Observable<Author[]>;

  dropdownSelectedCategory: Category;
  filteredOptionsCategory: Observable<Category[]>;

  subscriptions: Subscription[] = [];

  dropdownSelectedPublisher: Publisher;
  filteredOptionsPublisher: Observable<Publisher[]>;

  authors: Author[]=[];
  publishers: Publisher[]=[];
  categories: Category[] = [];




  constructor(
    public dialogRef: MatDialogRef<AddEditVouchersComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private voucherService: VoucherService,
    private authorsService: AuthorsService,
    private categoryService: CategoryService,
    private publishersService: PublishersService,
    private toastr: ToastrService
  ) {
    const currentYear = new Date().getFullYear();
    const currentDay = new Date().getDate();
    const currentMonth = new Date().getMonth();
    this.minDate = new Date(currentYear, currentMonth, currentDay);
    this.maxDate = new Date(currentYear, currentMonth + 6, currentDay);

    this.authors = _.cloneDeep(this.data.authors);
    this.publishers = _.cloneDeep(this.data.publishers);
    this.categories = _.cloneDeep(this.data.categories);

    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Voucher();
      this.dropdownSelectedPublisher = null;
      this.dropdownSelectedAuthor = null;
      this.dropdownSelectedCategory = null;
    } else {

      if(this.data.model.voucherImage)
      {
        this.fileName = this.data.model.voucherImage;
        this.base64 = this.data.model.voucherImageSrc;
      }

      this.dropdownSelectedAuthor = this.data.model.author_voucher;
      this.dropdownSelectedCategory = this.data.model.category_voucher;
      this.dropdownSelectedPublisher = this.data.model.publisher_voucher;
    }

  }

  ngOnInit(): void {

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
      language: [this.data.model.language],
      publisher_voucher: [this.dropdownSelectedPublisher],
      author_voucher: [this.dropdownSelectedAuthor],
      category_voucher: [this.dropdownSelectedCategory]
    });


    if (this.data.type == "edit") {
      this.localForm.controls["voucherStartDate"].setValue(new Date(this.data.model.voucherStartDate));
      this.localForm.controls["voucherEndDate"].setValue(new Date(this.data.model.voucherEndDate));
    }


    this.ConstructFilterOptionsPublishers();
    this.ConstructFilterOptionsAuthors();
    this.ConstructFilterOptionsCategory();

  }

  get form() {
    return this.localForm.controls;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  SubmitForm() {
    if (this.localForm.valid) {
      let model: Voucher = new Voucher(this.localForm.value);
      model.voucherId = this.data.model.voucherId;
      model.voucherImage = this.base64
      ? this.base64.replace(/^data:image\/[a-z]+;base64,/, '')
      : null;
      model.voucherImageSrc = this.base64;
      this.dialogRef.close(model);
    }
  }

  displayFnPublisher(tip?: Publisher): string | undefined {
    return tip ? tip.publisherTitle : undefined;
  }

  private _filterPublisher(name: string): Publisher[] {
    const filterValue = name.toLowerCase();

    return this.publishers.filter(
      option => option.publisherTitle.toLowerCase().indexOf(filterValue) === 0
    );
  }


  ConstructFilterOptionsPublishers() {
    this.filteredOptionsPublisher = this.localForm.controls[
      'publisher_voucher'
    ].valueChanges.pipe(
      startWith(''),
      map(value => (typeof value === 'string' ? value : value.name)),
      map(name =>
        name ? this._filterPublisher(name) : this.publishers.slice()
      )
    );
  }

  displayFnAuthor(tip?: Author): string | undefined {
    return tip ? tip.firstName + " " + tip.lastName : undefined;
  }

  private _filterAuthor(name: string): Author[] {
    const filterValue = name.toLowerCase();

    return this.authors.filter(
      option =>
        option.firstName.toLowerCase().indexOf(filterValue) === 0 ||
        option.lastName.toLowerCase().indexOf(filterValue) === 0
    );
  }


  ConstructFilterOptionsAuthors() {
    this.filteredOptionsAuthor = this.localForm.controls[
      'author_voucher'
    ].valueChanges.pipe(
      startWith(''),
      map(value => (typeof value === 'string' ? value : value.name)),
      map(name =>
        name ? this._filterAuthor(name) : this.authors.slice()
      )
    );
  }

  displayFnCategory(tip?: Category): string | undefined {
    return tip ? tip.categoryTitle : undefined;
  }

  private _filterCategory(name: string): Category[] {
    const filterValue = name.toLowerCase();

    return this.categories.filter(
      option => option.categoryTitle.toLowerCase().indexOf(filterValue) === 0
    );
  }


  ConstructFilterOptionsCategory() {
    this.filteredOptionsCategory = this.localForm.controls[
      'category_voucher'
    ].valueChanges.pipe(
      startWith(''),
      map(value => (typeof value === 'string' ? value : value.name)),
      map(name =>
        name ? this._filterCategory(name) : this.categories.slice()
      )
    );
  }

  UploadFile() {
    const fileUpload = document.getElementById(
      'modal-file-upload-input'
    ) as HTMLInputElement;
    fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
        const file = fileUpload.files[index];
        var myReader: FileReader = new FileReader();
        this.fileName = file.name;
        myReader.onloadend = e => {
          console.log(myReader.result);
          this.data.model.voucherImage = <string>myReader.result;
          this.data.model.voucherImageSrc = myReader.result;
          this.base64 = <string>myReader.result;
        };
        myReader.readAsDataURL(file);
      }
    };
    fileUpload.click();
  }

  DeleteFile() {
    this.fileName = null;
    this.data.model.voucherImage = null;
    this.data.model.voucherImageSrc = null;
    this.base64 = null;
  }

}
