export class JwtResponse {
  jwttoken: string;
  public constructor(init?: Partial<JwtResponse>) {
    Object.assign(this, init);
  }
}
