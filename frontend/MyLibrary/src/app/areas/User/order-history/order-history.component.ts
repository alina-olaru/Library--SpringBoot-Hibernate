import { ApiResponse } from 'src/app/Models/general/api-response';
import { CartService } from './../../Home/cart/cart.service';
import { BookUser } from 'src/app/Models/BookUser';
import { BookOrder } from 'src/app/Models/cart/Order';
import { Component, OnInit } from '@angular/core';
import { VoucherUserService } from '../../Home/VouchersUser/VoucherUser.service';
import { VoucherService } from '../../admin/pages/vouchers/voucher.service';
import { TitleService } from '../../admin/services/title.service';
import { ToastrService } from 'src/app/services/toastr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';
import { CategoryService } from '../../admin/pages/category/category.service';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../login/login.service';
import { RESOURCE_CACHE_PROVIDER } from '@angular/platform-browser-dynamic';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.scss']
})
export class OrderHistoryComponent implements OnInit {
  orders:BookOrder[] = [];
  user : BookUser = null;
  existOrders : boolean = false;
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
    private voucherUser : VoucherUserService,
    private cartService : CartService

  ) { }

  ngOnInit(): void {
    this.user = this.auth.getUser();
    this.getOrders();
  }

  getOrders(){

    if(this.user!=null && this.user !=undefined){
    this.cartService.getOrderForUser(this.user.userId).subscribe((Response : ApiResponse<BookOrder[]>)=>{
      if(Response && Response.status == ApiResponseType.SUCCESS){
        this.orders = Response.body;
        if(Response.body.length==0){
         this.existOrders = false;
        }
        else{
        this.existOrders = true;
        }
      }
      else{
      this.existOrders = false;
      }

    })
  }
  }

}
