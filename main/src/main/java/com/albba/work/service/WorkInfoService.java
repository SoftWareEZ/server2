package com.albba.work.service;

import com.albba.albbaUser.repository.UserRepository;
import com.albba.work.dto.CodeDto;
import com.albba.work.dto.InfoDto;
import com.albba.work.dto.checkInDto;
import com.albba.work.model.Schedule;
import com.albba.work.model.Store;
import com.albba.work.model.WorkInfo;
import com.albba.work.repository.StoreRepository;
import com.albba.work.repository.WorkInfoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.jdbc.Work;
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
        work.setActivated(1);
        return workInfoRepository.save(work);
    }

    public String signUpStore(CodeDto codeDto, Long userId){
        String codeName = codeDto.getCode();
        Store store = storeRepository.findByCode(codeName);

        if(store == null) return "fail";

        if(codeName.equals(store.getCode())){
            WorkInfo workInfo = new WorkInfo(userId, store.getStoreId(), 0, null);
            workInfoRepository.save(workInfo);
            return "success";
        }
        else {
            System.out.println("store 초대코드와 일치하지 않습니다");
            return "fail";
        }
    }

    public String leaveStore(Long userId, Long storeId){
        WorkInfo work = workInfoRepository.findByUserIdAndStoreId(userId, storeId);

        if(work == null) return "fail";
        else{
            work.setActivated(2);
            workInfoRepository.save(work);
            return "success";
        }
    }

    public String leaveCheck(Long userId, Long storeId){
        WorkInfo work = workInfoRepository.findByUserIdAndStoreIdAndActivated(userId, storeId, 2);

        if(work == null) return "fail";
        else {
            work.setActivated(3);
            workInfoRepository.save(work);
            return "success";
        }
    }

    public String rejectJoin(Long storeId, Long userId){
        WorkInfo work = workInfoRepository.findByUserIdAndStoreIdAndActivated(userId, storeId, 0);

        if(work == null) return "fail";
        else{
            workInfoRepository.delete(work);
            return "success";
        }
    }

    public String rejectLeave(Long storeId, Long userId){
        WorkInfo work = workInfoRepository.findByUserIdAndStoreIdAndActivated(userId, storeId, 2);

        if(work == null) return "fail";
        else{
            work.setActivated(1);
            workInfoRepository.save(work);
            return "success";
        }
    }

    public List<checkInDto> checkIn(Long storeId){
        List<WorkInfo> join = workInfoRepository.findByStoreIdAndActivated(storeId, 0);
        List<WorkInfo> leave = workInfoRepository.findByStoreIdAndActivated(storeId, 2);

        List<checkInDto> waitList = new ArrayList<>();

        if(!join.isEmpty()) {
            for (WorkInfo w : join) {
                checkInDto c = new checkInDto();
                c.setName((userRepository.getById(w.getUserId()).getRealname()));
                c.setActivated(0);
                waitList.add(c);
            }
        }
        if(!leave.isEmpty()){
            for (WorkInfo w : leave) {
                checkInDto c = new checkInDto();
                c.setName((userRepository.getById(w.getUserId()).getRealname()));
                c.setActivated(2);
                waitList.add(c);
            }
        }

        if(waitList.isEmpty()) return null;

        return waitList;
    }

    public List<WorkInfo> getWorker(Long storeId) {
        List<WorkInfo> works = workInfoRepository.findByStoreIdAndActivated(storeId, 1);
        return works;
    }

    public WorkInfo getWorkerByUserIdAndStoreId(Long storeId, Long userId){
        return workInfoRepository.findByUserIdAndStoreId(userId, storeId);
    }

    public List<WorkInfo> getWorkerById(Long userId){
        return workInfoRepository.findByUserIdAndActivated(userId, 1);
    }

    public List<Schedule> getWorkSchedule(Long storeId, String day) {
        List<WorkInfo> work;
        List<Schedule> schedule = new ArrayList<>();
        switch (day) {
            case "mon":
                work = workInfoRepository.findByStoreIdAndMonStartNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getMonStart());
                    s.setEnd(workInfo.getMonEnd());
                    s.setUserId(workInfo.getUserId());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "tue":
                work = workInfoRepository.findByStoreIdAndTueStartIsNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getTueStart());
                    s.setEnd(workInfo.getTueEnd());
                    s.setUserId(workInfo.getUserId());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "wed":
                work = workInfoRepository.findByStoreIdAndWedStartIsNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getWedStart());
                    s.setEnd(workInfo.getWedEnd());
                    s.setUserId(workInfo.getUserId());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "thu":
                work = workInfoRepository.findByStoreIdAndThuStartIsNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getThuStart());
                    s.setEnd(workInfo.getThuEnd());
                    s.setUserId(workInfo.getUserId());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "fri":
                work = workInfoRepository.findByStoreIdAndFriStartIsNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getFriStart());
                    s.setEnd(workInfo.getFriEnd());
                    s.setUserId(workInfo.getUserId());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "sat":
                work = workInfoRepository.findByStoreIdAndSatStartIsNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getSatStart());
                    s.setEnd(workInfo.getSatEnd());
                    s.setUserId(workInfo.getUserId());
                    s.setName((userRepository.getById(workInfo.getUserId()).getRealname()));
                    schedule.add(s);
                }
                return schedule;
            case "sun":
                work = workInfoRepository.findByStoreIdAndSunStartIsNot(storeId, "null");
                if (work.isEmpty()) return null;
                for (WorkInfo workInfo : work) {
                    Schedule s = new Schedule();
                    s.setStart(workInfo.getSunStart());
                    s.setEnd(workInfo.getSunEnd());
                    s.setUserId(workInfo.getUserId());
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