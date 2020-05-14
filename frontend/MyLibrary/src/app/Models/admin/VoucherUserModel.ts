import { BookOrder } from 'src/app/Models/cart/Order';
import { Voucher } from './VoucherModel';
import { BookUser } from './../BookUser';
export class VoucherUser {
  usersWithVouchers: BookUser;
  vouchers: Voucher;
  used: boolean;
  orderWithVouchers:BookOrder[];

  public constructor(init?: Partial<VoucherUser>) {
    Object.assign(this, init);
  }
}
