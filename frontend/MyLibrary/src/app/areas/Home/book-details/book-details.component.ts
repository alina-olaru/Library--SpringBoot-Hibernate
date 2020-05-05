import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, ParamMap } from "@angular/router";
import { Book } from "src/app/Models/admin/BookModel";
import { ToastrService } from "src/app/services/toastr.service";
import { switchMap } from "rxjs/operators";

@Component({
  selector: "app-book-details",
  templateUrl: "./book-details.component.html",
  styleUrls: ["./book-details.component.scss"],
})
export class BookDetailsComponent implements OnInit {
  book: Book;
  bookId: number;
  constructor(private route: ActivatedRoute, private toastr: ToastrService) {
    console.error(this.bookId);
  }

  ngOnInit(): void {
    console.error(this.bookId);

    this.bookId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.GetBookById(this.bookId);
  }
  GetBookById(id: number) {
    this.bookId = id;
    console.error(id);
  }
}
