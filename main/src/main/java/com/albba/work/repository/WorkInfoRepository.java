package com.albba.work.repository;

import com.albba.work.model.WorkInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkInfoRepository extends JpaRepository<WorkInfo, Long> {
    WorkInfo findByUserIdAndStoreId(Long userId, Long StoreId);
    WorkInfo findByUserIdAndStoreIdAndActivated(Long userId, Long StoreId, int activated);
    List<WorkInfo> findByUserIdAndActivated(Long userId, int activated);
    List<WorkInfo> findByStoreIdAndActivated(Long storeId, int activated);

    List<WorkInfo> findByStoreIdAndMonStartNotAndActivatedNot(Long storeId, String check, int activated);
    List<WorkInfo> findByStoreIdAndTueStartIsNotAndActivatedNot(Long storeId, String check, int activated);
    List<WorkInfo> findByStoreIdAndWedStartIsNotAndActivatedNot(Long storeId, String check, int activated);
    List<WorkInfo> findByStoreIdAndThuStartIsNotAndActivatedNot(Long storeId, String check, int activated);
    List<WorkInfo> findByStoreIdAndFriStartIsNotAndActivatedNot(Long storeId, String check, int activated);
    List<WorkInfo> findByStoreIdAndSatStartIsNotAndActivatedNot(Long storeId, String check, int activated);
    List<WorkInfo> findByStoreIdAndSunStartIsNotAndActivatedNot(Long storeId, String check, int activated);

    List<WorkInfo> findWorkInfosByUserId(Long userId);

}
