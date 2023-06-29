package com.hackathon.going.domain.pin.controller;

import com.hackathon.going.domain.common.ResponseDto;
import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pin.dto.request.PinRequest;
import com.hackathon.going.domain.pin.dto.response.PinResponse;
import com.hackathon.going.domain.pin.service.PinService;
import com.hackathon.going.domain.user.dto.UserDto;
import com.hackathon.going.domain.user.service.UserService;
import com.hackathon.going.openai.drawing.KakaoImageService;
import com.hackathon.going.openai.translation.PapagoTransService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pins")
public class PinController {

    private final PinService pinService;
    private final UserService userService;
    private final PapagoTransService papagoTransService;
    private final KakaoImageService kakaoImageService;

    @PostMapping
    public ResponseEntity<Void> createPin(@Valid @RequestBody PinRequest request, Authentication authentication) throws Exception {
        PinDto pin = pinService.create(request.getTravelId(), request.getLatitude(), request.getLongitude());
        UserDto user = userService.findUser(authentication.getName());
        String address = papagoTransService.getTransSentence(request.getAddress().split(" ")[0]);
        String text = papagoTransService.makePrompt(user, address);
        kakaoImageService.create(pin, text);

        return ResponseDto.noContent();
    }

    @GetMapping("/{pinId}")
    public HttpEntity<PinResponse> getPin(@PathVariable Long pinId) {
        PinDto pin = pinService.getPin(pinId);
        return ResponseDto.ok(PinResponse.fromDto(pin));
    }

    @PostMapping("{pinId}")
    public void updatePin(@PathVariable Long pinId, List<MultipartFile> files) {
        pinService.update(pinId, files);
    }
}
