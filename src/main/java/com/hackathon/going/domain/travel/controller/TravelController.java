package com.hackathon.going.domain.travel.controller;

import com.hackathon.going.domain.common.ResponseDto;
import com.hackathon.going.domain.travel.dto.TravelDto;
import com.hackathon.going.domain.travel.dto.TravelWithPinsDto;
import com.hackathon.going.domain.travel.response.TravelResponse;
import com.hackathon.going.domain.travel.response.TravelWithPinsResponse;
import com.hackathon.going.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @GetMapping("/{travelId}")
    public ResponseEntity<TravelWithPinsResponse> getTravelWithPins(@PathVariable Long travelId) {
        TravelWithPinsDto travelWithPins = travelService.getTravelWithPins(travelId);
        return ResponseDto.ok(TravelWithPinsResponse.fromDto(travelWithPins));
    }
}
