package com.hackathon.going.domain.user.dto.response;

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

    public static UserResponse fromDto(UserDto dto) {
        return UserResponse.builder()
                .id(dto.getId())
                .userAccountId(dto.getUsername())
                .nickname(dto.getNickname())
                .build();
    }
}
