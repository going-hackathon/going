package com.hackathon.going.domain.pinImage.dto;

import com.hackathon.going.domain.pinImage.constant.PinImgStatus;
import com.hackathon.going.domain.pinImage.entity.PinImage;
import com.hackathon.going.domain.pin.dto.PinDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PinImageDto {

    private Long id;
    private String url;
    private PinDto pin;
    private PinImgStatus status;

    public static PinImageDto fromEntity(PinImage entity) {
        return PinImageDto.builder()
                .id(entity.getId())
                .url(entity.getUrl())
                .pin(PinDto.fromEntity(entity.getPin()))
                .status(entity.getStatus())
                .build();
    }
}
