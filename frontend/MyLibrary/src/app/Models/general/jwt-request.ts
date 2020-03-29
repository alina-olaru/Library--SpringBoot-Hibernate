export class JwtRequest {
  username: string;
  password: string;
  public constructor(init?: Partial<JwtRequest>) {
    Object.assign(this, init);
  }
}
