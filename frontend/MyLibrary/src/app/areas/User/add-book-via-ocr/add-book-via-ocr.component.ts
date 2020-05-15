import { ApiResponse } from './../../../Models/general/api-response';
import { PersonalBookService } from './../../Home/book-details/personalBook.service';
import { LoginService } from 'src/app/areas/login/login.service';
import { BookUser } from './../../../Models/BookUser';
import { PersonalBook } from "./../../../Models/home/PersonalBook";
import {
  Component,
  OnInit,
  ViewChild,
  ElementRef,
  Renderer2,
} from "@angular/core";
import { getElement, Image } from "@amcharts/amcharts4/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef } from '@angular/material/dialog';
import { ApiResponseType } from 'src/app/Models/general/api-response-type.enum';
import { ToastrService } from 'src/app/services/toastr.service';
import { LandingBooksService } from '../../Home/welcome/LandingBooks.service';
import Swal from 'sweetalert2';

@Component({
  selector: "app-add-book-via-ocr",
  templateUrl: "./add-book-via-ocr.component.html",
  styleUrls: ["./add-book-via-ocr.component.scss"],
})
export class AddBookViaOCRComponent implements OnInit {
  myBook: PersonalBook;
  images: Image[] = [];
  fileName: string;
  base64: string;
  state: number;
  localForm: FormGroup;
  user : BookUser;

  @ViewChild("video", { static: true }) videoElement: ElementRef;
  @ViewChild("canvas", { static: true }) canvas: ElementRef;

  videoWidth = 100;
  videoHeight = 100;
  constraints = {
    video: {
      facingMode: "environment",
      width: { ideal: 4096 },
      height: { ideal: 2160 },
    },
  };

  constructor
  ( public dialogRef: MatDialogRef<AddBookViaOCRComponent>,
    private renderer: Renderer2,
     private formBuilder: FormBuilder,
     private auth : LoginService,
     private persBookService : PersonalBookService,
     private toastr: ToastrService,
     private landingBooksService: LandingBooksService,
     ) {
    this.state = 0;
    this.user = auth.getUser();
    this.myBook = new PersonalBook();
  }

  ngOnInit(): void {
    console.log(this.state + " state");
    this.localForm = this.formBuilder.group({
      user: [{ value: this.user }],
  //    book : [{value : null}],
      persBAuthor: [" ", Validators.required],
      persBTitle: [" ", Validators.required],
    });
  }
  //   user: BookUser;
  //   book: Book;
  //   persBAuthor:string;
  //   persBTitle:string;
  //   bookImage:string;
  //   bookImageSrc?: any;
  // }
  ngOnDestroy(): void {
    //Called once, before the instance is destroyed.
    //Add 'implements OnDestroy' to the class.
    this.state = 0;
    console.log(this.state + " state");

    //Se opreste camera
    this.stopCamera();
  }
  //----------------------- ---------------------(STATE 1 )UPLOAD VIA CAMERA/WEBCAM-------------------------------------------

  startCamera() {
    console.log(this.state + " state");
    this.state = 1;
    console.log(this.state + " state");
    if (!!(navigator.mediaDevices && navigator.mediaDevices.getUserMedia)) {
      navigator.mediaDevices
        .getUserMedia(this.constraints)
        .then(this.attachVideo.bind(this))
        .catch(this.handleError);
    } else {
      alert("Sorry, camera not available.");
    }
  }

  stopCamera() {}
  attachVideo(stream) {
    console.log(this.state + " state");
    this.renderer.setProperty(
      this.videoElement.nativeElement,
      "srcObject",
      stream
    );

    this.renderer.listen(this.videoElement.nativeElement, "play", (event) => {
      this.videoHeight = this.videoElement.nativeElement.videoHeight;
      this.videoWidth = this.videoElement.nativeElement.videoWidth;
    });
  }

  capture() {
    console.log(this.state + " state");
    this.renderer.setProperty(
      this.canvas.nativeElement,
      "width",
      this.videoWidth
    );
    this.renderer.setProperty(
      this.canvas.nativeElement,
      "height",
      this.videoHeight
    );
    this.canvas.nativeElement
      .getContext("2d")
      .drawImage(this.videoElement.nativeElement, 0, 0);

    this.images.push(this.canvas.nativeElement.getContext("2d"));
    console.log(this.images);
  }

  handleError(error) {
    console.error("EROAREEEEEEEEEEEEEEEEEE");
    console.log("Error: ", error);
  }

  selectPhoto() {
    console.log(
      "S-a selectat imaginea" +
        this.images.length +
        "heeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeei"
    );
  }

  //----------------------- ---------------------(STATE 2 )UPLOAD from galery-------------------------------------------

  uploadFromGalery() {
    this.state = 2;
    this.UploadFile();
  }
  manual() {
    this.state = 3;
  }

  UploadFile() {
    this.myBook = new PersonalBook();
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

          this.myBook.bookImage = <string>myReader.result;
          this.myBook.bookImageSrc = myReader.result;
          this.base64 = <string>myReader.result;
        };
        myReader.readAsDataURL(file);
      }
    };
    fileUpload.click();
  }

  DeleteFile() {
    this.fileName = null;
    this.myBook.bookImage = null;
    this.myBook.bookImageSrc = null;
    this.base64 = null;
  }

  SubmitManualPersBook(){
      if (this.localForm.valid) {
        let model: PersonalBook = new PersonalBook(this.localForm.value);

        if(typeof this.base64 == "string"){
          model.bookImage = this.base64
            ? this.base64.replace(/^data:image\/[a-z]+;base64,/, '')
            : null;
          } else
          {
            model.bookImage = this.base64
            ? (this.base64 as any).changingThisBreaksApplicationSecurity.replace(/^data:image\/[a-z]+;base64,/, '')
            : null;
          }
        model.bookImageSrc = this.base64;
        this.PostRequest(model);
        this.dialogRef.close(model);
      }

  }


  PostRequest(book : PersonalBook){
    this.persBookService.addBook(book).subscribe((response : ApiResponse<PersonalBook>)=>{
      if (response && response.status == ApiResponseType.SUCCESS) {


        this.toastr.Swal.fire({
          title: 'Cartea s-a adaugat cu succes!',
          showClass: {
            popup: 'animate__animated animate__fadeInDown'
          },
          hideClass: {
            popup: 'animate__animated animate__fadeOutUp'
          }
        })



      }
      else{
        this.toastr.Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: response.message,
          footer: 'Contactati-ne pentru detalii'
        })
      }

    })

  }
}
