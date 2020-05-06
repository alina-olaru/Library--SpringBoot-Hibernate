import { BookUser } from '../BookUser';
import { Book } from '../admin/BookModel';

export class Wishlist {
  userwishlist: BookUser;
  bookwishlist: Book;
  public constructor(init?: Partial<BookUser>) {
    Object.assign(this, init);
  }
}
