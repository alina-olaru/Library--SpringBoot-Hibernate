import { LoginService } from './../../login/login.service';
import { ApiResponse } from './../../../Models/general/api-response';
import { AddressServiceService } from './AddressService.service';
import { Address } from './../../../Models/Address';
import { AddAddressComponent } from './add-address/add-address.component';
import { Component, OnInit } from '@angular/core';
import { BookUser } from 'src/app/Models/BookUser';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ToastrService } from 'src/app/services/toastr.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoadingService } from 'src/app/modules/loading-spinner/loading.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-addresses-book',
  templateUrl: './addresses-book.component.html',
  styleUrls: ['./addresses-book.component.scss']
})
export class AddressesBookComponent implements OnInit {
user:BookUser;

  constructor(
    public  adressService: AddressServiceService,
    private toastr: ToastrService,
    private sanitizer: DomSanitizer,
    private loadingService: LoadingService,
    public dialog: MatDialog,
    private authService: LoginService,
    private login:LoginService) {


      this.user=this.login.getUser();
     }

  ngOnInit(): void {
  }


  AddAddress(){
    console.log("S-a intrat in functie");
    const dialogRef = this.dialog.open(AddAddressComponent, {
      width: '40%',
      data: {
        type: 'add'
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result != undefined && result != null) {
        this.AddConfirm(result);
      }
    });
  }

  AddConfirm(address:Address) {
    //this.loadingService.start();

    this.adressService.addAddress(address, this.authService.getUser()).subscribe((response:ApiResponse<BookUser>) =>{
      if (response && response.status == ApiResponseType.SUCCESS) {
        this.toastr.Toast.fire({
          title: 'Vouchcerul a fost editat cu succes!',
          icon: 'success'
        });
    }
        else {
          this.toastr.Swal.fire(
            'Eroare!',
            'A aparut o eroare la editare, incearca din nou!',
            'error'
          );
        }
      });
  }



}
