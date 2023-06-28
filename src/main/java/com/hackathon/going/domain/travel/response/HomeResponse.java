package com.hackathon.going.domain.travel.response;

import com.hackathon.going.domain.post.response.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class HomeResponse {

    private TravelResponse travel;
    private List<PostResponse> posts;
}
