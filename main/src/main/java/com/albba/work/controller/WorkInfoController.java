package com.albba.work.controller;

import com.albba.work.dto.CodeDto;
import com.albba.work.model.Schedule;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.service.StoreService;
import com.albba.work.service.WorkInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@RequestMapping("/albba")
@RestController
public class WorkInfoController {
    private final WorkInfoService workInfoService;
    private final StoreService storeService;
    
    //알바생이 초대 코드를 눌렀을 때 입사 요청 전송
    @PostMapping("worker/signup/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public void signUpStore(@RequestBody CodeDto codeDto, @PathVariable Long userId){
        workInfoService.signUpStore(codeDto, userId);
    }

    //알바생 워크 플레이스 조회
    @GetMapping("/worker/{userId}")
    @PreAuthorize("hasAnyRole('USER')")
    public List<Store> getWorkerById(@PathVariable Long userId){
        List<WorkInfo> work = workInfoService.getWorkerById(userId);

        List<Store> store = new ArrayList<>();
        for(WorkInfo workInfo : work){
            Store s = storeService.getStoreById(workInfo.getStoreId());
            store.add(s);
        }
        return store;
    }

    //한달 근무표
    @GetMapping("/{storeId}/{day}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Schedule> getWorkSchedule(@PathVariable Long storeId, @PathVariable String day){
        return workInfoService.getWorkSchedule(storeId, day);
    }
}