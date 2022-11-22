package com.albba.work.controller;

import com.albba.work.dto.CodeDto;
import com.albba.work.model.WorkInfo;
import com.albba.work.service.WorkInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RequestMapping("/albba")
@RestController
public class WorkInfoController {
    private final WorkInfoService workInfoService;
    
    //알바생이 초대 코드를 눌렀을 때 입사 요청 받아줌
    @PostMapping("/worker/join/{userId}")
    public WorkInfo joinStore(@RequestBody CodeDto codeDto, @PathVariable Long userId){
        return workInfoService.joinStore(codeDto, userId);
    }
}