import { BooksAuthors } from './BooksAuthorsModel';
import { BooksCategories } from './BooksCategoriesModel';
import { Category } from 'src/app/Models/admin/CategoryModel';
import { Publisher } from './PublisherModel';

export class Book{
  bookId:number;
  bookTitle:string;
  bookLanguage:string;
  bookYear:number;
  numberOfPages:number;
  numberofVolumes:number;
  bookDescription:string;
  bookDimension:string;
  bookWeight:number;
  bookPrice:number;
  coverType:string;
  numberOfReviews:number;
  bookRating:number;
  numberOfBoooks:number;
  publisher:Publisher;
  persB:any;
  wishBooks:any;
  items:any;
  booksCategories:BooksCategories[];
  bookAuthor:BooksAuthors[];
  bookR:any;
  bookImage:string;
  bookImageSrc?: any;
  public constructor(init?: Partial<Book>) {
    Object.assign(this, init);
  }
}

