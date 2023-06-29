package com.hackathon.going.search.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class WebClientConfig {

    private static final String BASE_URL = "https://openapi.naver.com";

    @Bean
    public WebClient webClient(){
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory(BASE_URL);
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.VALUES_ONLY);

        return WebClient.builder()
                .baseUrl(BASE_URL)
                .uriBuilderFactory(factory)
                .build();
    }

}