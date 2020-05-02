import { Voucher } from './VoucherModel';
import { BookUser } from './../BookUser';
export class VoucherUser {
  usersWithVouchers: BookUser;
  vouchers: Voucher;
  used: boolean;

  public constructor(init?: Partial<VoucherUser>) {
    Object.assign(this, init);
  }
}
