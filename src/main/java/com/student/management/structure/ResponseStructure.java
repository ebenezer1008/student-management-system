package com.student.management.structure;

public class ResponseStructure<T> {

    private String message;
    private int httpStatus;
    private Object data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseStructure{" +
                "message='" + message + '\'' +
                ", httpStatus=" + httpStatus +
                ", data=" + data +
                '}';
    }
}
