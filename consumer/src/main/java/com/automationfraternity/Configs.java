package com.automationfraternity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configs {

    @Bean
    public RestTemplate getRestTemplate(@Value("${provider.url}") String root){
        return new RestTemplateBuilder().rootUri(root).build();
    }
}
