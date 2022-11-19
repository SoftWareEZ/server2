package com.albba.albbaUser.service;

import com.albba.albbaUser.dto.SignupRequestDto;
import com.albba.albbaUser.entity.Authority;
import com.albba.albbaUser.entity.User;
import com.albba.albbaUser.repository.UserRepository;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //테스트용
    public List<User> UserList() {
        return userRepository.findAll();


    }
    //인증 정보 바탕으로 ID 가져오는 서비스, 로그인 성공 이후에 한 번만 해서 가지고 있으면 될 것 같은데

    public Optional<User> getUserInfo()
    {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }


        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent())
            return Optional.ofNullable(found.get());

        else
            throw new IllegalArgumentException("해당하는 유저 정보가 없습니다.");

    }



    public Optional<Object> getRealname()
    {
        //여기서부터
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();

        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }
        //여기까지 Authority로 username 받아오는 부분

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent())
        return Optional.ofNullable(found.get().getRealname());

        else
            throw new IllegalArgumentException("해당하는 유저 이름 정보가 없습니다.");

    }


    public void registerUser(SignupRequestDto requestDto) {
        // 회원 ID 중복 확인
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("이미 가입된 ID(username) 가 존재합니다.");
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String realname = requestDto.getRealname();
        String phone_number = requestDto.getPhone_number();

        Authority authority = new Authority();
        authority.setAuthorityName("ROLE_USER");

        User user = new User(username, password, email, realname,phone_number,true);
        user.setAuthorities(Collections.singleton(authority));

        userRepository.save(user);

    }
    /*
    //username으로 User객체 반환
    @Transactional(readOnly = true)
    public Optional<User> getUserInfo(String username){
        return userRepository.findOneWithAuthoritiesByUsername(username);
    }*/
    /*
    @Transactional(readOnly = true)
    public Optional<User> getMyUserWIthAuthorities(){
        return SecurityUtil.getCurrentUsername().flatMap(userRepository::findOneWithAuthoritiesByUsername);
    }
    */



}