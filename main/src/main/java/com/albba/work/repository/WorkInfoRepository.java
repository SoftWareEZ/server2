package com.albba.work.repository;

import com.albba.work.model.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkInfoRepository extends JpaRepository<WorkInfo, Long> {
    WorkInfo findByUserId(Long userId);

    List<WorkInfo> findByStoreId(Long storeId);

    List<WorkInfo> findByStoreIdAndMonStartIsNotNull(Long storeId);
    List<WorkInfo> findByStoreIdAndTueStartIsNotNull(Long storeId);
    List<WorkInfo> findByStoreIdAndWedStartIsNotNull(Long storeId);
    List<WorkInfo> findByStoreIdAndThuStartIsNotNull(Long storeId);
    List<WorkInfo> findByStoreIdAndFriStartIsNotNull(Long storeId);
    List<WorkInfo> findByStoreIdAndSatStartIsNotNull(Long storeId);
    List<WorkInfo> findByStoreIdAndSunStartIsNotNull(Long storeId);


}
