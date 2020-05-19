import { BookUser } from './../BookUser';
import { Book } from '../admin/BookModel';
export class Review {

  reviewID:number;
  reviewerName:string;
  textReview:any;
  reviewPhotoDb:any;
  reviewPhoto:any;
  value:number;
  bookR:Book;
  userReviewMaker:BookUser;
  public constructor(init?: Partial<Review>) {
    Object.assign(this, init);
  }
}
