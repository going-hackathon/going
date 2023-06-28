package com.hackathon.going.domain.post.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@AllArgsConstructor
public class PostCreateRequest {

    private String title;
    private String content;
    private List<MultipartFile> files;
}
