package com.albba.work.service;

import com.albba.work.model.Store;
import com.albba.work.dto.StoreDto;
import com.albba.work.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
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

    //사업장 조회
    public List<Store> getStore() throws SQLException {
        List<Store> stores = storeRepository.findAll();
        return stores;
    }

    //초대코드 조회
    public String getCode(Long storeId){
        Store store = storeRepository.findById(storeId).get();
        return store.getCode();
    }
}
