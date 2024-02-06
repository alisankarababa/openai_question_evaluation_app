package com.madtech.openai_question_evaluation_app.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Choice {

    private int index;
    private Message message;
}
