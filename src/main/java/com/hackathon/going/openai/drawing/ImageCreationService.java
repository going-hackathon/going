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

    public String creation(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + openAiApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

//        String requestBody = "{\"prompt\": \"A 20's Korean Man in Busan Gwanganri\"}";
        String requestBody = "{\"prompt\": \" + prompt + \"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(OPEN_AI_REQUEST_URI, requestEntity, String.class);
        return extractFirstImage(response.getBody());
    }

    private static String extractFirstImage(String response) {
        String start = "\"url\": \"";
        int startIdx = response.indexOf(start) + start.length();
        int endIdx = response.indexOf("\"", startIdx);
        return response.substring(startIdx, endIdx);
    }
}
