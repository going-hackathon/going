package com.hackathon.going.domain.user.dto.response;

import com.hackathon.going.domain.user.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserJoinResponse {

    private Long id;
    private String userAccountId;
    private UserRole role;
    private String nickname;
}
