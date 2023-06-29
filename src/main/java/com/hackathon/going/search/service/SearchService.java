package com.hackathon.going.search.service;

import com.hackathon.going.search.response.NaverLocationInfoListResponse;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public interface SearchService {
    NaverLocationInfoListResponse searchLocation(String keyword) throws IOException, ParseException;
}
