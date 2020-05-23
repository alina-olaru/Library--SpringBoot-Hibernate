import { Voucher } from './VoucherModel';

export class Quizz {


  public constructor(init?: Partial<Quizz>) {
    Object.assign(this, init);
  }


  quizzId:number;
  quizzQuestion:string;
  quizzAnswers:string[];
  quizzCorrectAnswer:string;
  quizzStartDate:Date;
  quizzEndDate:Date;
  bonus: number;
}
