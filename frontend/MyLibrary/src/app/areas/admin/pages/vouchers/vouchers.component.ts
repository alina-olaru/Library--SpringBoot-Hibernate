import { MatTableDataSource } from '@angular/material/table';
import { TitleService } from './../../services/title.service';
import { Component, OnInit, ViewChild } from '@angular/core';
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

@Component({
  selector: 'app-vouchers',
  templateUrl: './vouchers.component.html',
  styleUrls: ['./vouchers.component.scss']
})
export class VouchersComponent implements OnInit {
  @ViewChild(MatSort, { static: false }) sort: MatSort;
  @ViewChild(MatPaginatoraginator, { static: false }) paginator: MatPaginator;



vouchers:Voucher[]=[];
_addVoucher:Voucher;
displayedColumns:string[]=[
'voucherId',
'voucherTitle',
'voucherDescription',
//'voucherImage',
'voucherStartDate',
'voucherEndDate',
'voucherMaximumUses',
'voucherPrice'

];

fromRedirect=false;
dataSource:MatTableDataSource<Voucher>=new MatTableDataSource(this.vouchers);


  constructor(
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private activatedRoute: ActivatedRoute,
    private voucherService:VoucherService
  ) {

    activatedRoute.queryParamMap.subscribe(params => {
      if (params["action"] == "add") {
        this.fromRedirect = true;
      }
      if(params["action"]==undefined && (params as any).params["action"]=="add"){
        this.fromRedirect = true;
      }
    });


  }

  ngOnInit(): void {
    if (this.fromRedirect == true) {
      this.AddVoucher();
    }
    this.GetVoucher();
    this.titleService.setTitle('faMoneyBillAlt', 'Vouchere');
  }
  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  updateDataSouce() {
    this.dataSource = new MatTableDataSource(this.vouchers);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  GetVoucher(){

    this.loadingService.start();

    this.voucherService
    .GetVouchers()


  }



  AddVoucher(){


  }

  UpdateVoucher(){

  }

  DeleteVoucher(){

  }


}
