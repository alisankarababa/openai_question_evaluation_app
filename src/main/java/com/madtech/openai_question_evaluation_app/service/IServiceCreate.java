package com.madtech.openai_question_evaluation_app.service;

public interface IServiceCreate<T_Response, T_Request>{
    T_Response create(T_Request request);
}
