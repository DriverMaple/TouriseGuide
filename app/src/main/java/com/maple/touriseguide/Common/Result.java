package com.maple.touriseguide.Common;

public class Result {
    public static Byte FAIL = 1;
    public static Byte SUCCESS = 0;
    private Byte result;
    private Object value;
    private String message;

    public Byte getResult() {
        return result;
    }

    public void setResult(Byte result) {
        this.result = result;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
