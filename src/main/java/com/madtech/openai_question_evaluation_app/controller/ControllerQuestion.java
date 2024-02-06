package com.madtech.openai_question_evaluation_app.controller;

import com.madtech.openai_question_evaluation_app.service.IServiceGptCompletions;
import com.madtech.openai_question_evaluation_app.dto.ReqQuestion;
import com.madtech.openai_question_evaluation_app.dto.ResQuestion;
import com.madtech.openai_question_evaluation_app.service.IServiceQuestion;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerQuestion {

    @Autowired
    private IServiceQuestion serviceQuestion;
    @Autowired
    private IServiceGptCompletions serviceGptCompletions;

    @PostMapping("/question")
    public ResQuestion create(@Valid @RequestBody ReqQuestion reqQuestion) {

        return serviceQuestion.create(reqQuestion);
    }
    @GetMapping("/question/all")
    public List<ResQuestion> readAll() {
        return serviceQuestion.readAll();
    }
    @GetMapping("/question/{id}")
    public ResQuestion readById(@PathVariable long id) {
        return serviceQuestion.readById(id);
    }
    @PutMapping("/question/{id}")
    public ResQuestion update(@PathVariable long id, @Valid @RequestBody ReqQuestion reqQuestion ) {
        return serviceQuestion.update(id, reqQuestion);
    }
    @DeleteMapping("question/{id}")
    public ResQuestion delete(@PathVariable long id) {
        return serviceQuestion.delete(id);
    }
}