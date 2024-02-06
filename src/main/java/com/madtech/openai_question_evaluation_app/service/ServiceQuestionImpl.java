package com.madtech.openai_question_evaluation_app.service;

import com.madtech.openai_question_evaluation_app.entity.Question;
import com.madtech.openai_question_evaluation_app.dto.ReqQuestion;
import com.madtech.openai_question_evaluation_app.dto.ResQuestion;
import com.madtech.openai_question_evaluation_app.exception.ExceptionOpenAi;
import com.madtech.openai_question_evaluation_app.repository.IRepoQuestion;
import com.madtech.openai_question_evaluation_app.validation_message_patterns.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceQuestionImpl implements IServiceQuestion{

    private IRepoQuestion repoQuestion;

    @Autowired
    public ServiceQuestionImpl(IRepoQuestion repoQuestion) {
        this.repoQuestion = repoQuestion;
        Question question1 = new Question();
        question1.setText("JS DOM'da dinamik olarak bir liste elemanı nasıl oluşturulur? Hangi adımları izlersin?");
        repoQuestion.save(question1);

        Question question2 = new Question();
        question2.setText("Java da neden pointerlar kullanılmıyor?");
        repoQuestion.save(question2);

        Question question3 = new Question();
        question3.setText("Nesne yönelimli programlama (OOP) kavramını nasıl açıklarsınız? Bu programlama yaklaşımının temel unsurları nelerdir?");
        repoQuestion.save(question3);

        Question question4 = new Question();
        question4.setText("React'ın avantajları ve sınırlamaları nelerdir? Bu konuda bize detaylı bilgi verebilir misin?");
        repoQuestion.save(question4);

        Question question5 = new Question();
        question5.setText("Back-end tarafında hangi SQL veri tipleriyle çalıştın? Bu veri tiplerini kullanım alanlarıyla birlikte anlatabilir misin?");
        repoQuestion.save(question5);

        Question question6 = new Question();
        question6.setText("Value Object ve Reference Object arasındaki farkları anlatabilir misin? Bu konuda ne düşünüyorsun?");
        repoQuestion.save(question6);

        Question question7 = new Question();
        question7.setText("İlişkisel ve NoSQL veritabanları arasındaki temel farkları açıklayabilir misin?");
        repoQuestion.save(question7);

        Question question8 = new Question();
        question8.setText("Polymorphism'in sağladığı avantajları açıklar mısın? Bu kavramın uygulamadaki önemini anlatır mısın?");
        repoQuestion.save(question8);

        Question question9 = new Question();
        question9.setText("Bir HTML elemanını JavaScript kullanarak nasıl seçebilirsin?");
        repoQuestion.save(question9);
    }

    @Override
    public ResQuestion create(ReqQuestion reqQuestion) {

        Question question = Question.convert(reqQuestion);
        return Question.convert(repoQuestion.save(question));
    }

    @Override
    public ResQuestion delete(long id) {

        Question question = findQuestionById(id);

        repoQuestion.deleteById(id);
        return Question.convert(question);
    }

    @Override
    public ResQuestion readById(long id) {
        Question question = findQuestionById(id);
        return Question.convert(question);
    }

    @Override
    public List<ResQuestion> readAll() {
        List<Question> listQuestion = repoQuestion.findAll();
        return Question.convert(listQuestion);
    }

    @Override
    public ResQuestion update(long id, ReqQuestion reqQuestion) {

        Question question = Question.convert(reqQuestion);
        question.setId(id);

        return Question.convert(repoQuestion.save(question));
    }

    @Override
    public Question findQuestionById(long id) {
        return repoQuestion.findById(id)
                .orElseThrow(() -> new ExceptionOpenAi(
                        String.format("Question " + Msg.idCannotBeFound, id),
                        HttpStatus.NOT_FOUND
                ));
    }
}
