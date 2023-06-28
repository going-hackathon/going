package com.hackathon.going.security.service;

import com.hackathon.going.domain.user.dto.UserDto;
import com.hackathon.going.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDto loadUserByUsername(String username) {
        try {
            return userRepository.findByUserAccountId(username).map(UserDto::fromEntity).orElseThrow(Exception::new);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
