package com.lms.common.exception;

public enum ExceptionCode {

    ISBN("10001");

    private String code;
    private ExceptionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public ExceptionCode getExceptionCode(String code) {
        return ExceptionCode.valueOf(code);
    }
}
