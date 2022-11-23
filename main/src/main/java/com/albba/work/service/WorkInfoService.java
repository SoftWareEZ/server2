package com.albba.work.service;

import com.albba.work.dto.CodeDto;
import com.albba.work.dto.InfoDto;
import com.albba.work.dto.WorkInfoDto;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class WorkInfoService{
    private final WorkInfoRepository workInfoRepository;
    private final StoreRepository storeRepository;

    public WorkInfo joinStore(CodeDto codeDto, Long userId){
        String codeName = codeDto.getCode();
        Store store = storeRepository.findByCode(codeName);

        if(codeName.equals(store.getCode())){
            WorkInfo workInfo = new WorkInfo(userId, store.getStoreId(), 0, null);
            workInfoRepository.save(workInfo);
            return workInfo;
        }
        else {
            System.out.println("store 초대코드와 일치하지 않습니다");
            return null;
        }
    }

    public List<WorkInfo> getWorker(Long storeId) {
        List<WorkInfo> works = workInfoRepository.findByStoreId(storeId);
        return works;
    }

    public WorkInfo getWorkerById(Long userId){
        return workInfoRepository.findByUserId(userId);
    }

    public WorkInfo updateWorker(Long userId, InfoDto infoDto){
        WorkInfo work = workInfoRepository.findByUserId(userId);
        work.setWage(infoDto.getWage());
        work.setAccount(infoDto.getAccount());

        work.setMon_start(infoDto.getMon_start());
        work.setMon_end(infoDto.getMon_end());
        work.setTue_start(infoDto.getTue_start());
        work.setTue_end(infoDto.getTue_end());
        work.setWed_start(infoDto.getWed_start());
        work.setWed_end(infoDto.getWed_end());
        work.setThu_start(infoDto.getThu_start());
        work.setThu_end(infoDto.getThu_end());
        work.setFri_start(infoDto.getFri_start());
        work.setFri_end(infoDto.getFri_end());
        work.setSat_start(infoDto.getSat_start());
        work.setSat_end(infoDto.getSat_end());
        work.setSun_start(infoDto.getSun_start());
        work.setSun_end(infoDto.getSun_end());
        return workInfoRepository.save(work);
    }
}