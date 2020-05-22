import { Book } from "./../../../Models/admin/BookModel";
import { ApiResponse } from "./../../../Models/general/api-response";
import { PersonalBookService } from "./../../Home/book-details/personalBook.service";
import { LoginService } from "src/app/areas/login/login.service";
import { BookUser } from "./../../../Models/BookUser";
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
import { MatDialogRef } from "@angular/material/dialog";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";
import { ToastrService } from "src/app/services/toastr.service";
import { LandingBooksService } from "../../Home/welcome/LandingBooks.service";
import Swal from "sweetalert2";
import { DomSanitizer } from "@angular/platform-browser";
import { Subscription } from "rxjs";
import { retryWhen } from "rxjs/operators";

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
  user: BookUser;
  emptyBook: Book;
  subscriptions: Subscription[] = [];

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

  constructor(
    public dialogRef: MatDialogRef<AddBookViaOCRComponent>,
    private renderer: Renderer2,
    private formBuilder: FormBuilder,
    private auth: LoginService,
    private persBookService: PersonalBookService,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService,
    private domSanitizer: DomSanitizer
  ) {
    this.state = 0;
    this.user = auth.getUser();
    this.myBook = new PersonalBook();

    console.log("empty book sus:" + this.emptyBook);
  }

  ngOnInit() {
    //  if (this.emptyBook != undefined) {
    this.GetBookById(27);
    console.log("ngOnInit" + this.emptyBook);
    //  }
  }

  ngAfterContentInit() {
    console.log(this.state + " state");
    console.log("empty book ngAfterContentChecked:" + this.emptyBook);

    this.localForm = this.formBuilder.group({
      persBAuthor: [null, Validators.required],
      persBTitle: [null, Validators.required],
    });
  }
  ngOnDestroy(): void {
    this.subscriptions.forEach((e) => {
      e.unsubscribe();
    });
    this.state = 0;

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

          this.myBook.bookImageDb = <string>myReader.result;
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
    this.myBook.bookImageDb = null;
    this.myBook.bookImageSrc = null;
    this.base64 = null;
  }

  SubmitManualPersBook() {
    if (this.localForm.valid) {
      let model: PersonalBook = new PersonalBook(this.localForm.value);
      model.user = this.user;
      if (this.emptyBook) {
     //   console.log("modelul " + model.book.bookAuthor);
        model.book = this.emptyBook;
        if (typeof this.base64 == "string") {
          model.bookImageDb = this.base64
            ? this.base64.replace(/^data:image\/[a-z]+;base64,/, "")
            : null;
        } else {
          model.bookImageDb = this.base64
            ? (this
                .base64 as any).changingThisBreaksApplicationSecurity.replace(
                /^data:image\/[a-z]+;base64,/,
                ""
              )
            : null;
        }
        model.bookImageSrc = this.base64;

        this.PostRequest(model);
      }
    } else {
      //NU AVEM EMPTY BOOK
    }
  }

  GetBookById(id: number) {
    const getBooks = this.landingBooksService.GetDetails(id).subscribe(
      (response: ApiResponse<Book>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.emptyBook = response.body;
          if (this.emptyBook.bookImageDb) {
            this.emptyBook.bookImageSrc = this.domSanitizer.bypassSecurityTrustResourceUrl(
              "data:image/jpg;base64," + this.emptyBook.bookImageDb
            );
          }
          console.log(this.emptyBook);

          return response.body;
        } else {
          this.toastr.Toast.fire({
            icon: "error",
            title: "A aparut o eroare",
          });
        }
      },
      (error) => {
        this.toastr.Toast.fire({
          icon: "error",
          title: "A aparut o eroare",
        });
      }
    );

    this.subscriptions.push(getBooks);
  }

  PostRequest(book: PersonalBook) {
    this.persBookService
      .addBook(book)
      .subscribe((response: ApiResponse<PersonalBook>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.toastr.Swal.fire({
            title: "Cartea s-a adaugat cu succes!",
            showClass: {
              popup: "animate__animated animate__fadeInDown",
            },
            hideClass: {
              popup: "animate__animated animate__fadeOutUp",
            },
          });
        } else {
          this.toastr.Swal.fire({
            icon: "error",
            title: "Oops...",
            text: response.message,
            footer: "Contactati-ne pentru detalii",
          });
        }
        this.dialogRef.close();
      });
  }
}
