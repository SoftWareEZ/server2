package com.albba.commute.service;

import com.albba.albbaUser.repository.UserRepository;
import com.albba.commute.dto.*;
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
    private final UserRepository userRepository;

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

    public Set<ListyearDto> Listyear(Long userId, String year){
        List<Commute> commute = commuteRepository.findCommuteByUserIdAndYear(userId, year);
        Set<ListyearDto> list = new HashSet<>();

        for (Commute value : commute) {
            if(value.getEnd() != null){
                list.add(new ListyearDto(Integer.parseInt(value.getMonth())));
            }
        }
        return list;
    }

    public List<ListmonthDto> Listmonth(Long userId, String year, String month){
        List<Commute> commute = commuteRepository.findCommuteByUserIdAndYearAndMonth(userId, year, month);
        List<ListmonthDto> list = new ArrayList<>();
        for (Commute value : commute) {
            if(value.getEnd() != null) {
                String store = storeRepository.findByStoreId(value.getStoreId()).getStoreName();

                list.add(new ListmonthDto(value.getDay() + "일 " + store + " " + value.getStart() + "-" + value.getEnd()));
            }
        }

        return list;
    }


    public List<MonthRequestDto>  Month(Long storeId, MonthDto monthDto){
        List<Commute> commute = commuteRepository.findCommuteByStoreIdAndYearAndMonth(storeId, monthDto.getYear(), monthDto.getMonth());
//
        HashMap<Long,Double> tt = new HashMap<Long, Double>();

        for(Commute x : commute)
        {
            if(tt.containsKey(x.getUserId()))
            {
                Double tmp = tt.get(x.getUserId());
                tmp += x.getTime();

                tt.replace(x.getUserId(),tmp);
            }
            else
            {
                tt.put(x.getUserId(),x.getTime());
            }
        }
        List<MonthRequestDto> List = new ArrayList<MonthRequestDto>();
        // for(Commute x: commute)
        for(int i =0;i<tt.size();i++)
        {
            Commute x = commute.get(i);
            List.add(new MonthRequestDto(userRepository.findByUserId((x.getUserId())).getRealname(),x.getUserId(),tt.get(x.getUserId())));
        }
        return List;
    }

    public int Cost(Long userId, CostDto costDto) {
        int min = 0;
        try {
            WorkInfo workInfo = workInfoRepository.findByUserIdAndStoreId(userId, costDto.getStoreId());
            List<Commute> commute = commuteRepository.findCommuteByUserIdAndStoreIdAndYearAndMonth(userId, costDto.getStoreId(), costDto.getYear(), costDto.getMonth());
            for (Commute value : commute) {
                min += value.getTime();
            }
            return (min / 30) * (workInfo.getWage() / 2);
        } catch (Exception e){
            return -1;
        }
    }
}
