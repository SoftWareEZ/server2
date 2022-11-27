package com.albba.albbaUser.service;

import com.albba.albbaUser.dto.KakaoLoginRequestDto;
import com.albba.albbaUser.dto.SignupRequestDto;
import com.albba.albbaUser.dto.UserInfoFrontDto;
import com.albba.albbaUser.entity.Authority;
import com.albba.albbaUser.entity.User;
import com.albba.albbaUser.repository.UserRepository;
import com.albba.work.model.Store;
import com.albba.work.repository.StoreRepository;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service

public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.storeRepository = storeRepository;
    }

    //
    public User KakaoLogin(KakaoLoginRequestDto requestDto){
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);



        if (!found.isPresent()) {
            String password = UUID.randomUUID().toString();
            String encodedPassword = passwordEncoder.encode(password);
            String email = requestDto.getEmail();

            String phone_number = null;


            Authority authority = new Authority();
            //if(requestDto.isAdmin())
            authority.setAuthorityName("ROLE_USER");
            //else
               // authority.setAuthorityName("ROLE_ADMIN");
            User user = new User(requestDto.getUsername(), encodedPassword, email, requestDto.getRealname(),phone_number,requestDto.getUsername());
            user.setAuthorities(Collections.singleton(authority));
            user.setAuthorities(new Authority("ROLE_ADMIN"));
            userRepository.save(user);
            return user;
        }




        return found.get();

    }


    //테스트용
    public List<User> UserList() {
        return userRepository.findAll();


    }
    //인증 정보 바탕으로 ID 가져오는 서비스

    public UserInfoFrontDto getUserInfo()
    {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context에 인증 정보가 없습니다.");
            return null;
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }






        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent()) {
            User usertemp = found.get();
            //임시로 storeid=1로해두고
            //storerepository코드 추가 되면 바꾸기 이부분
            //userID에 해당하는 storeID가 있는 지 알아보고 있으면 그 store 반환
            //한 사용자가 사업장 여러 개 할거면 여기서 List<Store> 로 가져오기
            Long storeid = (long)1;
            //<Store> storechk = storeRepository.findByUserId(usertemp.getUserId());
            List<Store> storeschk = storeRepository.findStoresByUserId(usertemp.getUserId());


            if(storeschk.size()==0) {
                storeid = null;
            }
            else {
                storeid = storeschk.get(0).getStoreId();
            }
             /*   if(storechk.isPresent())
                storeid= storechk.get().getStoreId();
            else
                storeid = null;
            return new UserInfoFrontDto(usertemp,storeid);*/
            return new UserInfoFrontDto(usertemp,storeid);
        }
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



        //if(requestDto.isAdmin())
        authority.setAuthorityName("ROLE_USER");
        //else
        // authority.setAuthorityName("ROLE_ADMIN");



        User user = new User(username, password, email, realname,phone_number);
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