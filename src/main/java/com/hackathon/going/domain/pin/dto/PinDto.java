package com.hackathon.going.domain.pin.dto;

import com.hackathon.going.domain.pin.constant.PinStatus;
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
    private Point point;
    private TravelDto travel;
    private PinStatus status;

    public static PinDto fromEntity(Pin entity) {
        return PinDto.builder()
                .id(entity.getId())
                .point(entity.getPoint())
                .travel(TravelDto.fromEntity(entity.getTravel()))
                .status(entity.getStatus())
                .build();
    }
}
