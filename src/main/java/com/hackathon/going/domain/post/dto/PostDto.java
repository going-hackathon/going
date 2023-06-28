package com.hackathon.going.domain.post.dto;

import com.hackathon.going.domain.image.dto.ImageDto;
import com.hackathon.going.domain.post.entity.Post;
import com.hackathon.going.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDto {
    private Long id;
    private UserDto user;
    private String title;
    private String content;
    private List<ImageDto> images;

    public static PostDto fromEntity(Post entity) {
        return PostDto.builder()
                .id(entity.getId())
                .user(UserDto.fromEntity(entity.getUser()))
                .title(entity.getTitle())
                .content(entity.getContent())
                .images(entity.getImages().stream().map(ImageDto::fromEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}
