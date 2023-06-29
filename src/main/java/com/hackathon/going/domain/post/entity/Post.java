package com.hackathon.going.domain.post.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.postImage.entity.PostImage;
import com.hackathon.going.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "post",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true)
    @Builder.Default
    private List<PostImage> postImages = new ArrayList<>();

    public void addImage(PostImage postImage) {
        this.postImages.add(postImage);

        if (postImage.getPost() != this)
            postImage.setPost(this);
    }
}
