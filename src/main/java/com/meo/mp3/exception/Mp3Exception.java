package com.meo.mp3.exception;

import org.springframework.http.HttpStatus;

public class Mp3Exception extends RuntimeException {
    private HttpStatus errorStatus;


    public Mp3Exception(HttpStatus httpStatus, String errorMsg) {
        super(errorMsg);
        this.errorStatus = httpStatus;
    }

    public HttpStatus getErrorStatus() {
        return errorStatus;
    }
}
