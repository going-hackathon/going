package com.hackathon.going.domain.pin.service;

<<<<<<< main
import com.hackathon.going.domain.pin.dto.request.PinRequest;
=======
import com.hackathon.going.domain.pinImage.dto.PinImageDto;
>>>>>>> feat: Pin Entity 등록을 위한 기초 작업
import com.hackathon.going.domain.pinImage.entity.PinImage;
import com.hackathon.going.domain.pinImage.repository.PinImageRepository;
import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pin.entity.Pin;
import com.hackathon.going.domain.pin.repository.PinRepository;
import com.hackathon.going.domain.travel.entity.Travel;
import com.hackathon.going.domain.travel.repository.TravelRepository;
import com.hackathon.going.domain.user.dto.UserDto;
import com.hackathon.going.domain.user.service.UserService;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.NotFoundException;
import com.hackathon.going.openai.drawing.KakaoImageService;
import com.hackathon.going.openai.translation.PapagoTransService;
import com.hackathon.going.s3.utils.AwsS3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PinService {

    private final PinRepository pinRepository;
    private final PinImageRepository pinImageRepository;
    private final TravelRepository travelRepository;
    private final AwsS3Utils awsS3Utils;

    @Transactional
<<<<<<< main
    public void create(PinRequest requestDto) {
        Travel travel = getTravel(requestDto.getTravelId());
=======
    public PinDto create(Long travelId, Double latitude, Double longitude) {
        Travel travel = getTravel(travelId);
>>>>>>> feat: Pin Entity 등록을 위한 기초 작업

        Pin pin = Pin.builder()
                .latitude(requestDto.getLatitude())
                .longitude(requestDto.getLongitude())
                .content(requestDto.getContent())
                .title(requestDto.getTitle())
                .address(requestDto.getAddress())
                .startDate(requestDto.getStartDate())
                .endDate(requestDto.getEndDate())
                .travel(travel)
                .build();

        Pin savedPin = pinRepository.save(pin);
        return PinDto.fromEntity(savedPin);
    }

    @Transactional
    public void update(Long pinId, List<MultipartFile> files) {
        Pin pin = pinRepository.findById(pinId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.PIN_NOT_FOUND));

        // TODO : 이미지 업로드 시에 생성된 이미지는 삭제되도록

        List<PinImage> pinImages = awsS3Utils.uploadPinImage(pinId, files);

        if(!pinImages.isEmpty()) {
            for(PinImage pinImage : pinImages) {
                pinImage.setPin(pin);
            }
            pinImageRepository.saveAll(pinImages);
        }
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
