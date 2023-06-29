package com.hackathon.going.domain.pinImage.response;

import com.hackathon.going.domain.pinImage.constant.PinImgStatus;
import com.hackathon.going.domain.pinImage.dto.PinImageDto;
import com.hackathon.going.domain.pin.dto.response.PinResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class PinImageResponse {

    private Long id;
    private String url;
    private PinResponse pin;
    private PinImgStatus status;

    public static PinImageResponse fromDto(PinImageDto dto) {
        return PinImageResponse.builder()
                .id(dto.getId())
                .url(dto.getUrl())
                .pin(PinResponse.fromDto(dto.getPin()))
                .status(dto.getStatus())
                .build();
    }
}
