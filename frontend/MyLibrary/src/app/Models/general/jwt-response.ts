import { BookUser } from './../BookUser';
export class JwtResponse {
  jwttoken: string;
  bookUser: BookUser;
  public constructor(init?: Partial<JwtResponse>) {
    Object.assign(this, init);
  }
}
