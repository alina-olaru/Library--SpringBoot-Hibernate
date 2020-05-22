import { BookUser } from 'src/app/Models/BookUser';
export class Complaint {

  complaintId:number;
  text:string;
  subject:string;
  complaintImageDb:any;
  complaintImage:any;
  com:BookUser;
  public constructor(init?: Partial<Complaint>)
  {
    Object.assign(this, init);
  }
}
