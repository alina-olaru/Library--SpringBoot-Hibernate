import { ToastrService } from "./../../../../../services/toastr.service";
import { Quizz } from "../../../../../Models/admin/QuizzModel";

import { Component, OnInit, Inject } from "@angular/core";
import { MatIconModule } from "@angular/material/icon";
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators,
} from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";

interface Data {
  type: string;
  model: Quizz;
}
@Component({
  selector: "app-add-edit-quizz",
  templateUrl: "./add-edit-quizz.component.html",
  styleUrls: ["./add-edit-quizz.component.scss"],
})
export class AddEditQuizzComponent implements OnInit {
  localForm: FormGroup;
  myControl = new FormControl();
  minDate: Date;
  maxDate: Date;
  answers: string[] = [];
  correctAnswer: string;
  constructor(
    public dialogRef: MatDialogRef<AddEditQuizzComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private toastr: ToastrService
  ) {}

  ngOnInit(): void {
    const currentYear = new Date().getFullYear();
    const currentDay = new Date().getDate();
    const currentMonth = new Date().getMonth();
    this.minDate = new Date(currentYear, currentMonth, currentDay);
    this.maxDate = new Date(currentYear, currentMonth + 6, currentDay);

    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Quizz();
    } else
    {
      this.correctAnswer = this.data.model.quizzCorrectAnswer;
      this.answers = this.data.model.quizzAnswers;
    }
    this.localForm = this.formBuilder.group({
      quizzId: [{ value: this.data.model.quizzId, disabled: true }],
      quizzQuestion: [this.data.model.quizzQuestion, Validators.required],
      quizzStartDate: [this.data.model.quizzStartDate, Validators.required],
      quizzEndDate: [this.data.model.quizzEndDate, Validators.required],
      bonus: [this.data.model.bonus, Validators.required]
    });
  }

  get form() {
    return this.localForm.controls;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  async addAnswer() {
    const { value: answer } = await this.toastr.Swal.fire({
      title: "Introdu raspunsul:",
      input: "text",
      showCancelButton: true,
      inputValidator: (value) => {
        if (!value) {
          return "Raspunsul trebuie sa contina text!";
        }
      },
    });

    if (this.answers.indexOf(answer) >= 0) {
      this.toastr.Toast.fire({
        title: "Raspunsul exista deja in lista de raspunsuri",
        icon: "warning",
      });
      return;
    }

    if (answer) {
      this.answers.push(answer);
      if (this.answers.length == 1) {
        this.correctAnswer = answer;
      }
      console.log(this.answers);
    }
  }

  SubmitForm() {
    if (this.localForm.valid) {
      if (this.answers.length < 2) {
        this.toastr.Toast.fire({
          title: "Numarul minim de raspunsuri este 2",
          icon: "error",
        });
        return;
      }

      console.log(this.localForm.value);
      const model: Quizz = new Quizz(this.localForm.value);
      model.quizzId = this.data.model.quizzId;
      model.quizzAnswers = this.answers;
      model.quizzCorrectAnswer = this.correctAnswer;
      this.dialogRef.close(model);
    }
  }

  DeleteAnswer(answer: string) {

    this.answers.splice(this.answers.indexOf(answer), 1);
    if (this.correctAnswer == answer && this.answers.length > 0) {
      this.correctAnswer = this.answers[0];
    }
  }

  MakeCorrectAnswer(answer: string) {
    this.correctAnswer = answer;
  }
}
