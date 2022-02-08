package com.lgcns.icst.lecture.springjsp.lec2.frontController.v3;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class MethodNotAllowedException extends RuntimeException {

    public MethodNotAllowedException() {
        super("Method Not Allowed!");
    }

    public MethodNotAllowedException(String message) {
        super(message);
    }
}
