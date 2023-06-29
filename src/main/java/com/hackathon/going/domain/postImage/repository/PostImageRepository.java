package com.hackathon.going.domain.postImage.repository;

import com.hackathon.going.domain.postImage.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostImageRepository extends JpaRepository<PostImage, Long> {
}
