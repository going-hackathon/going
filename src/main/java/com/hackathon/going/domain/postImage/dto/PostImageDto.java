package com.hackathon.going.domain.postImage.dto;

import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.postImage.entity.PostImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostImageDto {

    private Long id;
    private String url;
    private PostDto post;

    public static PostImageDto fromEntity(PostImage entity) {
        return PostImageDto.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .post(PostDto.fromEntity(entity.getPost()))
                .build();
    }
}
