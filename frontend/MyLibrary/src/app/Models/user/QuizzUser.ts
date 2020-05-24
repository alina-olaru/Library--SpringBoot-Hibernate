import { Address } from '../Address';
import { BookUser } from 'src/app/Models/BookUser';
import { Quizz } from '../admin/QuizzModel';
export class QuizzUser {
  userForQuizz: BookUser;
  quizzForUser: Quizz;
  public constructor(init?: Partial<QuizzUser>) {
    Object.assign(this, init);
  }
}
