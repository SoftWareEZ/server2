package com.albba.commute.service;

import com.albba.commute.dto.StartDto;
import com.albba.commute.model.Commute;
import com.albba.commute.repository.CommuteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CommuteService {
    private final CommuteRepository commuteRepository;
    public Commute insert(StartDto startdto){
        Commute commute = new Commute(startdto);
        commuteRepository.save(commute);
        return commute;
    }

//    public Commute update(EndDto endDto){
//        Commute commute = commuteRepository.findCommuteByUserIdAndStoreIdAndDate(endDto.getUserId(), endDto.getStoreId(), endDto.getDate()).orElse(null);
//        commuteRepository.save()
//
//    }
}
