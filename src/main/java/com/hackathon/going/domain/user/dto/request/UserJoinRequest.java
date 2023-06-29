package com.hackathon.going.domain.user.dto.request;

import com.hackathon.going.domain.user.constant.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {

    private String userAccountId;
    private String password;
    private String nickname;
    private String birthYear;
    private Gender gender;
}
