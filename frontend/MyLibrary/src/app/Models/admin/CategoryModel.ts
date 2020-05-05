import { Book } from './BookModel';

export class Category{

  categoryId:number;
  categoryTitle:string;
  categoryDescription:string;
  categoryBooks?: Book[];
  public constructor(init?: Partial<Category>) {
    Object.assign(this, init);
  }


  }
