import { Book } from 'src/app/Models/admin/BookModel';
export class CartBook {
  book: Book;
  quantity: number;
  public constructor(init?: Partial<Book>) {
    Object.assign(this, init);
  }
}
