package com.hackathon.going.domain.user.entity;

import com.hackathon.going.domain.common.BaseEntity;
import com.hackathon.going.domain.user.constant.Gender;
import com.hackathon.going.domain.user.constant.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_account_id", nullable = false)
    private String userAccountId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.USER;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "birth_year")
    private String birthYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
}
