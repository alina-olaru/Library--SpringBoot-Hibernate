import { AddEditVouchersComponent } from './add-edit-vouchers/add-edit-vouchers.component';
import { Voucher } from 'src/app/Models/admin/VoucherModel';
import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ToastrService } from 'src/app/services/toastr.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Author } from 'src/app/Models/admin/AuthorModel';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ActivatedRoute } from '@angular/router';
import { VoucherService } from './voucher.service';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
import { Category } from 'src/app/Models/admin/CategoryModel';
import { Subscription } from 'rxjs/internal/Subscription';
import { PublishersService } from '../publishers/publishers.service';
import { CategoryService } from '../category/category.service';
import { AuthorsService } from '../authors/authors.service';

@Component({
  selector: 'app-vouchers',
  templateUrl: './vouchers.component.html',
  styleUrls: ['./vouchers.component.scss'],
})
export class VouchersComponent implements OnInit, OnDestroy {
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;

  vouchers: Voucher[] = [];
  authors: Author[] = [];
  publishers: Publisher[] = [];
  categories: Category[] = [];
  _addVoucher: Voucher;

  displayedColumns: string[] = [
    'voucherId',
    'voucherTitle',
    'voucherDescription',
    'voucherStartDate',
    'voucherEndDate',
    'voucherMaximumUses',
    'voucherPrice',
    'language',
    'publisher_voucher',
    'author_voucher',
    'category_voucher',
    'voucherImage',
    'actions',
  ];

  fromRedirect = false;
  dataSource: MatTableDataSource<Voucher> = new MatTableDataSource(
    this.vouchers
  );
  subscriptions: Subscription[] = [];

  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private voucherService: VoucherService,
    private authorsService: AuthorsService,
    private categoryService: CategoryService,
    private publishersService: PublishersService
  ) {
    activatedRoute.queryParamMap.subscribe((params) => {
      if (params['action'] == 'add') {
        this.fromRedirect = true;
      }
      if (
        params['action'] == undefined &&
        (params as any).params['action'] == 'add'
      ) {
        this.fromRedirect = true;
      }
    });
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach((e) => {
      e.unsubscribe();
    });
  }

  ngOnInit(): void {
    if (this.fromRedirect == true) {
      this.AddVoucher();
    }
    this.GetVoucher();
    this.titleService.setTitle('faMoneyBillAlt', 'Vouchere');
    this.getAuthors();
    this.getPublishers();
    this.getCategories();
  }

  getAuthors() {
    const autSubscriber = this.authorsService
      .GetAuthors()
      .subscribe((response) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.authors = response.body;
        }
      });
    this.subscriptions.push(autSubscriber);
  }

  getPublishers() {
    const pubSubscriber = this.publishersService
      .GetPublishers()
      .subscribe((response) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.publishers = response.body;
        }
      });
    this.subscriptions.push(pubSubscriber);
  }

  getCategories() {
    const catSubscriber = this.categoryService
      .GetCategory()
      .subscribe((response) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.categories = response.body;
        }
      });
    this.subscriptions.push(catSubscriber);
  }

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.vouchers);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  GetVoucher() {
    this.loadingService.start();

    this.voucherService
      .GetVouchers()
      .subscribe((response: ApiResponse<Voucher[]>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          if (response.body.length == 0) {
            this.toastr.Toast.fire({
              icon: 'info',
              title: 'Nu exista vouchere in baza de date',
            });
            return;
          }
          this.vouchers = response.body;
          this.vouchers.forEach((e) => {
            if (e.voucherImageDb) {
              const objectURL = 'data:image/png;base64,' + e.voucherImageDb;
              e.voucherImageSrc = this.sanitizer.bypassSecurityTrustResourceUrl(
                objectURL
              );
            }
          });
          this.updateDataSouce();
        }
      });
  }

  AddVoucher() {
    const dialogRef = this.dialog.open(AddEditVouchersComponent, {
      width: '40%',
      data: {
        type: 'add',
        model: this._addVoucher,
        authors: this.authors,
        publishers: this.publishers,
        categories: this.categories,
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != undefined && result != null) {
        this.AddVoucherrConfirm(result);
      }
    });
  }

  AddVoucherrConfirm(voucher: Voucher) {
    this.loadingService.start();
    this.voucherService.AddVoucher(voucher).subscribe(
      (response: ApiResponse<Voucher>) => {
        this.loadingService.stop();

        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Voucherul a fost adaugat cu succes',
            icon: 'success',
          });
          if (response.body.voucherImageDb) {
            const objectURL =
              'data:image/png;base64,' + response.body.voucherImageDb;
            response.body.voucherImageSrc = this.sanitizer.bypassSecurityTrustResourceUrl(
              objectURL
            );
          }
          this.vouchers.unshift(response.body);
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la adaugare, incearca din nou!',
            'error'
          );
        }
      },
      (error) => {
        this.toastr.Swal.fire(
          'Eroare!',
          'A aparut o eroare la adaugare, incearca din nou!',
          'error'
        );
      }
    );
  }

  UpdateVoucher(item: Voucher) {
    const dialogRef = this.dialog.open(AddEditVouchersComponent, {
      width: '40%',
      data: {
        type: 'edit',
        model: Object.assign({}, item),
        authors: this.authors,
        publishers: this.publishers,
        categories: this.categories
      },
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result != undefined && result != null) {
        this.EditVoucherConfirm(result, item);
      }
    });
  }

  EditVoucherConfirm(newVoucher: Voucher, oldVoucher: Voucher) {
    this.loadingService.start();

    this.voucherService
      .UpdateVoucher(newVoucher, newVoucher.voucherId)
      .subscribe((response: ApiResponse<Voucher>) => {
        this.loadingService.stop();
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Toast.fire({
            title: 'Vouchcerul a fost editat cu succes!',
            icon: 'success',
          });

          const idxOld = this.vouchers.indexOf(oldVoucher);
          this.vouchers[idxOld] = newVoucher;
          this.updateDataSouce();
        } else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la editare, incearca din nou!',
            'error'
          );
        }
      });
  }

  DeleteVoucher(voucher: Voucher) {
    this.toastr.Swal.fire({
      title: 'Esti sigur ca vrei sa stergi acest bautura?',
      html: `Id: <b>${voucher.voucherId}</b> - Nume: <b>${voucher.voucherTitle}</b>`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Da',
      cancelButtonText: 'Nu',
    }).then((result) => {
      if (result.value) {
        this.loadingService.start();

        this.voucherService
          .DeleteVoucher(voucher.voucherId)
          .subscribe((response: ApiResponse<boolean>) => {
            this.loadingService.stop();
            if (
              response &&
              response.status == ApiResponseType.SUCCESS &&
              response.body == true
            ) {
              this.toastr.Toast.fire({
                title: 'Voucherul a fost sters.',
                icon: 'success',
              });
              this.GetVoucher();
            } else {
              this.toastr.Swal.fire(
                'Eroare!',
                'A aparut o eroare la stergere, incearca din nou!',
                'error'
              );
            }
          });
      }
    });
  }
}
