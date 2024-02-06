package com.madtech.openai_question_evaluation_app.service;

import com.madtech.openai_question_evaluation_app.entity.Question;
import com.madtech.openai_question_evaluation_app.dto.ReqQuestion;
import com.madtech.openai_question_evaluation_app.dto.ResQuestion;

public interface IServiceQuestion extends
        IServiceCreate<ResQuestion, ReqQuestion>,
        IServiceRead<ResQuestion>,
        IServiceUpdate<ResQuestion, ReqQuestion>,
        IServiceDelete<ResQuestion> {

    Question findQuestionById(long id);


}
