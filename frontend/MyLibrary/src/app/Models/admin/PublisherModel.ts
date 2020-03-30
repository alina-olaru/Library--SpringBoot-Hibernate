export class Publisher{

publisherId:number;
publisherTitle:String;
public constructor(init?: Partial<Publisher>) {
  Object.assign(this, init);
}


}
