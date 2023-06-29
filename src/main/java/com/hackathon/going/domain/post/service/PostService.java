package com.hackathon.going.domain.post.service;

import com.hackathon.going.domain.post.dto.PostDto;
import com.hackathon.going.domain.post.entity.Post;
import com.hackathon.going.domain.post.repository.PostRepository;
import com.hackathon.going.domain.postImage.entity.PostImage;
import com.hackathon.going.domain.postImage.repository.PostImageRepository;
import com.hackathon.going.domain.user.entity.User;
import com.hackathon.going.domain.user.repository.UserRepository;
import com.hackathon.going.global.error.dto.ErrorCode;
import com.hackathon.going.global.error.exception.BusinessException;
import com.hackathon.going.s3.utils.AwsS3Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostImageRepository postImageRepository;
    private final AwsS3Utils awsS3Utils;

    @Transactional
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

        List<PostImage> postImages = awsS3Utils.uploadPostImage(userAccountId, files);

        if(!postImages.isEmpty()) {
            for(PostImage postImage : postImages) {
                post.addImage(postImage);
            }
        }

        postRepository.save(post);
        postImageRepository.saveAll(postImages);
    }
}
