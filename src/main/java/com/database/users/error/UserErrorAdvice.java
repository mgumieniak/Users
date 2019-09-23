package com.database.users.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class UserErrorAdvice {

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String>handleRunTimeException(RuntimeException ex){
        return error(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<String>handleUserNotFoundException(UserNotFoundException ex){
        return error(HttpStatus.NOT_FOUND, ex);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
