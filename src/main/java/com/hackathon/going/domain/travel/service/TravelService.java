package com.hackathon.going.domain.travel.service;

import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pin.entity.Pin;
import com.hackathon.going.domain.pin.repository.PinRepository;
import com.hackathon.going.domain.travel.dto.TravelDto;
import com.hackathon.going.domain.travel.dto.TravelWithPinsDto;
import com.hackathon.going.domain.travel.entity.Travel;
import com.hackathon.going.domain.travel.repository.TravelRepository;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;
    private final PinRepository pinRepository;

    public TravelWithPinsDto getTravelWithPins(Long travelId) {
        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TRAVEL_NOT_FOUND));

        List<Pin> pins = pinRepository.findAllByTravel(travel);

        return TravelWithPinsDto.builder()
                .travel(TravelDto.fromEntity(travel))
                .pins(pins.stream().map(PinDto::fromEntity).collect(Collectors.toList()));
    }

    public TravelDto getMyTravel(String userAccountId) {
        Travel travel = travelRepository.findByUser_UserAccountIdOrderByModifiedAtDesc(userAccountId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TRAVEL_NOT_FOUND));
        return TravelDto.fromEntity(travel);
    }
}
