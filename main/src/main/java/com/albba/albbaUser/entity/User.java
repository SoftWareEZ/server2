package com.albba.albbaUser.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", length = 50, unique = true)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "realname",length = 50)
    private String realname;

    @Column(name = "phone_number", length = 20)
    private String phone_number;

    @Column(unique = true)
    private Long kakaoId;

    @ManyToMany
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")})
    private Set<Authority> authorities;

    public User(String username, String password, String email,String realname,String phone_number) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
        this.phone_number = phone_number;
        this.kakaoId = null;


    }

    public User(String username, String password, String email,String realname,String phone_number,Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
        this.phone_number = phone_number;
        this.kakaoId = kakaoId;


    }
}