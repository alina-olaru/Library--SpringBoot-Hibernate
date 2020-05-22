import { BookUser } from '../BookUser';
import { Book } from '../admin/BookModel';

export class Mailme {
  bookId: number;
  email: string;
  public constructor(init?: Partial<Mailme>) {
    Object.assign(this, init);
  }
}
