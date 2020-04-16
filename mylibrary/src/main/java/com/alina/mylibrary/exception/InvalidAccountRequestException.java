package com.alina.mylibrary.exception;

public class InvalidAccountRequestException extends Exception {
    public String message;

    public InvalidAccountRequestException() {
        this.message = "Date invalide";
    }

    public InvalidAccountRequestException(String message) {
        this.message = message;
    }

    public InvalidAccountRequestException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public InvalidAccountRequestException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public InvalidAccountRequestException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public InvalidAccountRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
