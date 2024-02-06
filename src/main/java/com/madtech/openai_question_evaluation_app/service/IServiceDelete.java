package com.madtech.openai_question_evaluation_app.service;

public interface IServiceDelete<T_Response>{
    T_Response delete(long id);
}
