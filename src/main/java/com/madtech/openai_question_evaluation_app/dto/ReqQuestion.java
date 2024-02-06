package com.madtech.openai_question_evaluation_app.dto;

import com.madtech.openai_question_evaluation_app.validation_message_patterns.Msg;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
public class ReqQuestion {

    @NotNull(message = "Text " + Msg.notNull)
    @NotEmpty(message = "Text " + Msg.notEmpty)
    @Length(min = 10, message = "Text " + Msg.minLenStr + " 10")
    @Length(max = 100, message = "Text " + Msg.maxLenStr + " 100")
    private String text;

}
