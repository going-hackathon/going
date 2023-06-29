package com.hackathon.going.domain.travel.service;

import com.hackathon.going.domain.travel.dto.TravelDto;
import com.hackathon.going.domain.travel.entity.Travel;
import com.hackathon.going.domain.travel.repository.TravelRepository;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TravelService {

    private final TravelRepository travelRepository;

    public TravelDto getMyTravel(String userAccountId) {
        Travel travel = travelRepository.findByUser_UserAccountIdOrderByModifiedAtDesc(userAccountId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.TRAVEL_NOT_FOUND));
        return TravelDto.fromEntity(travel);
    }
}
