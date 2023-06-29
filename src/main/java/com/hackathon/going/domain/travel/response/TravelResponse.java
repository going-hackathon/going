package com.hackathon.going.domain.travel.response;

import com.hackathon.going.domain.travel.dto.TravelDto;
import com.hackathon.going.domain.user.dto.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TravelResponse {

    private Long id;
    private UserResponse user;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static TravelResponse fromDto(TravelDto dto) {
        return TravelResponse.builder()
                .id(dto.getId())
                .user(UserResponse.fromDto(dto.getUserDto()))
                .title(dto.getTitle())
                .content(dto.getContent())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
