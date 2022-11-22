package com.albba.work.controller;

import com.albba.work.dto.StoreDto;
import com.albba.work.model.Store;
import com.albba.work.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/albba")
@RestController
public class StoreController {
    private final StoreService storeService;

    //사업장 추가
    @PostMapping("/store/add")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Store addStore(@RequestBody StoreDto storeDto) {
        return storeService.addStore(storeDto);
    }

    //사업장 조회
    @GetMapping("/store/list")
    public List<Store> getStore()throws SQLException {
        return storeService.getStore();
    }

    //사업장 초대코드 만들기
    @PostMapping("/store/invite/{storeId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String sendCode(@PathVariable Long storeId){
        return storeService.getCode(storeId);
    }

    //@PostMapping("/store/workSetting/")
}
