package com.hackathon.going.domain.post.service;

import com.hackathon.going.domain.image.entity.Image;
import com.hackathon.going.domain.image.repository.ImageRepository;
import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.post.entity.Post;
import com.hackathon.going.domain.post.repository.PostRepository;
import com.hackathon.going.domain.user.entity.User;
import com.hackathon.going.domain.user.repository.UserRepository;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.BusinessException;
import com.hackathon.going.s3.utils.AwsS3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final AwsS3Utils awsS3Utils;

    public List<PostDto> getPosts() {
        return postRepository.findAll().stream().map(PostDto::fromEntity).collect(Collectors.toList());
    }

    public void create(String title, String content, List<MultipartFile> files, String userAccountId) {
        User user = userRepository.findByUserAccountId(userAccountId).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_FOUND));
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        List<Image> images = awsS3Utils.uploadPostImage(userAccountId, files);

        if(!images.isEmpty()) {
            for(Image image : images) {
                post.addImage(image);
            }
            imageRepository.saveAll(images);
        }

        postRepository.save(post);
    }
}
