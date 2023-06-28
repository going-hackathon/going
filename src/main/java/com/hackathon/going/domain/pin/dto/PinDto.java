package com.hackathon.going.domain.pin.dto;

import com.hackathon.going.domain.pin.entity.Pin;
import com.hackathon.going.domain.travel.dto.TravelDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PinDto {

    private Long id;
    private Point geography;
    private TravelDto travel;

    public static PinDto fromEntity(Pin entity) {
        return PinDto.builder()
                .id(entity.getId())
                .geography(entity.getGeography())
                .travel(TravelDto.fromEntity(entity.getTravel()))
                .build();
    }
}
