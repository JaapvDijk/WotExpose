import { jwtDecode } from "jwt-decode";
import { store } from "./redux/store";
import { Api } from "./__generated__/Api";
import { authThunks } from "./redux/auth";
import { AccesJwtPayload } from "./types/AccesJwtPayload";

const authenticatedFetch: WindowOrWorkerGlobalScope['fetch'] = async (input: RequestInfo, init?: RequestInit) => {
    const token = store.getState().auth.token;

    if (UserIsValid(token)) {
        const customHeaders = {
            'Authorization': `Bearer ${token}`,
        };

        if (init && init.headers) {
            init.headers = new Headers(init.headers);
            Object.entries(customHeaders).forEach(([key, value]) => {
                (init!.headers as Headers).append(key, value);
            });
        } else {
            init = { ...init, headers: customHeaders };
        }
    }
    else {
        store.dispatch(authThunks.logout());
    }

    return fetch(input, init);
};

export class ApiClient extends Api<String | null> {
  private static authInstance: ApiClient;
  private static publicInstance: ApiClient;

  private constructor() {
    super();
  }

  static getAuthInstance() {
    if (!this.authInstance) {
      this.authInstance = new Api({
        baseURL: "http://localhost:5000",
        secure: true,
        securityWorker: () => {
          const token = store.getState().auth.token;
          return UserIsValid(token)
            ? { headers: { Authorization: `Bearer ${token}` } }
            : {};
        },
      });
    }
    return this.authInstance;
  }

  static getInstance() {
    if (!this.publicInstance) {
      this.publicInstance = new Api({
        baseURL: "http://localhost:5000",
      });
    }
    return this.publicInstance;
  }
}

export function UserIsValid(token: string) {
    if (token.length < 20)
        return false;

    var decodedToken = jwtDecode<AccesJwtPayload>(token);
    return decodedToken.exp! > new Date().getTime() / 1000;
}
