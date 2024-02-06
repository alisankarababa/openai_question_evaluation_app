package com.madtech.openai_question_evaluation_app.service;

import com.madtech.openai_question_evaluation_app.entity.Question;
import com.madtech.openai_question_evaluation_app.dto.ReqCompletions;
import com.madtech.openai_question_evaluation_app.dto.ResCompletions;

public interface IServiceGptCompletions {

    ResCompletions evaluateUserAnswer(Question question, ReqCompletions reqCompletions);

}
