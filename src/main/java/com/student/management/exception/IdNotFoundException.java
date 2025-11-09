package com.student.management.exception;

public class IdNotFoundException extends RuntimeException {
    private String message;
    public IdNotFoundException(String s) {
        super(s);
        this.message = s;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
