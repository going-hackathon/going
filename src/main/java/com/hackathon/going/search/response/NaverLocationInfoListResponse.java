package com.hackathon.going.search.response;

import com.hackathon.going.search.dto.NaverLocationInfoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class NaverLocationInfoListResponse {
    List<NaverLocationInfoDto> data;
}
