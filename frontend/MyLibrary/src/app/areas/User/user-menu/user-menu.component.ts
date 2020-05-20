import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ApiResponse } from 'src/app/Models/general/api-response';
import { LoginService } from 'src/app/areas/login/login.service';
import { Component, OnInit } from '@angular/core';
import {
faArrowRight
} from "@fortawesome/free-solid-svg-icons";
import { Route } from '@angular/compiler/src/core';
import { Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss']
})
export class UserMenuComponent implements OnInit {

faArrowRight = faArrowRight;

  constructor(private auth :LoginService,
    private router : Router,
    private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  logOut(){
    this.auth.logOutUser();

  }

  deleteAccount(){
    this.auth.deleteAccount().subscribe((response:ApiResponse<boolean>)=>{
      if(response && response.status == ApiResponseType.SUCCESS)
      {

        this.router.navigate['/login'];
      }
      else{
        this.toastr.Swal.fire({
          title: "A aparut o eroare,contacteaza-ne!",
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
           // this.router.navigate(["/login"]);
          }
        });
      }
    })

  }
}
