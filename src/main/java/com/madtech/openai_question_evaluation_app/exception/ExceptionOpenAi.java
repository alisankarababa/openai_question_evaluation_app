package com.madtech.openai_question_evaluation_app.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
public class ExceptionOpenAi extends RuntimeException{

    private HttpStatus httpStatus;
    public ExceptionOpenAi(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
