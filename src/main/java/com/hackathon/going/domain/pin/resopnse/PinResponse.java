package com.hackathon.going.domain.pin.resopnse;

import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.travel.response.TravelResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.locationtech.jts.geom.Point;

@Getter
@Builder
@AllArgsConstructor
public class PinResponse {

    private Long id;
    private Point point;
    private TravelResponse travel;

    public static PinResponse fromDto(PinDto dto) {
        return PinResponse.builder()
                .id(dto.getId())
                .point(dto.getPoint())
                .travel(TravelResponse.fromDto(dto.getTravel()))
                .build();
    }
}
