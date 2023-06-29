package com.hackathon.going.domain.user.service;

import com.hackathon.going.domain.user.dto.UserDto;
import com.hackathon.going.domain.user.entity.User;
import com.hackathon.going.domain.user.repository.UserRepository;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.BusinessException;
import com.hackathon.going.global.error.exception.NotFoundException;
import com.hackathon.going.security.service.CustomUserService;
import com.hackathon.going.security.util.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CustomUserService customUserService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expired-time-ms}")
    private Long expiredTimeMs;

    public String login(String userAccountId, String password) {
        UserDto userDto = customUserService.loadUserByUsername(userAccountId);

        if(!passwordEncoder.matches(password, userDto.getPassword())) {
            throw new BusinessException(ErrorCode.INVALID_PASSWORD);
        }

        return JwtTokenUtils.generateToken(userAccountId, secretKey, expiredTimeMs);
    }

    @Transactional
    public UserDto join(String userAccountId, String password, String nickname) {
        userRepository.findByUserAccountId(userAccountId).ifPresent(it -> {
            throw new BusinessException(ErrorCode.DUPLICATED_USER_ACCOUNT_ID);
                });

        User user = userRepository.save(User.builder()
                .userAccountId(userAccountId)
                .password(passwordEncoder.encode(password))
                .nickname(nickname)
                .build());

        return UserDto.fromEntity(user);
    }
}
