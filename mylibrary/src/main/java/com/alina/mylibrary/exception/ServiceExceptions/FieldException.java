package com.alina.mylibrary.exception.ServiceExceptions;

public class FieldException extends Exception {

    String message;
    String field;
    String className;

    public FieldException(String message, String field, String className) {
        this.message = message;
        this.field = field;
        this.className = className;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
