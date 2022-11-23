package com.albba.commute.service;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.ListDto;
import com.albba.commute.dto.MonthDto;
import com.albba.commute.dto.StartDto;
import com.albba.commute.model.Commute;
import com.albba.commute.repository.CommuteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommuteService {
    private final CommuteRepository commuteRepository;
    public int insert(StartDto startdto){
        try{
            if(commuteRepository.findCommuteByUserIdAndStoreIdAndYearAndMonthAndDay(startdto.getUserId(), startdto.getStoreId(), startdto.getYear(), startdto.getMonth(), startdto.getDay()).isPresent()){
                return -2;
            }
            Commute commute = new Commute(startdto);
            commuteRepository.save(commute);
            return 1;
        } catch(Exception e){
            return -1;
        }
    }

    public int update(EndDto endDto){
        try{
            Commute commute = commuteRepository.findCommuteByUserIdAndStoreIdAndYearAndMonthAndDay(endDto.getUserId(), endDto.getStoreId(), endDto.getYear(), endDto.getMonth(), endDto.getDay()).orElseThrow(() -> new NullPointerException("출근 정보가 존재하지 않습니다."));
            commute.setEnd(endDto.getEnd());
            commuteRepository.save(commute);
            return 1;
        } catch(Exception e){
            return -1;
        }

    }

    public List<Commute> List(ListDto listdto){
        return commuteRepository.findCommuteByUserId(listdto.getUserId());
    }

    public List<Commute> Month(MonthDto monthDto){
       return commuteRepository.findCommuteByStoreIdAndMonthAndUserId(monthDto.getStoreId(), monthDto.getMonth(), monthDto.getUserId());
    }
}
