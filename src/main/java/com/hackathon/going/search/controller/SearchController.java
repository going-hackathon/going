package com.hackathon.going.search.controller;

import com.hackathon.going.search.response.LocationInfoListResponse;
import com.hackathon.going.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public LocationInfoListResponse searchMusic(
            @RequestParam("keyword") @NotBlank String keyword
    ) {
        return searchService.searchLocation(keyword);
    }
}
