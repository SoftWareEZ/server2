package com.albba.commute.repository;

import com.albba.commute.model.Commute;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommuteRepository  extends JpaRepository<Commute, Long> {
    Optional<Commute> findCommuteByUserIdAndStoreIdAndYearAndMonthAndDay(Long userId, Long storeId, String year, String month, String day);

    List<Commute> findCommuteByUserIdAndYearAndMonth(Long UserId, String year, String month);

    List<Commute> findCommuteByStoreIdAndYearAndMonth(Long storeId, String year, String Month);
    List<Commute> findCommuteByUserIdAndStoreIdAndYearAndMonth(Long userId, Long storeId, String year, String Month);

    List<Commute> findCommuteByUserIdAndYear(Long userId, String year);
    default double maketime(String start, String end){
        double hour = Double.parseDouble(end.split(":")[0]) - Double.parseDouble(start.split(":")[0]);
        double endmin =  Double.parseDouble(end.split(":")[1]);
        double startmin = Double.parseDouble(start.split(":")[1]);
        double totalmin;
        if(endmin < startmin){
            hour -= hour;
            totalmin = 60 - startmin + endmin;
        } else{
            totalmin = endmin - startmin;
        }
        return hour * 60 + totalmin;
    }
}
