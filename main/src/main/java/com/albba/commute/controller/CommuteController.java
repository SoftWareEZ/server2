package com.albba.commute.controller;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.ListDto;
import com.albba.commute.dto.MonthDto;
import com.albba.commute.dto.StartDto;
import com.albba.commute.model.Commute;
import com.albba.commute.service.CommuteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/albba/commute/*") // http://localhost/albba/commute/*
public class CommuteController {
    private final CommuteService commuteService;

    // 출근
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

    // 퇴근
    @PostMapping(path ="/end") // http://localhost/albba/commute/end
    public String End(@RequestBody EndDto endDto) {
        int flag = commuteService.update(endDto);
        if(flag == 1){
            return "퇴근 성공";
        } else{
            return "퇴근 실패";
        }
    }

    // 년도별 출근 기록 - 언제 일한 월인지 보임(내가 일하는 모든 store 포함)
    @GetMapping(path ="/list/{userId}/{year}") // http://localhost/albba/commute/list/{userId}/{year}
    public List<String> List(@PathVariable Long userId, @PathVariable String year) {
        return commuteService.Listyear(userId, year);
    }

    // 월별 출근 기록 - 월에 맞는 일(내가 일하는 모든 store 포함)
    @PostMapping(path ="/list/{userId}/{year}/{month}") // http://localhost/albba/commute/list/{userId}/{year}/{month}
    public List<String> Year(@PathVariable Long userId, @PathVariable String year, @PathVariable String month) {
        return commuteService.Listmonth(userId, year, month);
    }

    // 월별 근무시간(store마다 다름)
//    @PostMapping(path = "/month/{userId}")
//    public int Month(@PathVariable Long userId, @RequestBody MonthDto monthDto) {
//        if(commuteService.Month(userId, monthDto) == -1){
//            return 0;
//        } else {return commuteService.Month(userId, monthDto);}
//    }

    //월별 근무시간 수정 중 아직 미완
    @PostMapping(path = "/month/{storeId}")
    public List<Commute> Month(@PathVariable Long storeId, @RequestBody MonthDto monthDto) {
       return commuteService.Month(storeId, monthDto);
    }

    // 한달 월급 계산(30분 단위로 월급 계산)
    @PostMapping(path = "/cost/{userId}")
    public int Cost(@PathVariable Long userId, @RequestBody MonthDto monthDto) {
        if(commuteService.Cost(userId, monthDto) == -1){
            return 0;
        } else {return commuteService.Cost(userId, monthDto);}
    }
}
