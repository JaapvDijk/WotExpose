/* eslint-disable */
/* tslint:disable */
// @ts-nocheck
/*
 * ---------------------------------------------------------------
 * ## THIS FILE WAS GENERATED VIA SWAGGER-TYPESCRIPT-API        ##
 * ##                                                           ##
 * ## AUTHOR: acacode                                           ##
 * ## SOURCE: https://github.com/acacode/swagger-typescript-api ##
 * ---------------------------------------------------------------
 */

export interface UserRequest {
  fullName?: string;
  email?: string;
}

export interface UserResponse {
  /** @format int32 */
  id?: number;
  fullName?: string;
  email?: string;
}

export interface RegisterUserRequest {
  email?: string;
  /**
   * @minLength 8
   * @maxLength 2147483647
   */
  password?: string;
  fullName?: string;
}

export interface LoginRequest {
  email?: string;
  password?: string;
}

export interface LoginResponse {
  token?: string;
  /** @format int64 */
  expiresIn?: number;
}

export interface PageUserResponse {
  /** @format int64 */
  totalElements?: number;
  /** @format int32 */
  totalPages?: number;
  sort?: SortObject;
  /** @format int32 */
  size?: number;
  content?: UserResponse[];
  /** @format int32 */
  number?: number;
  first?: boolean;
  last?: boolean;
  /** @format int32 */
  numberOfElements?: number;
  pageable?: PageableObject;
  empty?: boolean;
}

export interface PageableObject {
  sort?: SortObject;
  /** @format int64 */
  offset?: number;
  paged?: boolean;
  /** @format int32 */
  pageNumber?: number;
  /** @format int32 */
  pageSize?: number;
  unpaged?: boolean;
}

export interface SortObject {
  empty?: boolean;
  sorted?: boolean;
  unsorted?: boolean;
}

export interface PlayerRequest {
  /**
   * @minLength 1
   * @pattern EU|NA|ASIA
   */
  region: string;
}

export interface BaseStatistics {
  /** @format int32 */
  spotted?: number;
  /** @format int32 */
  xp?: number;
  /** @format int32 */
  draws?: number;
  /** @format int32 */
  battles?: number;
  /** @format int32 */
  frags?: number;
  /** @format int32 */
  hits?: number;
  /** @format int32 */
  wins?: number;
  /** @format int32 */
  losses?: number;
  /** @format int32 */
  shots?: number;
  /** @format int32 */
  battles_on_stunning_vehicles?: number;
  /** @format int32 */
  track_assisted_damage?: number;
  /** @format int32 */
  survived_battles?: number;
  /** @format int32 */
  dropped_capture_points?: number;
  /** @format int32 */
  hits_percents?: number;
  /** @format int32 */
  damage_received?: number;
  /** @format int32 */
  stun_number?: number;
  /** @format int32 */
  capture_points?: number;
  /** @format int32 */
  stun_assisted_damage?: number;
  /** @format int32 */
  battle_avg_xp?: number;
  /** @format int32 */
  damage_dealt?: number;
  /** @format int32 */
  radio_assisted_damage?: number;
}

export interface ExtraStatistics {
  /** @format int32 */
  spotted?: number;
  /** @format int32 */
  xp?: number;
  /** @format int32 */
  draws?: number;
  /** @format int32 */
  battles?: number;
  /** @format int32 */
  frags?: number;
  /** @format int32 */
  hits?: number;
  /** @format int32 */
  wins?: number;
  /** @format int32 */
  losses?: number;
  /** @format int32 */
  shots?: number;
  /** @format int32 */
  battles_on_stunning_vehicles?: number;
  /** @format int32 */
  track_assisted_damage?: number;
  /** @format int32 */
  survived_battles?: number;
  /** @format int32 */
  dropped_capture_points?: number;
  /** @format int32 */
  hits_percents?: number;
  /** @format int32 */
  damage_received?: number;
  /** @format int32 */
  stun_number?: number;
  /** @format int32 */
  capture_points?: number;
  /** @format int32 */
  stun_assisted_damage?: number;
  /** @format int32 */
  battle_avg_xp?: number;
  /** @format int32 */
  damage_dealt?: number;
  /** @format int32 */
  radio_assisted_damage?: number;
  /** @format double */
  avg_damage_blocked?: number;
  /** @format double */
  avg_damage_assisted?: number;
  /** @format double */
  avg_damage_assisted_track?: number;
  /** @format double */
  avg_damage_assisted_radio?: number;
  /** @format double */
  avg_damage_assisted_stun?: number;
  /** @format double */
  tanking_factor?: number;
  /** @format int32 */
  direct_hits_received?: number;
  /** @format int32 */
  explosion_hits?: number;
  /** @format int32 */
  piercings_received?: number;
  /** @format int32 */
  no_damage_direct_hits_received?: number;
  /** @format int32 */
  explosion_hits_received?: number;
}

