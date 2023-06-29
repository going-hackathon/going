package com.hackathon.going.search.dto;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class NaverLocationInfoDto {

    private String title;

    private String address;

    private Double latitude;

    private Double longitude;

}
