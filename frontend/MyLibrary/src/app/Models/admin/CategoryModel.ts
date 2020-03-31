export class Category{

  categoryId:number;
  categoryTitle:String;
  categoryDescription:String;
  public constructor(init?: Partial<Category>) {
    Object.assign(this, init);
  }


  }
