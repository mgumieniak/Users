package com.database.users.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserErrorAdvice {

//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<String>handleRunTimeException(RuntimeException ex){
//        return error(HttpStatus.INTERNAL_SERVER_ERROR, ex);
//    }

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Error>handleUserNotFoundException(UserNotFoundException ex){
        return error(HttpStatus.NOT_FOUND, ex, "UserNotFoundException.class");
    }

    private ResponseEntity<Error> error(HttpStatus status, Exception e, String exceptionName) {
        log.error("Exception : ", e);
        Error error = Error.createError(status.value(),
                e.getMessage(), exceptionName);
        return ResponseEntity.status(status).body(error);
    }
}
