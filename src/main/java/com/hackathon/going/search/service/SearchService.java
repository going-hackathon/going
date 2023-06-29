package com.hackathon.going.search.service;

import com.hackathon.going.search.response.LocationInfoListResponse;

public interface SearchService {
    LocationInfoListResponse searchLocation(String keyword);
}
