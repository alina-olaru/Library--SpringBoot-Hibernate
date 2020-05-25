import { ApiResponse } from 'src/app/Models/general/api-response';
import { BookUser } from 'src/app/Models/BookUser';
import { Component, OnInit } from '@angular/core';
import { VoucherService } from '../../admin/pages/vouchers/voucher.service';
import { TitleService } from '../../admin/services/title.service';
import { ToastrService } from 'src/app/services/toastr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { CategoryService } from '../../admin/pages/category/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../login/login.service';
import { VoucherUserService } from '../../Home/VouchersUser/VoucherUser.service';
import { Voucher } from 'src/app/Models/admin/VoucherModel';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-MyVouchers',
  templateUrl: './MyVouchers.component.html',
  styleUrls: ['./MyVouchers.component.scss']
})
export class MyVouchersComponent implements OnInit {

  vouchers : Voucher[]=[];
  user : BookUser;
  empty : boolean = false;
  constructor(

    public voucherService : VoucherService,
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    public CategoriesService: CategoryService,
    private activatedRoute: ActivatedRoute,
    private auth : LoginService,
    private voucherUserService : VoucherUserService,
    private router:Router,
    private voucherUser : VoucherUserService



  ) { }

  ngOnInit() {
    this.user=this.auth.getUser();
    this.getVouchers();
  }


  getVouchers(){
    if (this.user == null || this.user == undefined) {
      this.toastr.Swal.fire({
        title: "Trebuie sa te loghezi pentru a aduna vouchere!!",
        html: ``,
        // Autor: <b>${Book.bookAuthor.firstName} ${Book.bookAuthor.lastName}</b>
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Da",
        cancelButtonText: "Nu",
      }).then((result) => {
        if (result.value) {
          this.router.navigate(["/login"]);
        }
      });
    }

    this.voucherUserService.getVouchersForUser(this.user).subscribe((response:ApiResponse<Voucher[]>) =>{
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.vouchers = response.body;
        console.log(this.vouchers);
      }
      else{
        this.empty=true;
        //todo handling in front sa iti apara ceva mesaj
      }
    })

  }

}
