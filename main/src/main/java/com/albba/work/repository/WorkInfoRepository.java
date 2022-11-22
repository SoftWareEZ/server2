package com.albba.work.repository;

import com.albba.work.model.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkInfoRepository extends JpaRepository<WorkInfo, Long> {
    WorkInfo findByUserId(Long userId);

}
