package com.albba.work.controller;

import com.albba.commute.dto.MonthDto;
import com.albba.commute.dto.MonthRequestDto;
import com.albba.work.dto.InfoDto;
import com.albba.work.dto.StoreDto;
import com.albba.work.dto.checkInDto;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.service.StoreService;
import com.albba.work.service.WorkInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
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
    @GetMapping("/store/list/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<Store> getStore(@PathVariable Long userId) {
        return storeService.getStore(userId);
    }

    //사업장 초대코드 조회
    @GetMapping("/store/invite/{storeId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String sendCode(@PathVariable Long storeId){
        return storeService.getCode(storeId);
    }
    
    //입사 요청 받기
    @GetMapping("/store/{storeId}/join/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public WorkInfo joinStore(@PathVariable Long storeId, @PathVariable Long userId){
        return workInfoService.joinStore(storeId, userId);
    }

    //입사 요청 거절
    @GetMapping("/store/{storeId}/rejectJoin/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String rejectJoin(@PathVariable Long storeId, @PathVariable Long userId) {
        return workInfoService.rejectJoin(storeId, userId);
    }

    //퇴사 요청 받기
    @GetMapping("/store/{storeId}/leave/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String leaveCheck(@PathVariable Long storeId, @PathVariable Long userId){
        return workInfoService.leaveCheck(userId, storeId);
    }

    //퇴사 요청 거절
    @GetMapping("/store/{storeId}/rejectLeave/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public String rejectLeave(@PathVariable Long storeId, @PathVariable Long userId) {
        return workInfoService.rejectLeave(storeId, userId);
    }

    //입퇴사 대기 조회 화면
    @GetMapping("/store/{storeId}/checkIn")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<checkInDto> checkIn(@PathVariable Long storeId){
        return workInfoService.checkIn(storeId);
    }

    //입사한 알바생 조회
    @PostMapping("/store/{storeId}/worker/list")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public List<MonthRequestDto> getWorker(@PathVariable Long storeId, @RequestBody MonthDto monthDto){
        return workInfoService.getWorker(storeId, monthDto);
    }

    //입사한 특정 알바생 조회
    @GetMapping("/store/{storeId}/worker/list/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public WorkInfo getWorkerById(@PathVariable Long storeId, @PathVariable Long userId){
        return workInfoService.getWorkerByUserIdAndStoreId(storeId, userId);
    }
    
    //알바생 알바정보 update
    @PostMapping("store/{storeId}/worker/setting/{userId}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public WorkInfo updateWorkInfo(@PathVariable Long storeId, @PathVariable Long userId, @RequestBody InfoDto infoDto) {
        return workInfoService.updateWorker(storeId, userId, infoDto);
    }

}
