package com.albba.work.controller;

import com.albba.work.dto.InfoDto;
import com.albba.work.dto.StoreDto;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.service.StoreService;
import com.albba.work.service.WorkInfoService;
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
    private final WorkInfoService workInfoService;

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

    //입사한 알바생 조회
    @GetMapping("/store/worker/list")
    public List<WorkInfo> getWorker() throws SQLException{
        return workInfoService.getWorker();
    }
    
    //알바생 알바정보 update
    @PostMapping("store/worker/setting/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public WorkInfo updateWorkInfo(@PathVariable Long userId, @RequestBody InfoDto infoDto) {return workInfoService.updateWorker(userId, infoDto);}

}
