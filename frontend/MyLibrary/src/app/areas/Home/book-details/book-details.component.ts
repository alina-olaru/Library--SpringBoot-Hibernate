import { ApiResponse } from "./../../../Models/general/api-response";
import { LandingBooksService } from "./../welcome/LandingBooks.service";
import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Book } from "src/app/Models/admin/BookModel";
import { ToastrService } from "src/app/services/toastr.service";
import { switchMap } from "rxjs/operators";
import { ApiResponseType } from "src/app/Models/general/api-response-type.enum";

@Component({
  selector: "app-book-details",
  templateUrl: "./book-details.component.html",
  styleUrls: ["./book-details.component.scss"],
})
export class BookDetailsComponent implements OnInit {
  book: Book;
  bookId: number;
  constructor(
    private route: ActivatedRoute,
    private toastr: ToastrService,
    private landingBooksService: LandingBooksService
  ) {}

  ngOnInit(): void {
    this.bookId = parseInt(this.route.snapshot.paramMap.get("id"));
    this.GetBookById(this.bookId);
  }
  GetBookById(id: number) {
    this.bookId = id;
    console.error(id);

    this.landingBooksService
      .GetDetails(id)
      .subscribe((response: ApiResponse<Book>) => {
        if (response && response.status == ApiResponseType.SUCCESS) {
          this.book = response.body;
          console.log(this.book);
        } else {
          this.toastr.Toast.fire({
            icon: "error",
            title: "A aparut o eroare",
          });
        }
      }),
      (error) => {
        this.toastr.Toast.fire({
          icon: "error",
          title: "A aparut o eroare",
        });
      };
  }
}
