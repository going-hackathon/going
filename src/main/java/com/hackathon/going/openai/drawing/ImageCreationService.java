package com.hackathon.going.openai.drawing;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ImageCreationService {

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private final String OPEN_AI_REQUEST_URI = "https://api.openai.com/v1/images/generations";

    private final RestTemplate restTemplate;

    public String creation() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + openAiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestBody = "{\"prompt\": \"A 20's Korean Man in Busan Gwanganri\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPEN_AI_REQUEST_URI, requestEntity, String.class);
        return response.getBody();
    }
}
