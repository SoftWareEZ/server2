package com.albba.albbaUser.dto;


import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @NotNull
    //@Size(min = 3, max = 20)
    private String username;


    @NotNull
    //@Size(min = 5, max = 100)
    private String password;
}
