package com.albba.work.service;

import com.albba.work.dto.CodeDto;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class WorkInfoService{
    private final WorkInfoRepository workInfoRepository;
    private final StoreRepository storeRepository;

    public WorkInfo joinStore(CodeDto codeDto, Long userId){
        String codeName = codeDto.getCode();
        Store store = storeRepository.findByCode(codeName).get();
        
//        if(codeName.isEqual()){
//
//        }

        WorkInfo workInfo = new WorkInfo(userId, store.getStoreId(), 0, 0, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        workInfoRepository.save(workInfo);

        return workInfo;
    }
}