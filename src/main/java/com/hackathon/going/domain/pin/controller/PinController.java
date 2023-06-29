package com.hackathon.going.domain.pin.controller;

import com.hackathon.going.domain.common.ResponseDto;
import com.hackathon.going.domain.pin.dto.request.PinRequestDto;
import com.hackathon.going.domain.pin.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pins")
public class PinController {

    private final PinService pinService;

    @PostMapping
    public ResponseEntity<Void> createPin(@Valid @RequestBody PinRequestDto requestDto) {
        pinService.create(requestDto);
        return ResponseDto.noContent();
    }

}
