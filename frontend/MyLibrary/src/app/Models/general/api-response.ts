import { ApiResponseType } from './api-response-type.enum';
export class ApiResponse<T> {
  message: string;
  status: ApiResponseType;
  body: T;
}
