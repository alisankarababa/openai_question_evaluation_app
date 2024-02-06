package com.madtech.openai_question_evaluation_app.validation_message_patterns;

public class Msg {
    private Msg() {}
    public static final String notEmpty = "cannot be empty.";
    public static final String notNull = "cannot be null.";
    public static final String maxLenStr = "character count cannot be greater than";
    public static final String minLenStr = "character count cannot be less than";
    public static final String idCannotBeFound = "with id %d cannot be found.";
}
