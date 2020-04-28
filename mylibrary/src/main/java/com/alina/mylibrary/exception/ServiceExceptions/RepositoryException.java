package com.alina.mylibrary.exception.ServiceExceptions;

public class RepositoryException extends Exception {
    public String message;
    public Integer code;
    public String className;
    public String field;
    public String operationAffected;

    public RepositoryException(String message) {
        this.message = message;
    }

    public RepositoryException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public RepositoryException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public RepositoryException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public RepositoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
