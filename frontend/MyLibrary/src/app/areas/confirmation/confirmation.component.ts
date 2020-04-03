import { Component, OnInit } from '@angular/core';
import {  RegisterService} from '../register/register.service';
import { ActivatedRoute , Params} from '@angular/router';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { BookUser } from 'src/app/Models/BookUser';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ToastrService } from 'src/app/services/toastr.service';
import { CookieService } from 'ngx-cookie-service';
@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss']
})
export class ConfirmationComponent implements OnInit {
token: string = "";


  constructor(private registerService: RegisterService,
              private route: ActivatedRoute,
              private toastr: ToastrService,
              private cookieService: CookieService
              ) {

              }

  ngOnInit(): void {

    this.route.queryParams.subscribe(params => {
      if(params != undefined)
      {
        this.token = params["token"];
      }

      if(!this.token){
        this.toastr.Toast.fire({
          icon: "error",
          title: "Token-ul este gol!"
        });
      }else
      {
        this.ConfirmUser();
      }
    });



    // this.token= this.route.snapshot.paramMap.get('token');
    // console.log(this.token + "asta e tokenul din url");

    // this.route.queryParamMap.subscribe((params: Params ) =>{
    //   let date = params['startdate'];
    //   console.info(date + "token");
    // });


    // const allParamas=this.route.snapshot.params;
    // const param_token=allParamas.token;
    // console.info(param_token+ "   token  ");






    // tslint:disable-next-line: deprecation

  }

  ConfirmUser(){
    this.registerService.ConfirmUser(this.token).subscribe((response: ApiResponse<BookUser>) =>{
      if (response && response.status == ApiResponseType.SUCCESS) {
        let expiration: Date = new Date();

        this.toastr.Toast.fire({
          title: 'Ati confirmat contul cu succes!!',
          icon: 'success'

        });
        this.cookieService.set(
          "auth-user-info",
          JSON.stringify(response.body),
          expiration
        );

      }
    });
  }

}
