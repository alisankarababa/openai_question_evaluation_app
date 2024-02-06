package com.madtech.openai_question_evaluation_app.dto;

public enum eResponseFormat {
    TEXT("text"),
    JSON_OBJECT("json_object");

    private String value;

    eResponseFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
