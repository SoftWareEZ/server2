package com.albba.albbaDaeta.repository;

import com.albba.albbaDaeta.table.Daeta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaetaRepository extends JpaRepository<Daeta, Long> {
    List<Daeta> findDaetasByDateAndStoreId(Long date,Long storeId);

    List<Daeta> findDaetasByStoreIdAndApproved(Long storeId,Long approved);
    List<Daeta> findDaetasByDateAndStoreIdAndApproved(Long date,Long storeId,Long approved);
}
