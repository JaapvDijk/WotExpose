import { JwtPayload } from "jwt-decode";

export interface AccessJwtPayload extends JwtPayload {
  roles: JwtRole[];
}

export interface JwtRole {
  authority: string;
}
