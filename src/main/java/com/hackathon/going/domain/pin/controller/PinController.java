package com.hackathon.going.domain.pin.controller;

import com.hackathon.going.domain.common.ResponseDto;
import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pin.dto.request.PinRequestDto;
import com.hackathon.going.domain.pin.resopnse.PinResponse;
import com.hackathon.going.domain.pin.service.PinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pins")
public class PinController {

    private final PinService pinService;

    @PostMapping
    public ResponseEntity<Void> createPin(@Valid @RequestBody PinRequestDto requestDto) {
        pinService.create(requestDto);
        return ResponseDto.noContent();
    }

    @GetMapping("/{pinId}")
    public HttpEntity<PinResponse> getPin(@PathVariable Long pinId) {
        PinDto pin = pinService.getPin(pinId);
        return ResponseDto.ok(PinResponse.fromDto(pin));
    }
}
