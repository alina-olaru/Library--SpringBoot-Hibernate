import { Category } from "./CategoryModel";
import { Author } from "src/app/Models/admin/AuthorModel";
import { Publisher } from "src/app/Models/admin/PublisherModel";
import { VoucherUser } from './VoucherUserModel';
import { Quizz } from './QuizzModel';
export class Voucher {
  voucherId: number;
  voucherTitle: string;
  voucherDescription: string;
  voucherImageDb?: string;
  voucherImageSrc?: any;
  voucherStartDate: Date;
  voucherEndDate: Date;
  voucherMaximumUses: number;
  voucherPrice: number;
  userVoucherLink: VoucherUser[];

  language: string;
  publisher_voucher: Publisher;
  author_voucher: Author;
  category_voucher: Category;

  public constructor(init?: Partial<Voucher>) {
    Object.assign(this, init);
  }
}
