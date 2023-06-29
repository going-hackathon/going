package com.hackathon.going.domain.image.dto;

import com.hackathon.going.domain.image.constant.ImgStatus;
import com.hackathon.going.domain.image.entity.Image;
import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.post.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ImageDto {

    private Long id;
    private String url;
    private PinDto pin;
    private PostDto post;
    private ImgStatus status;

    public static ImageDto fromEntity(Image entity) {
        return ImageDto.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .pin(PinDto.fromEntity(entity.getPin()))
                .post(PostDto.fromEntity(entity.getPost()))
                .status(entity.getStatus())
                .build();
    }
}
