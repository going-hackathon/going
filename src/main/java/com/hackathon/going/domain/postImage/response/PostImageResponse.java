package com.hackathon.going.domain.postImage.response;

import com.hackathon.going.domain.post.response.PostResponse;
import com.hackathon.going.domain.postImage.dto.PostImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PostImageResponse {

    private Long id;
    private String url;
    private PostResponse post;

    public static PostImageResponse fromDto(PostImageDto dto) {
        return PostImageResponse.builder()
                .id(dto.getId())
                .url(dto.getUrl())
                .post(PostResponse.fromDto(dto.getPost()))
                .build();
    }
}
