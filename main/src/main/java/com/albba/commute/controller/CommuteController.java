package com.albba.commute.controller;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.ListDto;
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
    public Commute Start(@RequestBody StartDto startdto) {
        return commuteService.insert(startdto);
    }

    @PostMapping(path ="/end") // http://localhost/albba/commute/end
    public Commute End(@RequestBody EndDto endDto) {
       return commuteService.update(endDto);
    }

    @PostMapping(path ="/list") // http://localhost/albba/commute/list
    public List<Commute> List(@RequestBody ListDto listDto) {
        return commuteService.List(listDto);
    }
}
