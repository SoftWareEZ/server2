package com.albba.work.controller;

import com.albba.work.dto.CodeDto;
import com.albba.work.model.WorkInfo;
import com.albba.work.service.WorkInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@RequestMapping("/albba")
@RestController
public class WorkInfoController {
    private final WorkInfoService workInfoService;

    @PostMapping("/worker/join/{userId}")
    public WorkInfo joinStore(@RequestBody CodeDto codeDto, @PathVariable Long userId){
        return workInfoService.joinStore(codeDto, userId);
    }
}