package com.hackathon.going.domain.image.response;

import com.hackathon.going.domain.image.dto.ImageDto;
import com.hackathon.going.domain.pin.resopnse.PinResponse;
import com.hackathon.going.domain.post.response.PostResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ImageResponse {

    private Long id;
    private String url;
    private PinResponse pin;
    private PostResponse post;

    public static ImageResponse fromDto(ImageDto dto) {
        return ImageResponse.builder()
                .id(dto.getId())
                .url(dto.getUrl())
                .pin(PinResponse.fromDto(dto.getPin()))
                .post(PostResponse.fromDto(dto.getPost()))
                .build();
    }
}
