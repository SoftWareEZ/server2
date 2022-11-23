package com.albba.commute.controller;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.ListDto;
import com.albba.commute.dto.MonthDto;
import com.albba.commute.dto.StartDto;
import com.albba.commute.model.Commute;
import com.albba.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albba/commute/*") // http://localhost/albba/commute/*
public class CommuteController {
    private final CommuteService commuteService;

    @PostMapping(path ="/start") // http://localhost/albba/commute/start
    public String Start(@RequestBody StartDto startdto) {
        int flag = commuteService.insert(startdto);
        if(flag == 1){
            return "출근 성공";
        } else if(flag == -2){
            return "이미 출근된 상태입니다.";
        }
        else{
            return "출근 실패";
        }
    }

    @PostMapping(path ="/end") // http://localhost/albba/commute/end
    public String End(@RequestBody EndDto endDto) {
        int flag = commuteService.update(endDto);
        if(flag == 1){
            return "퇴근 성공";
        } else{
            return "퇴근 실패";
        }
    }

    @PostMapping(path ="/list") // http://localhost/albba/commute/list
    public List<Commute> List(@RequestBody ListDto listDto) {
        return commuteService.List(listDto);
    }

    @PostMapping(path = "/month")
    public List<Commute> Month(@RequestBody MonthDto monthDto) {return commuteService.Month(monthDto);}
}
