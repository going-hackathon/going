package com.hackathon.going.search.service;

import com.hackathon.going.search.config.WebClientConfig;
import com.hackathon.going.search.response.LocationInfoListResponse;
import com.hackathon.going.search.response.naver.NaverSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class NaverSearchService implements SearchService {

    @Value("${naver.client.id}")
    private String NAVER_CLIENT_ID;
    @Value("${naver.client.secret}")
    private String NAVER_CLIENT_SECRET;

    private final WebClientConfig webClientConfig;

    public LocationInfoListResponse searchLocation(String keyword) {
        Mono<NaverSearchResponse> response;

        response = webClientConfig.webClient().get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/search/local.xml")
                        .queryParam("query", keyword)
                        .queryParam("display", 10)
                        .queryParam("start", 1)
                        .queryParam("sort", "random")
                        .build())
                .header(HttpHeaders.AUTHORIZATION, "X-Naver-Client-Id " + NAVER_CLIENT_ID)
                .header(HttpHeaders.AUTHORIZATION, "X-Naver-Client-Secret " + NAVER_CLIENT_SECRET)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.is4xxClientError() || httpStatus.is5xxServerError(),
                        clientResponse ->
                                clientResponse.bodyToMono(String.class)
                                        .map(body -> new RuntimeException(body)))
                .bodyToMono(NaverSearchResponse.class);

        return LocationInfoListResponse.of(response.block());
    }

}
