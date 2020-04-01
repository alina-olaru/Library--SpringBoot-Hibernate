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


  public constructor(init?: Partial<Voucher>) {
    Object.assign(this, init);
  }

}
