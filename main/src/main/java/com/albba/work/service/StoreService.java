package com.albba.work.service;

import com.albba.work.model.Store;
import com.albba.work.dto.StoreDto;
import com.albba.work.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    //사업장 추가
    public Store addStore(StoreDto storeDto) {
        Store store = new Store(storeDto);
        store.setCode(storeRepository.makeCode());
        storeRepository.save(store);

        return store;
    }

    //모든 사업장 조회
    public List<Store> getStore(Long userId){
        List<Store> stores = storeRepository.findAllByUserId(userId);
        return stores;
    }

    //특정 사업장 조회
    public Store getStoreById(Long storeId){
        return storeRepository.findByStoreId(storeId);
    }

    //초대코드 조회
    public String getCode(Long storeId){
        Store store = storeRepository.findById(storeId).get();
        return store.getCode();
    }
}
