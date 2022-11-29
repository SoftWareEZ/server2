package com.albba.albbaUser.dto;


import com.albba.albbaUser.entity.User;
import lombok.*;

import javax.persistence.Column;
import java.util.List;


//프론트로 유저정보랑 store보내줄 때 쓰는 dto
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoFrontDto {
    private Long userId;
    private Long storeId;

    private String username;


    private String email;

    private String realname;

    private String phone_number;

    public UserInfoFrontDto(User user, Long sid)
    {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.realname = user.getRealname();
        this.phone_number = user.getPhone_number();
        this.storeId = sid;
    }



}
