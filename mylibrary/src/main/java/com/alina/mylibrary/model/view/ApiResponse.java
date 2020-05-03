package com.alina.mylibrary.model.view;

import lombok.Data;

@Data
public class ApiResponse<T> {
    private String message;
    private ApiResponseType status;
    private T body;

    public ApiResponse(ApiResponseType status, T body) {
        this.message = "";
        this.status = status;
        this.body = body;
    }

    public ApiResponse(ApiResponseType status, T body,String message) {
        this.message = message;
        this.status = status;
        this.body = body;
    }
}
