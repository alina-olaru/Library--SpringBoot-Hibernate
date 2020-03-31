export class Publisher{

publisherId:number;
publisherTitle:string;
public constructor(init?: Partial<Publisher>) {
  Object.assign(this, init);
}


}
