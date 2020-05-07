import { BookUser } from '../BookUser';
import { Book } from '../admin/BookModel';

export class PersonalBook {
  user: BookUser;
  book: Book;
  persBAuthor:string;
  persBTitle:string;
  bookImage:string;
  bookImageSrc?: any;
  public constructor(init?: Partial<BookUser>) {
    Object.assign(this, init);
  }
}
