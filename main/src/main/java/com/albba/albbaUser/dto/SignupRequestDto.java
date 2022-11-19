package com.albba.albbaUser.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String email;
    private String realname;
    private String phone_number;
    private boolean admin = false;
    private String adminToken = "";
}
