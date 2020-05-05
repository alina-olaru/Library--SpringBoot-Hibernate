package com.alina.mylibrary.exception;

public class QueryCustomException extends Exception {
    private String message;
    private String error_name;
    private String query_affected;
    private String className;
    private String operation;


    public QueryCustomException(String message, String error_name, String query_affected, String className, String operation) {
        this.message = message;
        this.error_name = error_name;
        this.query_affected = query_affected;
        this.className = className;
        this.operation = operation;
    }

    public QueryCustomException(String message, String message1, String error_name, String query_affected, String className, String operation) {
        super(message);
        this.message = message1;
        this.error_name = error_name;
        this.query_affected = query_affected;
        this.className = className;
        this.operation = operation;
    }

    public QueryCustomException(String message, Throwable cause, String message1, String error_name, String query_affected, String className, String operation) {
        super(message, cause);
        this.message = message1;
        this.error_name = error_name;
        this.query_affected = query_affected;
        this.className = className;
        this.operation = operation;
    }

    public QueryCustomException(Throwable cause, String message, String error_name, String query_affected, String className, String operation) {
        super(cause);
        this.message = message;
        this.error_name = error_name;
        this.query_affected = query_affected;
        this.className = className;
        this.operation = operation;
    }

    public QueryCustomException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, String error_name, String query_affected, String className, String operation) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.error_name = error_name;
        this.query_affected = query_affected;
        this.className = className;
        this.operation = operation;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_name() {
        return error_name;
    }

    public void setError_name(String error_name) {
        this.error_name = error_name;
    }

    public String getQuery_affected() {
        return query_affected;
    }

    public void setQuery_affected(String query_affected) {
        this.query_affected = query_affected;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}
