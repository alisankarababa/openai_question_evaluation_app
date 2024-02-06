package com.madtech.openai_question_evaluation_app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<RespExceptionOpenAi> hExceptionOpenAi(ExceptionOpenAi exceptionOpenAi) {

        RespExceptionOpenAi respExceptionOpenAi = new RespExceptionOpenAi(exceptionOpenAi.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(respExceptionOpenAi, exceptionOpenAi.getHttpStatus());
    }

    @ExceptionHandler
    public ResponseEntity<RespExceptionOpenAi> hException(Exception exception) {

        RespExceptionOpenAi respExceptionOpenAi = new RespExceptionOpenAi(exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(respExceptionOpenAi, HttpStatus.BAD_REQUEST);
    }
}
