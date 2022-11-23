package com.albba.commute.repository;

import com.albba.commute.model.Commute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommuteRepository  extends JpaRepository<Commute, Long> {
    Optional<Commute> findCommuteByUserIdAndStoreIdAndYearAndMonthAndDay(Long userId, Long storeId, String year, String month, String day);
    List<Commute> findCommuteByUserId(Long userId);

    @Query("select c from Commute c where c.storeId =:storeId and c.month =:month and c.userId =:userId" )
    List<Commute> findCommuteByStoreIdAndMonthAndUserId(@Param("storeId")Long storeId, @Param("month")String month, @Param("userId")Long userId);

    default double maketime(String start, String end){
        return Double.parseDouble(end) - Double.parseDouble(start);
    }
}
