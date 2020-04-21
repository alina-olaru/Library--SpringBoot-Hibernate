import { Author } from 'src/app/Models/admin/AuthorModel';
import { Publisher } from 'src/app/Models/admin/PublisherModel';
export class Voucher {

  voucherId:number;
  voucherTitle:string;
  voucherDescription:string;
  voucherImage:string;
  voucherImageSrc?: any;
  voucherStartDate:Date;
  voucherEndDate:Date;
  voucherMaximumUses:number;
  voucherPrice:number;
  quizzez:any;
  userVoucherLink:any;

  language:string;
  publisher_voucher:Publisher;
  author_voucher:Author;

  public constructor(init?: Partial<Voucher>) {
    Object.assign(this, init);
  }

}
