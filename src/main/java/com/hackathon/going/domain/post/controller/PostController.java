package com.hackathon.going.domain.post.controller;

import com.hackathon.going.domain.common.ResponseDto;
import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.post.request.PostCreateRequest;
import com.hackathon.going.domain.post.response.PostResponse;
import com.hackathon.going.domain.post.service.PostService;
import com.hackathon.going.domain.travel.dto.TravelDto;
import com.hackathon.going.domain.travel.response.HomeResponse;
import com.hackathon.going.domain.travel.response.TravelResponse;
import com.hackathon.going.domain.travel.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final TravelService travelService;
    private final PostService postService;

    @GetMapping
    public ResponseEntity<HomeResponse> home(Authentication authentication) {
        TravelDto myTravel = travelService.getMyTravel(authentication.getName());
        List<PostDto> posts = postService.getPosts();
        return ResponseDto.ok(new HomeResponse(TravelResponse.fromDto(myTravel), posts.stream().map(PostResponse::fromDto).collect(Collectors.toList())));
    }

    @PostMapping
    public ResponseEntity<Void> createPost(Authentication authentication, @RequestBody PostCreateRequest request) {
        postService.create(request.getTitle(), request.getContent(), request.getFiles(), authentication.getName());
        return ResponseDto.noContent();
    }
}
