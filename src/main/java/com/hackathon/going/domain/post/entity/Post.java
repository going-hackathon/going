package com.hackathon.going.domain.post.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.image.entity.Image;
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
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        this.images.add(image);

        if (image.getPost() != this)
            image.setPost(this);
    }
}
