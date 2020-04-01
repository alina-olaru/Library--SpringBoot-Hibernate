import { Voucher } from './VoucherModel';

export class Quizzez {


  public constructor(init?: Partial<Quizzez>) {
    Object.assign(this, init);
  }


  quizzId:number;
  quizzQuestion:string;
  quizzAnswers:string[];
  quizzCorrectAnswer:string;
  quizzStartDate:Date;
  quizzEndDate:Date;
  vouchersGotByQuizz:Voucher[]
}
