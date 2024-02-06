package com.madtech.openai_question_evaluation_app.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestClientConfigOpenAi {
    @Value("${openai.api.v1.key}")
    private String openApiKey;
    @Bean
    public RestTemplate restTemplate() {

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.getInterceptors().add( (request, body, execution) -> {
            request.getHeaders().add("Authorization", "Bearer " + this.openApiKey);
            return execution.execute(request, body);
        });

        return restTemplate;
    }
}