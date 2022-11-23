package com.albba.work.repository;

import com.albba.work.model.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkInfoRepository extends JpaRepository<WorkInfo, Long> {
    WorkInfo findByUserId(Long userId);

    List<WorkInfo> findByStoreId(Long storeId);
}
