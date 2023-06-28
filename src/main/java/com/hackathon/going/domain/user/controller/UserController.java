package com.hackathon.going.domain.user.controller;

import com.hackathon.going.domain.common.ResponseDto;
import com.hackathon.going.domain.user.dto.UserDto;
import com.hackathon.going.domain.user.dto.request.UserJoinRequest;
import com.hackathon.going.domain.user.dto.request.UserLoginRequest;
import com.hackathon.going.domain.user.dto.response.UserJoinResponse;
import com.hackathon.going.domain.user.dto.response.UserLoginResponse;
import com.hackathon.going.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseDto<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        String token = userService.login(request.getUserAccountId(), request.getPassword());
        return new ResponseDto<>(new UserLoginResponse(token));
    }

    @PostMapping("/join")
    public ResponseDto<UserJoinResponse> join(@RequestBody UserJoinRequest request) {
        UserDto userDto = userService.join(request.getUserAccountId(), request.getPassword(), request.getNickname());
        return new ResponseDto<>(UserJoinResponse.builder()
                .id(userDto.getId())
                .userAccountId(userDto.getUsername())
                .role(userDto.getUserRole())
                .nickname(userDto.getNickname())
                .build());
    }
}
