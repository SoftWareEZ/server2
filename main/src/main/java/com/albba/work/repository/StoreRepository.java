package com.albba.work.repository;

import com.albba.work.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Random;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // Optional<Store> findById(Long userId);
     Optional<Store> findByUserId(Long userId);

    default String makeCode(){
        Random random = new Random();
        int createNum = 0;
        String ranNum = "";
        int letter = 8;
        String resultNum ="";

        for(int i = 0; i < letter; i++){
            createNum = random.nextInt(9);
            ranNum = Integer.toString(createNum);
            resultNum += ranNum;
        }
        return resultNum;
    }
}
