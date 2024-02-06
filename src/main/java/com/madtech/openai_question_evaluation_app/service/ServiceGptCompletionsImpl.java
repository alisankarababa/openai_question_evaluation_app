package com.madtech.openai_question_evaluation_app.service;

import com.madtech.openai_question_evaluation_app.dto.*;
import com.madtech.openai_question_evaluation_app.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceGptCompletionsImpl implements IServiceGptCompletions{

    @Value("${openai.api.v1.completions.model}")
    private String model;
    @Value("${openai.api.v1.completions.url}")
    private String urlOpenAiV1CompletionsUrl;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public ResCompletions evaluateUserAnswer(Question question, ReqCompletions reqCompletions) {

        //prepare system message
        Message messageSystem = new Message();
        messageSystem.setRole(eRole.system);
        messageSystem.setContent("First work out your own solution to the problem. " +
                "Then compare your solution to the student's solution and evaluate if the student's solution" +
                " is correct or not give the result as Result: . " +
                "Don't decide if the student's solution is correct until you have done the problem yourself.");

        //prepare user message

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Problem Statement: ");
        stringBuilder.append(question.getText());
        stringBuilder.append("Student Solution: ");
        stringBuilder.append(reqCompletions.getPrompt());

        Message messageUser = new Message();
        messageUser.setRole(eRole.user);
        messageUser.setContent(stringBuilder.toString());

        List<Message> messageList = new ArrayList<>();
        messageList.add(messageSystem);
        messageList.add(messageUser);

        ReqCGPTCompletions requestChatGPT = new ReqCGPTCompletions(model, messageList);

        ResCGPTCompletions responseChatGPT = restTemplate.postForObject(urlOpenAiV1CompletionsUrl, requestChatGPT, ResCGPTCompletions.class);

        ResCompletions resCompletions = new ResCompletions();
        resCompletions.setContent(responseChatGPT.getChoices().get(0).getMessage().getContent());

        return resCompletions;
    }
}
