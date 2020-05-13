import { orderItem } from './orderItem';
import { BookUser } from './../BookUser';
import { Book } from 'src/app/Models/admin/BookModel';
export class BookOrder {
  orderD:Date;
  orderId: number;
  shipping : number;
  voucherDiscount : number;
  subtotal : number;
  total : number;
  numberItems : number;
  ordersUser : BookUser;
  vouchersForUser:any;
  idLocatie:number;
  items : orderItem[];
  public constructor(init?: Partial<Book>) {
    Object.assign(this, init);
  }
}
