package com.madtech.openai_question_evaluation_app.repository;

import com.madtech.openai_question_evaluation_app.entity.Question;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class RepoQuestionImpl implements IRepoQuestion {

    private final Map<Long, Question> questionMap = new HashMap<>();
    private long id = 1L;

    private long generateId() {

        return id++;
    }

    @Override
    public Question save(Question question) {

        Long id = question.getId();

        if(id == null) {
            id = generateId();
            question.setId(id);
            questionMap.put(id, question);
        } else {
            questionMap.put(id, question);
        }

        return questionMap.get(id);
    }

    @Override
    public void deleteById(long id) {
        questionMap.remove(id);
    }

    @Override
    public List<Question> findAll() {

        return new ArrayList<>(questionMap.values());
    }

    @Override
    public Optional<Question> findById(long id) {
        Question foundQuestion = questionMap.get(id);
        if(foundQuestion != null)
            return Optional.of(foundQuestion);

        return Optional.empty();
    }
}
