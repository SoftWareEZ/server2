package com.albba.commute.repository;

import com.albba.commute.model.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommuteRepository  extends JpaRepository<Commute, Long> {
    Optional<Commute> findCommuteByUserIdAndStoreIdAndDate(Long userId, Long storeId, String date);
    List<Commute> findCommuteByUserId(Long userId);
}
