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
  selectedAuthors: Author[] = [];
  filteredOptionsAuthors: Observable<Author[]>;

  selectedCategories: Category[] = [];
  filteredOptionsCategories: Observable<Category[]>;

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


    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Voucher();
      this.dropdownSelectedPublisher = null;


    } else {
      this.dropdownSelectedPublisher = this.data.model.publisher_voucher;
      this.selectedAuthors = this.data.model.author_voucher;

      this.authors = this.authors.filter(e=>this.selectedAuthors.map(z=>z.authorId).indexOf(e.authorId) < 0);
      this.categories = this.categories.filter(e=>this.selectedCategories.map(z=>z.categoryId).indexOf(e.categoryId) < 0);
   ///   this.publishers = this.publishers.filter(e=>this.dropdownSelectedPublisher.map(z=>z.categoryId).indexOf(e.categoryId) < 0);

    }


    this.ConstructFilterOptionsPublishers();
    this.ConstructFilterOptionsAutori();
    this.ConstructFilterOptionsCategories();


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

  displayFnPublisher(tip?: Publisher): string | undefined {
    return tip ? tip.publisherTitle : undefined;
  }

  displayFnAuthor(tip?: Author): string | undefined {
    return tip ? tip.firstName : undefined;
  }

  private _filterPublisher(name: string): Publisher[] {
    const filterValue = name.toLowerCase();

    return this.publishers.filter(
      option => option.publisherTitle.toLowerCase().indexOf(filterValue) === 0
    );
  }



  RefreshPublishers() {
    this.publishersService.GetPublishers().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {

        this.publishers = response.body;
        this.data.model.publisher_voucher = _.cloneDeep(response.body);
        this.ConstructFilterOptionsPublishers();
        this.toastr.Toast.fire({
          icon: 'success',
          title: 'Datele au fost actualizate cu succes!'
        });
      } else {
        this.toastr.Toast.fire({
          icon: 'error',
          title: 'A aparut o eroare la preluarea datelor depsre edituri!'
        });
      }
    });
  }

  RefreshAuthors() {
    this.authorsService.GetAuthors().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {

        this.authors = response.body;
        this.data.model.author_voucher = _.cloneDeep(response.body);

        this.selectedAuthors = this.selectedAuthors.filter(
          e => this.authors.map(z => z.authorId).indexOf(e.authorId) >= 0
        );
        this.authors = this.authors.filter(
          e => this.selectedAuthors.map(z => z.authorId).indexOf(e.authorId) < 0
        );
        this.ConstructFilterOptionsAutori();
        this.toastr.Toast.fire({
          icon: 'success',
          title: 'Datele au fost actualizate cu succes!'
        });
      } else {
        this.toastr.Toast.fire({
          icon: 'error',
          title: 'A aparut o eroare la preluarea datelor depsre autori!'
        });
      }
    });
  }

  RefreshCategories() {
    this.categoryService.GetCategory().subscribe(response => {
      if (response && response.status == ApiResponseType.SUCCESS) {
      //  this.data.categories = _.cloneDeep(response.body);
        this.categories = response.body;
        this.selectedCategories = this.selectedCategories.filter(
          e =>
            this.categories.map(z => z.categoryId).indexOf(e.categoryId) >=
            0
        );
        this.categories = this.categories.filter(
          e =>
            this.selectedCategories
              .map(z => z.categoryId)
              .indexOf(e.categoryId) < 0
        );
        this.ConstructFilterOptionsCategories();
        this.toastr.Toast.fire({
          icon: 'success',
          title: 'Datele au fost actualizate cu succes!'
        });
      } else {
        this.toastr.Toast.fire({
          icon: 'error',
          title: 'A aparut o eroare la preluarea datelor depsre categorii!'
        });
      }
    });
  }



  ConstructFilterOptionsPublishers() {
    this.filteredOptionsPublisher = this.localForm.controls[
      'publisher'
    ].valueChanges.pipe(
      startWith(''),
      map(value => (typeof value === 'string' ? value : value.name)),
      map(name =>
        name ? this._filterPublisher(name) : this.publishers.slice()
      )
    );
  }

  RemoveAutor(author: Author) {
    const index = this.selectedAuthors.indexOf(author);

    if (index >= 0) {
      this.authors.unshift(this.selectedAuthors[index]);
      this.selectedAuthors.splice(index, 1);
      this.localForm.controls['local_autori'].setValue('');
    }
  }

  RemoveCategory(category: Category) {
    const index = this.selectedCategories.indexOf(category);

    if (index >= 0) {
      this.categories.unshift(this.selectedCategories[index]);
      this.selectedCategories.splice(index, 1);
      this.localForm.controls['local_categories'].setValue('');
    }
  }

  ConstructFilterOptionsAutori() {
    this.filteredOptionsAuthors = this.localForm.controls[
      'local_autori'
    ].valueChanges.pipe(
      startWith(''),
      map(value => (typeof value === 'string' ? value : value.name)),
      map(name =>
        name ? this._filterAuthors(name) : this.authors.slice()
      )
    );
  }

  ConstructFilterOptionsCategories() {
    this.filteredOptionsCategories = this.localForm.controls[
      'local_categories'
    ].valueChanges.pipe(
      startWith(''),
      map(value => (typeof value === 'string' ? value : value.name)),
      map(name =>
        name ? this._filterCategories(name) : this.categories.slice()
      )
    );
  }

  private _filterAuthors(name: string): Author[] {
    const filterValue = name.toLowerCase();

    return this.authors.filter(
      option =>
        option.firstName.toLowerCase().indexOf(filterValue) === 0 ||
        option.lastName.toLowerCase().indexOf(filterValue) === 0
    );
  }

  private _filterCategories(name: string): Category[] {
    const filterValue = name.toLowerCase();

    return this.categories.filter(
      option => option.categoryTitle.toLowerCase().indexOf(filterValue) === 0
    );
  }

  selectedAuthor(
    event: MatAutocompleteSelectedEvent,
    ele: HTMLInputElement
  ): void {
    ele.value = '';
    ele.blur();
    this.selectedAuthors.push(event.option.value);
    let index = this.authors.indexOf(event.option.value);
    this.authors.splice(index, 1);
    this.localForm.controls['local_autori'].setValue('');
  }

  selectedCategory(
    event: MatAutocompleteSelectedEvent,
    ele: HTMLInputElement
  ): void {
    ele.value = '';
    ele.blur();
    this.selectedCategories.push(event.option.value);
    let index = this.categories.indexOf(event.option.value);
    this.categories.splice(index, 1);
    this.localForm.controls['local_categories'].setValue('');
  }

}
