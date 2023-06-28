package com.hackathon.going.domain.post.response;

import com.hackathon.going.domain.image.response.ImageResponse;
import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class PostResponse {

    private Long id;
    private UserResponse user;
    private String title;
    private String content;
    private List<ImageResponse> images;

    public static PostResponse fromDto(PostDto dto) {
        return PostResponse.builder()
                .id(dto.getId())
                .user(UserResponse.fromDto(dto.getUser()))
                .title(dto.getTitle())
                .title(dto.getTitle())
                .images(dto.getImages().stream().map(ImageResponse::fromDto).collect(Collectors.toList()))
                .build();
    }
}
