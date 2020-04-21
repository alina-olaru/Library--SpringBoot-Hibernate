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
  authorlastName:string;
  authorFirstName:string;
  language:string;
  Publisher:string;


  public constructor(init?: Partial<Voucher>) {
    Object.assign(this, init);
  }

}
