package com.automationfraternity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configs {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
