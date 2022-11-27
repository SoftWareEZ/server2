package com.albba.commute.service;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.ListDto;
import com.albba.commute.dto.MonthDto;
import com.albba.commute.dto.StartDto;
import com.albba.commute.model.Commute;
import com.albba.commute.repository.CommuteRepository;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.implementation.bind.MethodDelegationBinder;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class CommuteService {
    private final CommuteRepository commuteRepository;
    private final WorkInfoRepository workInfoRepository;

    private final StoreRepository storeRepository;
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
            commute.setTime(commuteRepository.maketime(commute.getStart(), endDto.getEnd()));
            commuteRepository.save(commute);
            return 1;
        } catch(Exception e){
            return -1;
        }

    }

    public List<String> Listyear(Long userId, String year){
        List<Commute> commute = commuteRepository.findCommuteByUserIdAndYear(userId, year);
        List<String> list = new ArrayList<>();

        for (Commute value : commute) {
            list.add(value.getMonth());
        }
        Set<String> list1 = new HashSet<>(list);
        return new ArrayList<>(list1);
    }

    public List<String> Listmonth(Long userId, String year, String month){
        List<Commute> commute = commuteRepository.findCommuteByUserIdAndYearAndMonth(userId, year, month);
        List<String> list = new ArrayList<>();
        for (Commute value : commute) {
            String store = storeRepository.findByStoreId(value.getStoreId()).getStoreName();
            list.add(value.getDay() + "일 "+ store + " " + value.getStart() + "-" + value.getEnd());
        }
        return list;
    }

    public class li{
        Long userId;
        int time = 0;
        li(Long userId){
            this.userId = userId;
        }
    }

    public List<Commute> Month(Long storeId, MonthDto monthDto){
        List<li> list = new ArrayList<>();
        List<Commute> commute = commuteRepository.findCommuteByStoreIdAndYearAndMonth(storeId, monthDto.getYear(), monthDto.getMonth());
        for (Commute item : commute) {
            li l = new li(item.getUserId());
            list.add(l);
        }

        for (int i = 0; i < commute.size(); i++) {
            for (CommuteService.li li : list) {
                if (li.userId.equals(commute.get(i).getUserId())) {
                    list.get(i).time += commute.get(i).getTime();
                }
            }
        }
        return commute;
    }

    public int Cost(Long userId, MonthDto monthDto) {
        int min = 0;
        try {
            WorkInfo workInfo = workInfoRepository.findByUserIdAndStoreId(userId, monthDto.getStoreId());
            List<Commute> commute = commuteRepository.findCommuteByUserIdAndStoreIdAndYearAndMonth(userId, monthDto.getStoreId(), monthDto.getYear(), monthDto.getMonth());
            for (Commute value : commute) {
                min += value.getTime();
            }
            return (min / 30) * (workInfo.getWage() / 2);
        } catch (Exception e){
            return -1;
        }
    }
}
