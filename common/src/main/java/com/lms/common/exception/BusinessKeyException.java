package com.lms.common.exception;

public class BusinessKeyException extends RuntimeException{

    private String id;
    private String message;
    private BusinessKeyException(ExceptionCode code, String message) {

    }

    public static BusinessKeyException invalidBusinessKey(ExceptionCode code, String[] message) {
        return new BusinessKeyException(code, message.toString());
    }
}