export interface PlayerTankStatResponse {
  company?: BaseStatistics;
  all?: ExtraStatistics;
  globalmap?: ExtraStatistics;
  frags?: any;
  /** @format int32 */
  account_id?: number;
  /** @format int32 */
  max_xp?: number;
  /** @format int32 */
  mark_of_mastery?: number;
  in_garage?: any;
  /** @format int32 */
  tank_id?: number;
}

export interface PlayerTankStatsResponse {
  data?: PlayerTankStatResponse[];
  totals?: StatTotals;
  notInEncyclopedia?: PlayerTankStatResponse[];
}

export interface StatTotals {
  /** @format int32 */
  battlesAll?: number;
  battlesPerType?: Record<string, number>;
  battlesPerTier?: Record<string, number>;
  battlesPerTypePercentage?: Record<string, number>;
  battlesPerTierPercentage?: Record<string, number>;
}

export interface PlayerSearchRequest {
  /**
   * @minLength 1
   * @pattern EU|NA|ASIA
   */
  region: string;
  /**
   * @minLength 3
   * @maxLength 2147483647
   */
  name: string;
}

export interface PlayerSearchResponse {
  nickname?: string;
  /** @format int32 */
  account_id?: number;
}

export interface Private {
  restrictions?: Restrictions;
  /** @format int32 */
  gold?: number;
  /** @format int32 */
  credits?: number;
  /** @format int32 */
  bonds?: number;
  /** @format int32 */
  free_xp?: number;
  ban_time?: any;
  is_bound_to_phone?: boolean;
  is_premium?: boolean;
  /** @format int32 */
  premium_expires_at?: number;
  /** @format int32 */
  battle_life_time?: number;
  ban_info?: any;
}

export interface Restrictions {
  chat_ban_time?: any;
}

export interface Statistics {
  clan?: BaseStatistics;
  all?: ExtraStatistics;
  company?: BaseStatistics;
  historical?: ExtraStatistics;
  team?: ExtraStatistics;
  regular_team?: ExtraStatistics;
  /** @format int32 */
  trees_cut?: number;
  stronghold_skirmish?: ExtraStatistics;
  stronghold_defense?: ExtraStatistics;
}

export interface WoTPlayerInfoResponse {
  statistics?: Statistics;
  nickname?: string;
  client_language?: string;
  /** @format int32 */
  last_battle_time?: number;
  /** @format int32 */
  account_id?: number;
  /** @format int32 */
  created_at?: number;
  /** @format int32 */
  updated_at?: number;
  private?: Private;
  /** @format int32 */
  global_rating?: number;
  /** @format int32 */
  clan_id?: number;
  /** @format int32 */
  logout_at?: number;
}

import type {
  AxiosInstance,
  AxiosRequestConfig,
  AxiosResponse,
  HeadersDefaults,
  ResponseType,
} from "axios";
import axios from "axios";

export type QueryParamsType = Record<string | number, any>;

export interface FullRequestParams
  extends Omit<AxiosRequestConfig, "data" | "params" | "url" | "responseType"> {
  /** set parameter to `true` for call `securityWorker` for this request */
  secure?: boolean;
  /** request path */
  path: string;
  /** content type of request body */
  type?: ContentType;
  /** query params */
  query?: QueryParamsType;
  /** format of response (i.e. response.json() -> format: "json") */
  format?: ResponseType;
  /** request body */
  body?: unknown;
}

