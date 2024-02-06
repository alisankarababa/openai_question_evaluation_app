package com.madtech.openai_question_evaluation_app.exception;

import java.time.LocalDateTime;

public record RespExceptionOpenAi(String message, LocalDateTime localDateTime) {
}
