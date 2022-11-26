package com.albba.work.service;

import com.albba.albbaUser.repository.UserRepository;
import com.albba.work.dto.CodeDto;
import com.albba.work.dto.InfoDto;
import com.albba.work.model.Schedule;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.ScheduleRepository;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class WorkInfoService{
    private final WorkInfoRepository workInfoRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public WorkInfo joinStore(Long storeId, Long userId){
        WorkInfo work = workInfoRepository.findByUserIdAndStoreId(userId, storeId);
        work.setActivated(true);
        return workInfoRepository.save(work);
    }

    public void signUpStore(CodeDto codeDto, Long userId){
        String codeName = codeDto.getCode();
        Store store = storeRepository.findByCode(codeName);

        if(codeName.equals(store.getCode())){
            WorkInfo workInfo = new WorkInfo(userId, store.getStoreId(), 0, null);
            workInfoRepository.save(workInfo);
        }
        else {
            System.out.println("store 초대코드와 일치하지 않습니다");
        }
    }

    public List<WorkInfo> getWorker(Long storeId) {
        List<WorkInfo> works = workInfoRepository.findByStoreIdAndActivated(storeId, true);
        return works;
    }

    public WorkInfo getWorkerByUserIdAndStoreId(Long storeId, Long userId){
        return workInfoRepository.findByUserIdAndStoreId(userId, storeId);
    }

    public List<WorkInfo> getWorkerById(Long userId){
        return workInfoRepository.findByUserIdAndActivated(userId, true);
    }

    public List<Schedule> getWorkSchedule(Long storeId, String day) {
        List<WorkInfo> work;
        List<Schedule> schedule = new ArrayList<>();
        switch (day) {
            case "mon":
                work = workInfoRepository.findByStoreIdAndMonStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getMonStart());
                    s.setEnd(workInfo.getMonEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "tue":
                work = workInfoRepository.findByStoreIdAndTueStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getTueStart());
                    s.setEnd(workInfo.getTueEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "wed":
                work = workInfoRepository.findByStoreIdAndWedStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getWedStart());
                    s.setEnd(workInfo.getWedEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "thu":
                work = workInfoRepository.findByStoreIdAndThuStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getThuStart());
                    s.setEnd(workInfo.getThuEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "fri":
                work = workInfoRepository.findByStoreIdAndFriStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getFriStart());
                    s.setEnd(workInfo.getFriEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "sat":
                work = workInfoRepository.findByStoreIdAndSatStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getSatStart());
                    s.setEnd(workInfo.getSatEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "sun":
                work = workInfoRepository.findByStoreIdAndSunStartIsNotNull(storeId);
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getSunStart());
                    s.setEnd(workInfo.getSunEnd());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            default:
                return null;
        }
    }

    public WorkInfo updateWorker(Long storeId, Long userId, InfoDto infoDto){
        WorkInfo work = workInfoRepository.findByUserIdAndStoreId(userId, storeId);
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