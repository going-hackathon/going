package com.hackathon.going.domain.pin.dto;

import com.hackathon.going.domain.pin.constant.PinStatus;
import com.hackathon.going.domain.pin.entity.Pin;
import com.hackathon.going.domain.pinImage.dto.PinImageDto;
import com.hackathon.going.domain.travel.dto.TravelDto;
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
public class PinDto {

    private Long id;
    private Double latitude;
    private Double longitude;
    private TravelDto travel;
    private PinStatus status;
    private List<PinImageDto> pinImages;

    public static PinDto fromEntity(Pin entity) {
        return PinDto.builder()
                .id(entity.getId())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .travel(TravelDto.fromEntity(entity.getTravel()))
                .status(entity.getStatus())
                .pinImages(entity.getPinImages().stream()
                        .map(PinImageDto::fromEntity).collect(Collectors.toList()))
                .build();
    }
}
