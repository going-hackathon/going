package com.hackathon.going.search.service;

import com.hackathon.going.search.response.LocationInfoListResponse;
import com.hackathon.going.search.response.naver.NaverSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class NaverSearchService implements SearchService {

    @Value("${naver.client.id}")
    private String NAVER_CLIENT_ID;
    @Value("${naver.client.secret}")
    private String NAVER_CLIENT_SECRET;

    public LocationInfoListResponse searchLocation(String keyword) {
        WebClient webClient = WebClient.builder().baseUrl("https://openapi.naver.com").build();

        Mono<NaverSearchResponse> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/local.json")
                        .queryParam("query", keyword)
                        .queryParam("display", 10)
                        .queryParam("term", keyword)
                        .queryParam("start", 1)
                        .queryParam("sort", "random")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "X-Naver-Client-Id " + NAVER_CLIENT_ID)
                .header(HttpHeaders.AUTHORIZATION, "X-Naver-Client-Secret " + NAVER_CLIENT_SECRET)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(NaverSearchResponse.class);
        return LocationInfoListResponse.of(response.block());
    }

}
