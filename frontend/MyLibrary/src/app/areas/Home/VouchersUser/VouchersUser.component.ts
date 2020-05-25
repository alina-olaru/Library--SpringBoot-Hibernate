import { Router } from "@angular/router";
import { VoucherUserService } from "./VoucherUser.service";
import { LoginService } from "src/app/areas/login/login.service";
import { BookUser } from "src/app/Models/BookUser";
import { VoucherUser } from "./../../../Models/admin/VoucherUserModel";
import { ApiResponse } from "src/app/Models/general/api-response";
import { Voucher } from "src/app/Models/admin/VoucherModel";
import { VoucherService } from "./../../admin/pages/vouchers/voucher.service";
import { Component, OnInit } from "@angular/core";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { TitleService } from "../../admin/services/title.service";
import { ToastrService } from "src/app/services/toastr.service";
import { DomSanitizer } from "@angular/platform-browser";
import { LoadingService } from "src/app/modules/loading-spinner/loading.service";
import { MatDialog } from "@angular/material/dialog";
import { CategoryService } from "../../admin/pages/category/category.service";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: "app-VouchersUser",
  templateUrl: "./VouchersUser.component.html",
  styleUrls: ["./VouchersUser.component.scss"],
})
export class VouchersUserComponent implements OnInit {
  user: BookUser;
  basicVouchers: Voucher[] = [];
  myVouchers: Voucher[] = [];
  already: boolean = false;
  currentDate: Date;

  constructor(
    public voucherService: VoucherService,
    private titleService: TitleService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    public CategoriesService: CategoryService,
    private activatedRoute: ActivatedRoute,
    private auth: LoginService,
    private voucherUserService: VoucherUserService,
    private router: Router
  ) {
    this.currentDate = new Date();
    console.log(this.currentDate);
  }




  ngOnInit() {
    this.getBASICVouchers();
    this.user = this.auth.getUser();
  }

  //------------------------------basic user ( not necessarily logged in)------------------------------------------------------
  getBASICVouchers() {
    this.voucherService
      .GetVouchers()
      .subscribe((response: ApiResponse<Voucher[]>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.basicVouchers = response.body;
        // response.body.forEach((el)=>{
        //   if(el.voucherStartDate.getTime()<this.currentDate.getTime() && el.voucherEndDate.getTime()>this.currentDate.getTime()){
        //     this.basicVouchers.push(el);
        //     console.log(el.voucherStartDate.getTime()<this.currentDate.getTime());
        //   }
        // }
        // response.body.forEach((el)=>{
        //   if(el.voucherStartDate<this.currentDate && el.voucherEndDate>this.currentDate){
        //     this.basicVouchers.push(el);
        //     console.log(el.voucherStartDate.getTime()<this.currentDate.getTime());
           }
        else {
          //todo daca nu exista vouchere sa faci ceva in html sa nu fie gol
        }
      });
    console.log(this.basicVouchers + "vouchere");
  }

  getUrlImageForVoucher(voucher: Voucher) {
    return "url('data:image/jpg;base64," + voucher.voucherImageDb + "')";
  }

  //----------------------------------------------------------------actions-------------------------------------------------------

  myVouchersAdd(voucher: Voucher) {
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

    let newVoucher = new VoucherUser();
    newVoucher.used = false;
    newVoucher.usersWithVouchers = this.user;
    newVoucher.vouchers = voucher;
    newVoucher.orderWithVouchers = [];

    this.voucherUserService
      .getVouchersForUser(this.user)
      .subscribe((response) => {
        if ((response.status = ApiResponseType.SUCCESS)) {
          this.myVouchers = response.body;

          this.checkVoucher(voucher.voucherId);
          if (this.already == false) {
            this.voucherUserService
              .addVoucherToUser(newVoucher)
              .subscribe((response: ApiResponse<VoucherUser>) => {
                if (response && response.status == ApiResponseType.SUCCESS) {
                  this.toastr.Swal.fire({
                    title: "Ati adaugat voucherul in contul dumneavoastra!",
                    width: 600,
                    allowOutsideClick: true,
                    allowEscapeKey: true,
                    padding: "3em",
                    background:
                      "#fff url(https://themotherofallnerds.com/wp-content/uploads/2018/05/2-qscLHLb.gif)",
                    backdrop: `
                  rgba(0,0,0,0.396)
                  url("https://i.pinimg.com/originals/8d/8d/c0/8d8dc0ac8e4d6decf1b45a0a717088fa.gif")
                 center top
                  no-repeat
                `,
                  });
                } else {
                  this.toastr.Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: response.message,
                    footer: "Ne puteti contacta oricand!",
                  });
                  // footer: '<a href>Why do I have this issue?</a>'
                }
              });
          } else {
            this.toastr.Swal.fire({
              title: "VOUCHERUL este deja in contul dumneavoastra!",
              width: 600,
              allowOutsideClick: true,
              allowEscapeKey: true,
              padding: "3em",
              background:
                "#fff url(https://themotherofallnerds.com/wp-content/uploads/2018/05/2-qscLHLb.gif)",
              backdrop: `
              rgba(0,0,0,0.396)
              url("https://i.pinimg.com/originals/8d/8d/c0/8d8dc0ac8e4d6decf1b45a0a717088fa.gif")
             center top
              no-repeat
            `,
            });
          }
        }
      });
  }

  checkVoucher(id: number) {
    this.already = false;
    this.myVouchers.forEach((e) => {
      if (e.voucherId == id) {
        this.already = true;
      }
    });
  }
}
