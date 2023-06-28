package com.hackathon.going.domain.travel.response;

import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.travel.dto.TravelDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeResponse {

    private TravelDto travel;
    private List<PostDto> posts;
}
