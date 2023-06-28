package com.hackathon.going.domain.travel.dto;

import com.hackathon.going.domain.travel.entity.Travel;
import com.hackathon.going.domain.user.dto.UserDto;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelDto {

    private Long id;
    private UserDto userDto;
    private String title;
    private String content;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public static TravelDto fromEntity(Travel entity) {
        return TravelDto.builder()
                .id(entity.getId())
                .userDto(UserDto.fromEntity(entity.getUser()))
                .title(entity.getTitle())
                .content(entity.getContent())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }
}
