import { jwtDecode } from "jwt-decode";
import { store, RootState } from "./redux/store";
import { Api } from "./__generated__/Api";
import { AccessJwtPayload } from "./types/AccessJwtPayload";
import { authThunks } from "./redux/auth";
import { useSelector } from "react-redux";

export class ApiClient extends Api<string | null> {
  private static authInstance: ApiClient;
  private static publicInstance: ApiClient;

  private static baseUrl: string = "http://localhost:5000";

  private constructor(config?: any) {
    super(config);
  }

  static getAuthInstance() {
    if (!this.authInstance) {
      this.authInstance = new ApiClient({
        baseURL: ApiClient.baseUrl,
        secure: true,
        securityWorker: () => {
          const token = store.getState().auth.token;

          if (UserIsValid(token)) {
            return { headers: { Authorization: `Bearer ${token}` } };
          } 
          else {
            store.dispatch(authThunks.logout());
            return {};
          }
        },
      });
    }
    return this.authInstance;
  }

  static getInstance() {
    if (!this.publicInstance) {
      this.publicInstance = new ApiClient({
        baseURL: ApiClient.baseUrl
      });
    }
    return this.publicInstance;
  }
}

export function UserIsValid(token?: string) {
  if (!token || token.length < 20) return false;
  const decodedToken = jwtDecode<AccessJwtPayload>(token);
  return decodedToken.exp! > new Date().getTime() / 1000;
}

export function UserIsAdmin(token?: string) {
  if (!token) return false;

  if (!UserIsValid(token)) return false;

  try {
    const decoded = jwtDecode<AccessJwtPayload>(token);
    const hasAdminRole = decoded.roles.some(
      (role) => role.authority === "ROLE_ADMIN"
    );

    return hasAdminRole;
  } catch {
    return false;
  }
}