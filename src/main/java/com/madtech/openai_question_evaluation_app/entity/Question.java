package com.madtech.openai_question_evaluation_app.entity;

import com.madtech.openai_question_evaluation_app.dto.ReqQuestion;
import com.madtech.openai_question_evaluation_app.dto.ResQuestion;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
public class Question {

    private Long id;

    private String text;

    public static Question convert(ReqQuestion reqQuestion) {

        Question question = new Question();
        question.setText(reqQuestion.getText());
        return question;
    }

    public static ResQuestion convert(Question question) {

        ResQuestion resQuestion = new ResQuestion();
        resQuestion.setId(question.getId());
        resQuestion.setText(question.getText());
        return resQuestion;
    }

    public static List<ResQuestion> convert(List<Question> questionList) {

        List<ResQuestion> resQuestionList = new ArrayList<>();

        questionList.forEach(question ->
                resQuestionList.add(
                        Question.convert(question)
                ));

        return resQuestionList;
    }

}
