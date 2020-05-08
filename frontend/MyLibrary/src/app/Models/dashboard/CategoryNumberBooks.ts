import { BookUser } from '../BookUser';
import { Book } from '../admin/BookModel';

export class CategoryNumberBooks {
  numberBooksforCategory:number;
  titleOfCategory:string;

  public constructor(init?: Partial<BookUser>) {
    Object.assign(this, init);
  }
}
