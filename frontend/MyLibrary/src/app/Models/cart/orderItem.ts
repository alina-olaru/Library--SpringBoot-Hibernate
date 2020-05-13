import { BookUser } from './../BookUser';
import { Book } from 'src/app/Models/admin/BookModel';
import { BookOrder } from './Order';
export class orderItem {
  order : BookOrder;
  booksorder : Book;
  quantity : number;
  public constructor(init?: Partial<Book>) {
    Object.assign(this, init);
  }
}
