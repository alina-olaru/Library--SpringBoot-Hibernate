import { Address } from "./Address";

export class BookUser {
  userId: number;
  firstName: string;
  lastName: string;
  emailAdress: string;
  phoneNumber: string;
  newsletter: boolean;
  adminPrivilege: boolean;
  userPrivilege: boolean;
  blocked: boolean;
  username: string;
  password: string;
  isEnabled: boolean;
  addresses: Address[];
  bonus: number;
  public constructor(init?: Partial<BookUser>) {
    Object.assign(this, init);
  }
}