export type RequestParams = Omit<
  FullRequestParams,
  "body" | "method" | "query" | "path"
>;

export interface ApiConfig<SecurityDataType = unknown>
  extends Omit<AxiosRequestConfig, "data" | "cancelToken"> {
  securityWorker?: (
    securityData: SecurityDataType | null,
  ) => Promise<AxiosRequestConfig | void> | AxiosRequestConfig | void;
  secure?: boolean;
  format?: ResponseType;
}

export enum ContentType {
  Json = "application/json",
  JsonApi = "application/vnd.api+json",
  FormData = "multipart/form-data",
  UrlEncoded = "application/x-www-form-urlencoded",
  Text = "text/plain",
}

export class HttpClient<SecurityDataType = unknown> {
  public instance: AxiosInstance;
  private securityData: SecurityDataType | null = null;
  private securityWorker?: ApiConfig<SecurityDataType>["securityWorker"];
  private secure?: boolean;
  private format?: ResponseType;

  constructor({
    securityWorker,
    secure,
    format,
    ...axiosConfig
  }: ApiConfig<SecurityDataType> = {}) {
    this.instance = axios.create({
      ...axiosConfig,
      baseURL: axiosConfig.baseURL || "http://localhost:5000",
    });
    this.secure = secure;
    this.format = format;
    this.securityWorker = securityWorker;
  }

  public setSecurityData = (data: SecurityDataType | null) => {
    this.securityData = data;
  };

  protected mergeRequestParams(
    params1: AxiosRequestConfig,
    params2?: AxiosRequestConfig,
  ): AxiosRequestConfig {
    const method = params1.method || (params2 && params2.method);

    return {
      ...this.instance.defaults,
      ...params1,
      ...(params2 || {}),
      headers: {
        ...((method &&
          this.instance.defaults.headers[
            method.toLowerCase() as keyof HeadersDefaults
          ]) ||
          {}),
        ...(params1.headers || {}),
        ...((params2 && params2.headers) || {}),
      },
    };
  }

  protected stringifyFormItem(formItem: unknown) {
    if (typeof formItem === "object" && formItem !== null) {
      return JSON.stringify(formItem);
    } else {
      return `${formItem}`;
    }
  }

  protected createFormData(input: Record<string, unknown>): FormData {
    if (input instanceof FormData) {
      return input;
    }
    return Object.keys(input || {}).reduce((formData, key) => {
      const property = input[key];
      const propertyContent: any[] =
        property instanceof Array ? property : [property];

      for (const formItem of propertyContent) {
        const isFileType = formItem instanceof Blob || formItem instanceof File;
        formData.append(
          key,
          isFileType ? formItem : this.stringifyFormItem(formItem),
        );
      }

      return formData;
    }, new FormData());
  }

  public request = async <T = any, _E = any>({
    secure,
    path,
    type,
    query,
    format,
    body,
    ...params
  }: FullRequestParams): Promise<AxiosResponse<T>> => {
    const secureParams =
      ((typeof secure === "boolean" ? secure : this.secure) &&
        this.securityWorker &&
        (await this.securityWorker(this.securityData))) ||
      {};
    const requestParams = this.mergeRequestParams(params, secureParams);
    const responseFormat = format || this.format || undefined;

    if (
      type === ContentType.FormData &&
      body &&
      body !== null &&
      typeof body === "object"
    ) {
      body = this.createFormData(body as Record<string, unknown>);
    }

    if (
      type === ContentType.Text &&
      body &&
      body !== null &&
      typeof body !== "string"
    ) {
      body = JSON.stringify(body);
    }

    return this.instance.request({
      ...requestParams,
      headers: {
        ...(requestParams.headers || {}),
        ...(type ? { "Content-Type": type } : {}),
      },
      params: query,
      responseType: responseFormat,
      data: body,
      url: path,
    });
  };
}

/**
 * @title OpenAPI definition
 * @version v0
 * @baseUrl http://localhost:5000
 */
