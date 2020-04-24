import { BookUser } from './BookUser';
export class Address{

  addressId:number;
  country:string;
  province:string;
  city:string;
  streetName:string;
  streetNumber:number;
  block:number;
  floor:number;
  appartmentNumber:number;
  userAddress:BookUser;
  public constructor(init?: Partial<BookUser>) {
    Object.assign(this, init);
  }

}
