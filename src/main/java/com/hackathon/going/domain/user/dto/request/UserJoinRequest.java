package com.hackathon.going.domain.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {

    private String userAccountId;
    private String password;
    private String nickname;
}
