export class Author{
  authorId:number;
  firstName:string;
  lastName:string;
  public constructor(init?: Partial<Author>) {
    Object.assign(this, init);
  }
}

