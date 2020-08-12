package com.meo.mp3.controllers;

import com.meo.mp3.exception.Mp3Exception;
import com.meo.mp3.response.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.Map;

public class BaseController {
    HttpStatus status;
    Map<String, Object> body;

    @Autowired
    private BaseResponse response;

    void success(Object data) {
        status = HttpStatus.OK;
        body = response.success(data);
    }

    void unSuccess(Mp3Exception e) {
        status = e.getErrorStatus();
        body = response.unSuccess(e.getMessage());
    }
}
