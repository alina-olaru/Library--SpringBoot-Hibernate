import { ApiResponse } from 'src/app/Models/general/api-response';
import { ComplaintService } from './Complaint.service';
import { BookUser } from 'src/app/Models/BookUser';
import { Complaint } from './../../../Models/user/Complaint';
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../../Home/welcome/LandingBooks.service';
import { DomSanitizer } from '@angular/platform-browser';
import { LoginService } from '../../login/login.service';
import { WishlistService } from '../../Home/book-details/wishlist.service';
import { PersonalBookService } from '../../Home/book-details/personalBook.service';
import { CartService } from '../../Home/cart/cart.service';
import { MatDialog } from '@angular/material/dialog';
import { BreakpointObserver } from '@angular/cdk/layout';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';

@Component({
  selector: 'app-Complaint',
  templateUrl: './Complaint.component.html',
  styleUrls: ['./Complaint.component.scss']
})
export class ComplaintComponent implements OnInit {

  localForm: FormGroup;
  user :BookUser;
  myComplaint : Complaint;
  all :Complaint[] = [];
  fileName: string;
  base64: string;
  state: number;


  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer,
    private auth: LoginService,
    private wishService: WishlistService,
    private router: Router,
    private personalBookService: PersonalBookService,
    private cartService: CartService,
    public dialog: MatDialog,
    public breakpointObserver: BreakpointObserver,
    private formBuilder: FormBuilder,
    private ComplaintService : ComplaintService

  ) {
    this.myComplaint = new Complaint();
   }

  ngOnInit() {
    this.user = this.auth.getUser();
    this.getComsbyId();
    this.localForm = this.formBuilder.group({
      text:[null,[Validators.required,Validators.minLength(20)]],
      subject:[null,[Validators.required,Validators.maxLength(15)]],
    });
  }

  get form() {
    return this.localForm.controls;
  }


  UploadFile() {
    this.myComplaint = new Complaint();
    const fileUpload = document.getElementById(
      "modal-file-upload-input"
    ) as HTMLInputElement;
    fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
        const file = fileUpload.files[index];
        var myReader: FileReader = new FileReader();
        this.fileName = file.name;
        myReader.onloadend = (e) => {
          console.log(myReader.result);

          this.myComplaint.complaintImageDb = <string>myReader.result;
          this.myComplaint.complaintImage = myReader.result;
          this.base64 = <string>myReader.result;
        };
        myReader.readAsDataURL(file);
      }
    };
    fileUpload.click();
  }

  DeleteFile() {
    this.fileName = null;
    this.myComplaint.complaintImageDb = null;
    this.myComplaint.complaintImage = null;
    this.base64 = null;
  }


  SubmitForm(){
    if(this.localForm.valid){
      let model = new Complaint(this.localForm.value);
      if (this.user != null && this.user != undefined) {
        model.com = this.user;
      } else {
        this.user = null;
      }
      if (typeof this.base64 == "string") {
        model.complaintImageDb = this.base64
          ? this.base64.replace(/^data:image\/[a-z]+;base64,/, "")
          : null;
      } else {
        model.complaintImageDb = this.base64
          ? (this
              .base64 as any).changingThisBreaksApplicationSecurity.replace(
              /^data:image\/[a-z]+;base64,/,
              ""
            )
          : null;
      }
      model.complaintImage = this.base64;



      this.PostRequest(model);
    }
  }

  PostRequest(model : Complaint){

    this.ComplaintService.addComplaint(model).subscribe((response:ApiResponse<Complaint>) => {

      if(response && response.status == ApiResponseType.SUCCESS){
        this.toastr.Swal.fire({
          title:
            "Ati trimis feedback-ul , va multumim!Veti primi un raspuns de la operatorii nostri in urmatoarele 3 zile!",
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
        }).then((response) => {
          this.localForm.reset();
        });
      }
    })


  }

  getComsbyId(){

    this.ComplaintService.getComsbyId(this.user.userId).subscribe((response:ApiResponse<Complaint[]>) =>{

      if(response && response.status == ApiResponseType.SUCCESS){
        this.all = response.body;
      }
      else{

      }
    })
  }
}

