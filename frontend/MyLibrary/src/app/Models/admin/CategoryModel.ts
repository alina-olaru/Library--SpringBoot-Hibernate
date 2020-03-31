export class Category{

  categoryId:number;
  categoryTitle:string;
  categoryDescription:string;
  public constructor(init?: Partial<Category>) {
    Object.assign(this, init);
  }


  }
