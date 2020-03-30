export class Author{
  authorId:number;
  firstName:String;
  lastName:String;
  public constructor(init?: Partial<Author>) {
    Object.assign(this, init);
  }
}
