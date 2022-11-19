package com.albba.albbaUser.jwt;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@AllArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    private TokenProvider tokenProvider;


    @Override
    public void configure(HttpSecurity http) {
        //만든 필터를 Security에 등록
        http.addFilterBefore(new JwtFilter(tokenProvider),
                UsernamePasswordAuthenticationFilter.class
        );
    }




}