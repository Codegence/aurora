package com.codegence.aurora.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by lmorganti on 04/03/16.
 */
@Slf4j
@ControllerAdvice
public class GlobalErrorHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MensajeDTO> handleStatusCodeException(StatusCodeException e) {
        if (e.getStatusCode().is5xxServerError())
            log.error("Error interno atrapado", e);
        return new ResponseEntity<>(new MensajeDTO(e.getStatusText()), e.getStatusCode());
    }

}
