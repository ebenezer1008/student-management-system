package com.student.management.exception;

public class NoDataFoundException extends RuntimeException {
    private String message;
    public NoDataFoundException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
