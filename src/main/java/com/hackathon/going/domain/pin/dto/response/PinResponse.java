package com.hackathon.going.domain.pin.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hackathon.going.domain.pin.constant.PinStatus;
import com.hackathon.going.domain.pin.dto.PinDto;
import com.hackathon.going.domain.pinImage.response.PinImageResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class PinResponse {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String content;
    private String title;
    private String address;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            locale = "Asia/Seoul"
    )
    private LocalDateTime startDate;
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd HH:mm:ss",
            locale = "Asia/Seoul"
    )
    private LocalDateTime endDate;
    private List<PinImageResponse> pinImages;
    private PinStatus status;

    public static PinResponse fromDto(PinDto dto) {
        return PinResponse.builder()
                .id(dto.getId())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .content(dto.getContent())
                .title(dto.getTitle())
                .address(dto.getAddress())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .pinImages(dto.getPinImages().stream()
                        .map(PinImageResponse::fromDto).collect(Collectors.toList()))
                .status(dto.getStatus())
                .build();
    }
}
