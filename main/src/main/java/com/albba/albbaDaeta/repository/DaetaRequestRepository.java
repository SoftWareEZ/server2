package com.albba.albbaDaeta.repository;

import com.albba.albbaDaeta.table.DaetaRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DaetaRequestRepository extends JpaRepository<DaetaRequest,Long> {
    List<DaetaRequest> findDaetaRequestByStoreId(Long storeId);
    DaetaRequest findByNo(Long no);
}
