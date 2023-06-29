package com.hackathon.going.domain.user.dto.response;

import com.hackathon.going.domain.user.constant.Gender;
import com.hackathon.going.domain.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String userAccountId;
    private String nickname;
    private String birthYear;
    private Gender gender;

    public static UserResponse fromDto(UserDto dto) {
        return UserResponse.builder()
                .id(dto.getId())
                .userAccountId(dto.getUsername())
                .nickname(dto.getNickname())
                .birthYear(dto.getBirthYear())
                .gender(dto.getGender())
                .build();
    }
}
