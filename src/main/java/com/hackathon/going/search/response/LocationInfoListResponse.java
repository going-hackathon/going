package com.hackathon.going.search.response;

import com.hackathon.going.search.response.naver.NaverSearchResponse;

import java.util.List;
import java.util.stream.Collectors;

public class LocationInfoListResponse {
    List<LocationInfoResponse> data;

    public static LocationInfoListResponse of(
            NaverSearchResponse naverSearchResponse
    ) {
        LocationInfoListResponse locationInfoListResponse = new LocationInfoListResponse();
        locationInfoListResponse.data = naverSearchResponse.getItems()
                .stream()
                .map(LocationInfoResponse::of)
                .collect(Collectors.toList());
        return locationInfoListResponse;
    }
}
