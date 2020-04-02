import { ToastrService } from './../../../../../services/toastr.service';
import { Quizzez } from './../../../../../Models/admin/QuizzezModel';



import { Component, OnInit, Inject } from '@angular/core';
import {MatIconModule} from '@angular/material/icon';
import {
  FormControl,
  FormGroup,
  FormBuilder,
  Validators
} from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';




interface Data {
  type: string;
  model: Quizzez;
}



@Component({
  selector: 'app-add-edit-quizz',
  templateUrl: './add-edit-quizz.component.html',
  styleUrls: ['./add-edit-quizz.component.scss']
})
export class AddEditQuizzComponent implements OnInit {




  localForm: FormGroup;
  myControl = new FormControl();
  minDate: Date;
  maxDate: Date;
  answers: string[]=[];
  constructor(
    public dialogRef: MatDialogRef<AddEditQuizzComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Data,
    private formBuilder: FormBuilder,
    private toastr: ToastrService

  ) { }

  ngOnInit(): void {

    const currentYear = new Date().getFullYear();
    const currentDay = new Date().getDate();
    const currentMonth = new Date().getMonth();
    this.minDate = new Date(currentYear,currentMonth,currentDay);
    this.maxDate = new Date(currentYear ,currentMonth+6,currentDay);


    if (this.data.model == null || this.data.model == undefined) {
      this.data.model = new Quizzez();
  }
    this.localForm = this.formBuilder.group({
      quizzId: [{ value: this.data.model.quizzId, disabled: true }],
      quizzQuestion: [this.data.model.quizzQuestion, Validators.required],
      quizzCorrectAnswer: [this.data.model.quizzCorrectAnswer, Validators.required],
      quizzStartDate: [this.data.model.quizzStartDate, Validators.required],
      quizzEndDate: [this.data.model.quizzEndDate, Validators.required]

  });



}

  get form() {
    return this.localForm.controls;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }


  async addAnswer(){
    const { value: answer } = await this.toastr.Swal.fire({
      title: 'Introdu raspunsul:',
      input: 'text',
      showCancelButton: true,
      inputValidator: (value) => {
        if (!value) {
          return 'Raspunsul trebuie sa contina text!'
        }
      }
    })

    if (answer) {
      this.answers.push(answer);
      console.log(this.answers);
    }
  }


  SubmitForm() {
    if (this.localForm.valid) {
      console.log(this.localForm.value);
      const model: Quizzez = new Quizzez(this.localForm.value);
      model.quizzId = this.data.model.quizzId;
      this.dialogRef.close(model);
    }
  }
}
