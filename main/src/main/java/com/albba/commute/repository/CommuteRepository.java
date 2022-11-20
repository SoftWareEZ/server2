package com.albba.commute.repository;

import com.albba.commute.model.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuteRepository  extends JpaRepository<Commute, Long> {
    Commute findCommuteByUserIdAndStoreIdAndDate(Long userId, Long storeId, String date);
}
