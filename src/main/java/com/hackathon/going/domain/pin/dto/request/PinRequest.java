package com.hackathon.going.domain.pin.dto.request;

import com.hackathon.going.domain.common.annotation.IsLatitude;
import com.hackathon.going.domain.common.annotation.IsLongitude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class PinRequest {

    @NotNull(message ="Travel Id is required")
    private Long travelId;

    @IsLatitude
    @NotNull(message ="Latitude is required")
    private Double latitude;

    @IsLongitude
    @NotNull(message = "Longitude is required")
    private Double longitude;

    private String content;

    private String title;

    private String address;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
