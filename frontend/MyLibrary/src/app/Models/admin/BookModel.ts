import { Category } from 'src/app/Models/admin/CategoryModel';
import { Publisher } from './PublisherModel';

export class Book{
  bookId:number;
  bookTitle:String;
  bookLanguage:String;
  bookYear:number;
  numberOfPages:number;
  numberofVolumes:number;
  bookDescription:String;
  bookDimension:String;
  bookWeight:number;
  bookPrice:number;
  coverType:String;
  numberOfReviews:number;
  bookRating:number;
  numberOfBoooks:number;
  publisher:Publisher;
  persB:any;
  wishBooks:any;
  items:any;
  booksCategories:Category
  bookAuthor:any;
  bookR:any;
  bookImage:any;
  public constructor(init?: Partial<Book>) {
    Object.assign(this, init);
  }
}

