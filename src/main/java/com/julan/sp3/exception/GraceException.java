package com.julan.sp3.exception;

public class GraceException extends RuntimeException {

    public int code;

    public GraceException(String error, int code) {
        super(error);
        this.code = code;
    }

    public static void display(String error) {
        throw new GraceException(error, 500);
    }

    public static void display(String error, int code) {
        throw new GraceException(error, code);
    }
}
