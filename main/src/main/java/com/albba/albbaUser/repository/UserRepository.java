package com.albba.albbaUser.repository;

import com.albba.albbaUser.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);//id로 검색하는거
    //신혜민쪽(Commute)
    User findByUserId(Long id);
    //권한 정보도 같이 가져오는 것, Eager -> authorities
    @EntityGraph(attributePaths = "authorities")
    Optional<User> findOneWithAuthoritiesByUsername(String username);

    Optional<User> findByKakaoId(String kakaoId);

    //로그인때 회원권한 확인

}
