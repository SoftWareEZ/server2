package com.albba.work.service;

import com.albba.work.dto.CodeDto;
import com.albba.work.dto.InfoDto;
import com.albba.work.dto.ScheduleDto;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<WorkInfo> getWorkSchedule(Long storeId, String day){
        List<WorkInfo> work;
        switch(day){
            case "mon":
                work = workInfoRepository.findByStoreIdAndMonStartIsNotNull(storeId);
                break;
            case "tue":
                work = workInfoRepository.findByStoreIdAndTueStartIsNotNull(storeId);
                break;
            case "wed":
                work = workInfoRepository.findByStoreIdAndWedStartIsNotNull(storeId);
                break;
            case "thu":
                work = workInfoRepository.findByStoreIdAndThuStartIsNotNull(storeId);
                break;
            case "fri":
                work = workInfoRepository.findByStoreIdAndFriStartIsNotNull(storeId);
                break;
            case "sat":
                work = workInfoRepository.findByStoreIdAndSatStartIsNotNull(storeId);
                break;
            case "sun":
                work = workInfoRepository.findByStoreIdAndSunStartIsNotNull(storeId);
                break;
            default:
                return null;
        }
        return work;
//        List<ScheduleDto> schedule;
//        schedule.
    }

    public WorkInfo updateWorker(Long userId, InfoDto infoDto){
        WorkInfo work = workInfoRepository.findByUserId(userId);
        work.setWage(infoDto.getWage());
        work.setAccount(infoDto.getAccount());

        work.setMonStart(infoDto.getMon_start());
        work.setMonEnd(infoDto.getMon_end());
        work.setTueStart(infoDto.getTue_start());
        work.setTueEnd(infoDto.getTue_end());
        work.setWedStart(infoDto.getWed_start());
        work.setWedEnd(infoDto.getWed_end());
        work.setThuStart(infoDto.getThu_start());
        work.setThuEnd(infoDto.getThu_end());
        work.setFriStart(infoDto.getFri_start());
        work.setFriEnd(infoDto.getFri_end());
        work.setSatStart(infoDto.getSat_start());
        work.setSatEnd(infoDto.getSat_end());
        work.setSunStart(infoDto.getSun_start());
        work.setSunEnd(infoDto.getSun_end());
        return workInfoRepository.save(work);
    }
}