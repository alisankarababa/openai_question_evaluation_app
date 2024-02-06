package com.madtech.openai_question_evaluation_app.dto;


import com.madtech.openai_question_evaluation_app.validation_message_patterns.Msg;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Validated
@NoArgsConstructor
@Data
public class ReqCompletions {

    @NotNull(message = "Prompt " + Msg.notNull)
    @NotEmpty(message = "Prompt " + Msg.notEmpty)
    private String prompt;
}
