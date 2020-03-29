import { ApiResponseType } from './api-response-type.enum';
export class ApiResponse<T> {
  message: String;
  status: ApiResponseType;
  body: T;
}
