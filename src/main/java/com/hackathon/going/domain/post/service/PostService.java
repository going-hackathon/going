package com.hackathon.going.domain.post.service;

import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostDto> getPosts() {
        return postRepository.findAll().stream().map(PostDto::fromEntity).collect(Collectors.toList());
    }
}
