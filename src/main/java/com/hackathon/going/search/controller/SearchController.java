package com.hackathon.going.search.controller;

import com.hackathon.going.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.io.IOException;

@RestController
@RequestMapping(value = "/api/search")
@RequiredArgsConstructor
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public void searchMusic(
            @RequestParam("keyword") @NotBlank String keyword
    ) throws IOException, ParseException {
        searchService.searchLocation(keyword);
    }
}
