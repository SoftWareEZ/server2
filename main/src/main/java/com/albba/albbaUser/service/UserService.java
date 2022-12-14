package com.albba.albbaUser.service;

import com.albba.albbaUser.dto.KakaoLoginRequestDto;
import com.albba.albbaUser.dto.SignupRequestDto;
import com.albba.albbaUser.dto.TokenDto;
import com.albba.albbaUser.dto.UserInfoFrontDto;
import com.albba.albbaUser.entity.Authority;
import com.albba.albbaUser.entity.User;
import com.albba.albbaUser.jwt.JwtFilter;
import com.albba.albbaUser.jwt.TokenProvider;
import com.albba.albbaUser.repository.UserRepository;
import com.albba.kakaoLogin.security.UserDetailsImpl;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private static final Logger logger = LoggerFactory.getLogger(SecurityUtil.class);
    private final TokenProvider tokenProvider;
    private final WorkInfoRepository workInfoRepository;

   /* @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, StoreRepository storeRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.storeRepository = storeRepository;
    }*/

    //
    public ResponseEntity<TokenDto> KakaoLogin(KakaoLoginRequestDto requestDto){
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        String password = null;


        if (!found.isPresent()) {
             password = requestDto.getUsername();
            String encodedPassword = passwordEncoder.encode(password);
            String email = requestDto.getEmail();
           System.out.println(password);
            String phone_number = null;


            Authority authority = new Authority();
            //if(requestDto.isAdmin())
            authority.setAuthorityName("ROLE_USER");
            Set<Authority> authoritySet = new HashSet<Authority>();
            authoritySet.add(authority);
            Authority authority2 = new Authority();
            authority2.setAuthorityName("ROLE_ADMIN");
            authoritySet.add(authority2);
            //else
               // authority.setAuthorityName("ROLE_ADMIN");
            User user = new User(requestDto.getUsername(), encodedPassword, email, requestDto.getRealname(),phone_number,requestDto.getUsername());
            //user.setAuthorities(Collections.singleton(authority));
            user.setAuthorities(authoritySet);
            userRepository.save(user);


            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(requestDto.getUsername(), password);

            //???????????? Authentication ?????? ??????


            // authentication???????????? ????????????
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);//????????? LoginUserDetails??? ?????? loadUserByUsername ??????
            //??? ????????? AUthentication ???????????? Security context??? ?????????
            //?????? ????????? ??????, ?????? ?????? ?????? ?????????

            //????????? ?????? ??????????????
            SecurityContextHolder.getContext().setAuthentication(authentication);

            //jwt ?????? ?????????
            String jwt = tokenProvider.createToken(authentication);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

            return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);





        }

        User kakaoUser = found.get();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), kakaoUser.getUsername());

        //???????????? Authentication ?????? ??????


        // authentication???????????? ????????????
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);//????????? LoginUserDetails??? ?????? loadUserByUsername ??????
        //??? ????????? AUthentication ???????????? Security context??? ?????????
        //?????? ????????? ??????, ?????? ?????? ?????? ?????????

        //????????? ?????? ??????????????
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //jwt ?????? ?????????
        String jwt = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);

    }


    //????????????
    public List<User> UserList() {
        return userRepository.findAll();


    }
    //?????? ?????? ???????????? ID ???????????? ?????????

    public UserInfoFrontDto getUserInfo(Long isAdmin)
    {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context??? ?????? ????????? ????????????.");
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
            //????????? storeid=1????????????
            //storerepository?????? ?????? ?????? ????????? ?????????
            //userID??? ???????????? storeID??? ?????? ??? ???????????? ????????? ??? store ??????
            //??? ???????????? ????????? ?????? ??? ????????? ????????? List<Store> ??? ????????????
            Long storeid = (long)1;
            //<Store> storechk = storeRepository.findByUserId(usertemp.getUserId());

            if(isAdmin ==1) {
                List<Store> storeschk = storeRepository.findStoresByUserId(usertemp.getUserId());
                if (storeschk.size() == 0) {
                    storeid = null;
                } else {
                    storeid = storeschk.get(0).getStoreId();
                }

            }
            else {
                List<WorkInfo> StoreChk = workInfoRepository.findWorkInfosByUserId(usertemp.getUserId());
                if(StoreChk.size()==0)
                    storeid = null;
                else {
                    for(WorkInfo w : StoreChk){
                        if((w.getActivated() == 0) || (w.getActivated() == 3)){
                            storeid = null;
                        }
                        else {
                            storeid = StoreChk.get(0).getStoreId();
                        }
                        break;
                    }
                }
            }

             /*   if(storechk.isPresent())
                storeid= storechk.get().getStoreId();
            else
                storeid = null;
            return new UserInfoFrontDto(usertemp,storeid);*/
            return new UserInfoFrontDto(usertemp,storeid);
        }
        else
            throw new IllegalArgumentException("???????????? ?????? ????????? ????????????.");

    }



    public Optional<Object> getRealname()
    {
        //???????????????
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            logger.debug("Security Context??? ?????? ????????? ????????????.");
            return Optional.empty();
        }

        String username = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            username = springSecurityUser.getUsername();

        } else if (authentication.getPrincipal() instanceof String) {
            username = (String) authentication.getPrincipal();
        }
        //???????????? Authority??? username ???????????? ??????

        Optional<User> found = userRepository.findByUsername(username);
        if(found.isPresent())
        return Optional.ofNullable(found.get().getRealname());

        else
            throw new IllegalArgumentException("???????????? ?????? ?????? ????????? ????????????.");

    }


    public void registerUser(SignupRequestDto requestDto) {
        // ?????? ID ?????? ??????
        String username = requestDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);

        if (found.isPresent()) {
            throw new IllegalArgumentException("?????? ????????? ID(username) ??? ???????????????.");
        }

        // ???????????? ?????????
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();
        String realname = requestDto.getRealname();
        String phone_number = requestDto.getPhone_number();

        Authority authority = new Authority();



        //if(requestDto.isAdmin())
        authority.setAuthorityName("ROLE_USER");
        //else
        // authority.setAuthorityName("ROLE_ADMIN");
        Set<Authority> authoritySet = new HashSet<Authority>();
        authoritySet.add(authority);
        Authority authority2 = new Authority();
        authority2.setAuthorityName("ROLE_ADMIN");
        authoritySet.add(authority2);


        User user = new User(username, password, email, realname,phone_number);
        //user.setAuthorities(Collections.singleton(authority));
        user.setAuthorities(authoritySet);
        userRepository.save(user);

    }
    /*
    //username?????? User?????? ??????
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