export class Api<
  SecurityDataType extends unknown,
> extends HttpClient<SecurityDataType> {
  user = {
    /**
     * No description
     *
     * @tags user-controller
     * @name GetUserById
     * @request GET:/user/{id}
     */
    getUserById: (id: number, params: RequestParams = {}) =>
      this.request<UserResponse, any>({
        path: `/user/${id}`,
        method: "GET",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name UpdateUser
     * @request PUT:/user/{id}
     */
    updateUser: (id: number, data: UserRequest, params: RequestParams = {}) =>
      this.request<UserResponse, any>({
        path: `/user/${id}`,
        method: "PUT",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name DeleteUser
     * @request DELETE:/user/{id}
     */
    deleteUser: (id: number, params: RequestParams = {}) =>
      this.request<void, any>({
        path: `/user/${id}`,
        method: "DELETE",
        ...params,
      }),

    /**
     * No description
     *
     * @tags user-controller
     * @name GetUsers
     * @request GET:/user/page
     */
    getUsers: (
      query?: {
        /**
         * @format int32
         * @default 0
         */
        pageNr?: number;
        /**
         * @format int32
         * @default 20
         */
        amount?: number;
      },
      params: RequestParams = {},
    ) =>
      this.request<PageUserResponse, any>({
        path: `/user/page`,
        method: "GET",
        query: query,
        ...params,
      }),
  };
  auth = {
    /**
     * No description
     *
     * @tags authentication-controller
     * @name Register
     * @request POST:/auth/signup
     */
    register: (data: RegisterUserRequest, params: RequestParams = {}) =>
      this.request<UserResponse, any>({
        path: `/auth/signup`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),

    /**
     * No description
     *
     * @tags authentication-controller
     * @name Authenticate
     * @request POST:/auth/login
     */
    authenticate: (data: LoginRequest, params: RequestParams = {}) =>
      this.request<LoginResponse, any>({
        path: `/auth/login`,
        method: "POST",
        body: data,
        type: ContentType.Json,
        ...params,
      }),
  };
  player = {
    /**
     * No description
     *
     * @tags player-controller
     * @name GetTankStats
     * @request GET:/player/tanks/{id}
     */
    getTankStats: (
      id: number,
      query: {
        request: PlayerRequest;
      },
      params: RequestParams = {},
    ) =>
      this.request<PlayerTankStatsResponse, any>({
        path: `/player/tanks/${id}`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags player-controller
     * @name Search
     * @request GET:/player/search
     */
    search: (
      query: {
        request: PlayerSearchRequest;
      },
      params: RequestParams = {},
    ) =>
      this.request<PlayerSearchResponse[], any>({
        path: `/player/search`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags player-controller
     * @name GetInfo
     * @request GET:/player/info/{id}
     */
    getInfo: (
      id: number,
      query: {
        request: PlayerRequest;
      },
      params: RequestParams = {},
    ) =>
      this.request<WoTPlayerInfoResponse, any>({
        path: `/player/info/${id}`,
        method: "GET",
        query: query,
        ...params,
      }),
  };
  heartbeat = {
    /**
     * No description
     *
     * @tags heartbeat-controller
     * @name Get
     * @request GET:/heartbeat
     */
    get: (params: RequestParams = {}) =>
      this.request<string, any>({
        path: `/heartbeat`,
        method: "GET",
        ...params,
      }),
  };
  admin = {
    /**
     * No description
     *
     * @tags admin-controller
     * @name DoImportVehicles
     * @request GET:/admin/import/vehicle
     */
    doImportVehicles: (
      query: {
        region: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<string, any>({
        path: `/admin/import/vehicle`,
        method: "GET",
        query: query,
        ...params,
      }),

    /**
     * No description
     *
     * @tags admin-controller
     * @name DoImportTomato
     * @request GET:/admin/import/tomato
     */
    doImportTomato: (
      query: {
        region: string;
      },
      params: RequestParams = {},
    ) =>
      this.request<string, any>({
        path: `/admin/import/tomato`,
        method: "GET",
        query: query,
        ...params,
      }),
  };
}
