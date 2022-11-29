package com.albba.work.repository;

import com.albba.work.model.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkInfoRepository extends JpaRepository<WorkInfo, Long> {
    WorkInfo findByUserIdAndStoreId(Long userId, Long StoreId);
    WorkInfo findByUserIdAndStoreIdAndActivated(Long userId, Long StoreId, int activated);
    List<WorkInfo> findByUserIdAndActivated(Long userId, int activated);
    List<WorkInfo> findByStoreIdAndActivated(Long storeId, int activated);

    List<WorkInfo> findByStoreIdAndMonStartNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndTueStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndWedStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndThuStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndFriStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndSatStartIsNot(Long storeId, String check);
    List<WorkInfo> findByStoreIdAndSunStartIsNot(Long storeId, String check);

    List<WorkInfo> findWorkInfosByUserId(Long userId);

}
