package com.albba.work.repository;

import com.albba.work.model.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkInfoRepository extends JpaRepository<WorkInfo, Long> {
    WorkInfo findByUserId(Long userId);

    List<WorkInfo> findByStoreId(Long storeId);

    WorkInfo findByUserIdAndStoreId(Long userId, Long StoreId);
    List<WorkInfo> findByUserIdAndActivated(Long userId, boolean activated);
    List<WorkInfo> findByStoreIdAndActivated(Long storeId, boolean activated);

    List<WorkInfo> findByStoreIdAndMonStartNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndTueStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndWedStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndThuStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndFriStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndSatStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndSunStartIsNot(Long storeId, String check);

}
