package com.hackathon.going.domain.travel.response;

import com.hackathon.going.domain.pin.resopnse.PinResponse;
import com.hackathon.going.domain.travel.dto.TravelWithPinsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class TravelWithPinsResponse {
    private TravelResponse travel;
    private List<PinResponse> pinList;

    public static TravelWithPinsResponse fromDto(TravelWithPinsDto dto) {
        return TravelWithPinsResponse.builder()
                .travel(TravelResponse.fromDto(dto.getTravel()))
                .pinList(dto.getPins().stream().map(PinResponse::fromDto).collect(Collectors.toList()))
                .build();
    }
}
