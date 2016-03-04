package com.codegence.aurora.config;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Created by lmorganti on 04/03/16.
 */
public class StatusCodeException extends HttpStatusCodeException {
    @Getter
    private Exception exception;

    public StatusCodeException(HttpStatus statusCode) {
        super(statusCode);
    }

    public StatusCodeException(HttpStatus statusCode, Exception e) {
        super(statusCode);
        exception = e;
    }

}
