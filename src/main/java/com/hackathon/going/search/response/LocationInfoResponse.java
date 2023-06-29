package com.hackathon.going.search.response;

import com.hackathon.going.search.response.naver.Item;
import lombok.Builder;

@Builder
public class LocationInfoResponse {
    private String title;
    private String address;
    private String mapx;
    private String mapy;

    public static LocationInfoResponse of(Item item) {
        return LocationInfoResponse.builder()
                .title(item.getTitle())
                .address(item.getAddress())
                .mapx(item.getMapx())
                .mapy(item.getMapy())
                .build();
    }
}
