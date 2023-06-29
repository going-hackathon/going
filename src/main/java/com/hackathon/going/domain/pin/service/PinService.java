package com.hackathon.going.domain.pin.service;

import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pin.dto.request.PinRequestDto;
import com.hackathon.going.domain.pin.entity.Pin;
import com.hackathon.going.domain.pin.repository.PinRepository;
import com.hackathon.going.domain.travel.entity.Travel;
import com.hackathon.going.domain.travel.repository.TravelRepository;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PinService {

    private final PinRepository pinRepository;
    private final TravelRepository travelRepository;

    @Transactional
    public void create(PinRequestDto requestDto) {
        Travel travel = getTravel(requestDto.getTravelId());

        Pin pin = Pin.builder()
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .travel(travel)
                .build();

        pinRepository.save(pin);
    }

    public PinDto getPin(Long pinId) {
        Pin pin = pinRepository.findById(pinId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PIN_NOT_FOUND));
        return PinDto.fromEntity(pin);
    }

    private Travel getTravel(Long travelId) {
        return travelRepository.findById(travelId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TRAVEL_NOT_FOUND));
    }

}
