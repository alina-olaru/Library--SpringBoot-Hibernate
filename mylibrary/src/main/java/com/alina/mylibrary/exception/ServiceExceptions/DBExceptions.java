package com.alina.mylibrary.exception.ServiceExceptions;

public class DBExceptions extends Exception {


    public String message;
    public Integer code;
    public String className;
    public String field;
    public String operationAffected;

    public DBExceptions(String message, Integer code, String className, String field, String operationAffected) {
        this.message = message;
        this.code = code;
        this.className = className;
        this.field = field;
        this.operationAffected = operationAffected;
    }

    public DBExceptions(String message, String message1, Integer code, String className, String field, String operationAffected) {
        super(message);
        this.message = message1;
        this.code = code;
        this.className = className;
        this.field = field;
        this.operationAffected = operationAffected;
    }

    public DBExceptions(String message, Throwable cause, String message1, Integer code, String className, String field, String operationAffected) {
        super(message, cause);
        this.message = message1;
        this.code = code;
        this.className = className;
        this.field = field;
        this.operationAffected = operationAffected;
    }

    public DBExceptions(Throwable cause, String message, Integer code, String className, String field, String operationAffected) {
        super(cause);
        this.message = message;
        this.code = code;
        this.className = className;
        this.field = field;
        this.operationAffected = operationAffected;
    }

    public DBExceptions(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1, Integer code, String className, String field, String operationAffected) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
        this.className = className;
        this.field = field;
        this.operationAffected = operationAffected;
    }
}
