package com.hackathon.going.domain.travel.dto;

import com.hackathon.going.domain.pin.dto.PinDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelWithPinsDto {
    private TravelDto travel;
    private List<PinDto> pins;
}
