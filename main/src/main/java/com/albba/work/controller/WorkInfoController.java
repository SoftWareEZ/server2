package com.albba.work.controller;

import com.albba.work.dto.CodeDto;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.service.StoreService;
import com.albba.work.service.WorkInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/albba")
@RestController
public class WorkInfoController {
    private final WorkInfoService workInfoService;
    private final StoreService storeService;
    
    //알바생이 초대 코드를 눌렀을 때 입사 요청 받아줌
    @PostMapping("/worker/join/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public WorkInfo joinStore(@RequestBody CodeDto codeDto, @PathVariable Long userId){
        return workInfoService.joinStore(codeDto, userId);
    }

    //알바생 워크 플레이스 조회
    @GetMapping("/worker/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public String getWorkerById(@PathVariable Long userId){
        WorkInfo work = workInfoService.getWorkerById(userId);
        Store store = storeService.getStoreById(work.getStoreId());
        return store.getStoreName();
    }

    //한달 근무표
    @GetMapping("/{storeId}/{day}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<WorkInfo> getWorkSchedule(@PathVariable Long storeId, @PathVariable String day){
        return workInfoService.getWorkSchedule(storeId, day);
    }
}