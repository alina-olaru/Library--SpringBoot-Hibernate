import { Address } from 'cluster';

export class BookUser {
  userId: number;
  firstName: String;
  lastName: String;
  emailAdress: String;
  phoneNumber: String;
  newsletter: boolean;
  adminPrivilege: boolean;
  userPrivilege: boolean;
  blocked: boolean;
  username: String;
  password: String;
  isEnabled:boolean;
  addresses:Address[];
  public constructor(init?: Partial<BookUser>) {
    Object.assign(this, init);
  }
}
