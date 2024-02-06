package com.madtech.openai_question_evaluation_app.repository;

import com.madtech.openai_question_evaluation_app.entity.Question;

import java.util.List;
import java.util.Optional;

public interface IRepoQuestion {
    Question save(Question question);
    void deleteById(long id);
    List<Question> findAll();
    Optional<Question> findById(long id);
}
