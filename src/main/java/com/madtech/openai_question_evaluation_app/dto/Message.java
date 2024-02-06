package com.madtech.openai_question_evaluation_app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private eRole role;
    private String content;
}
