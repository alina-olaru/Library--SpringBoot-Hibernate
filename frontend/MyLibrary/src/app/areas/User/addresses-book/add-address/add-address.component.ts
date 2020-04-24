import { AddressServiceService } from './../AddressService.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ToastrService } from 'src/app/services/toastr.service';

@Component({
  templateUrl: './add-address.component.html',
  styleUrls: ['./add-address.component.scss']
})
export class AddAddressComponent implements OnInit {

  localForm: FormGroup;


  constructor(
    private formBuilder: FormBuilder,
    private toastr: ToastrService,
    private addAddress:AddressServiceService
  ) { }

  ngOnInit(): void
   {

    this.localForm=this.formBuilder.group({


    })
  }

}
