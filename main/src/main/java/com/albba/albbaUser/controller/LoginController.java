package com.albba.albbaUser.controller;

import com.albba.albbaUser.dto.*;
import com.albba.albbaUser.entity.User;
import com.albba.albbaUser.jwt.JwtFilter;
import com.albba.albbaUser.jwt.TokenProvider;
import com.albba.albbaUser.repository.UserRepository;
import com.albba.albbaUser.service.UserService;
import com.albba.kakaoLogin.security.UserDetailsImpl;
import com.albba.kakaoLogin.service.KakaoUserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/albba")
@RequiredArgsConstructor
public class LoginController {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserService userService;

    private final KakaoUserService kakaoUserService;
    private final UserRepository userRepository;


    /*public AuthController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }*/
    ///api/login을 POST요청 보내면
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto) {

/*
        //@RequestBody로 객체 받아와서 바탕으로 UsernamePasswordAuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        //토큰으로 Authentication 객체 만듦


        // authentication메소드가 실행될때
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);//여기서 LoginUserDetails에 있는 loadUserByUsername 실행
        //그 결과로 AUthentication 만들어서 Security context에 넣어줌
        //이건 인증된 결과, 요청 둘다 되는 메소드

        //여기서 이게 필요한가??
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //jwt 토큰 만들기
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);*/
        return userService.Login(loginDto);
    }
    @PostMapping("/kakao/login")
    public ResponseEntity<TokenDto> kakaoLogin(@RequestBody KakaoLoginRequestDto requestDto) {

        return  userService.KakaoLogin(requestDto);

/*
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());

*/


        //userDetails로  Authentication 객체 만듦
        /*UserDetails userDetails = new UserDetailsImpl(user);

        // authentication메소드가 실행될때
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

        */
      /*  UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());

        //토큰으로 Authentication 객체 만듦


        // authentication메소드가 실행될때
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);





        System.out.println(user.getUsername());
        System.out.println(user.getPassword());








        //그 결과로 AUthentication 만들어서 Security context에 넣어줌
        //이건 인증된 결과, 요청 둘다 되는 메소드
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //jwt 토큰 만들기
        String jwt = tokenProvider.createToken(authentication);
        System.out.println("jwttoken = "+jwt);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
*/
    }
    @PostMapping("/signup")
    public void signup(@RequestBody SignupRequestDto requestDto) {

        userService.registerUser(requestDto);


    }
    //이름 가져오는 부분인데 굳이 이거말고 정보 가져오는 메소드로 하자
    @GetMapping("/getname")
    public Optional<Object> getname()
    {
        return userService.getRealname();
    }

    @GetMapping("/userinfo/{isAdmin}")
    public UserInfoFrontDto getUser(@PathVariable Long isAdmin)
    {
        return userService.getUserInfo(isAdmin);
    }

    //주석달랭
    //패스워드 변경은?
    @GetMapping("/kakao/callback")
    public ResponseEntity<TokenDto> kakaoLogin2(@RequestParam String code) throws JsonProcessingException {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        return kakaoUserService.kakaoLogin(code);

    }


    //

}
