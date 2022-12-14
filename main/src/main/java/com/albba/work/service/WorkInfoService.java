package com.albba.work.service;

import com.albba.albbaUser.repository.UserRepository;
import com.albba.commute.dto.MonthDto;
import com.albba.commute.dto.MonthRequestDto;
import com.albba.commute.model.Commute;
import com.albba.commute.repository.CommuteRepository;
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
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class WorkInfoService{
    private final WorkInfoRepository workInfoRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;
    private final CommuteRepository commuteRepository;

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
                c.setUserId(w.getUserId());
                c.setName((userRepository.getById(w.getUserId()).getRealname()));
                c.setActivated(0);
                waitList.add(c);
            }
        }
        if(!leave.isEmpty()){
            for (WorkInfo w : leave) {
                checkInDto c = new checkInDto();
                c.setUserId(w.getUserId());
                c.setName((userRepository.getById(w.getUserId()).getRealname()));
                c.setActivated(2);
                waitList.add(c);
            }
        }

        if(waitList.isEmpty()) return null;

        return waitList;
    }

    public List<MonthRequestDto> getWorker(Long storeId, MonthDto monthDto) {
        List<WorkInfo> works = workInfoRepository.findByStoreIdAndActivated(storeId, 1);
        List<WorkInfo> works2 = workInfoRepository.findByStoreIdAndActivated(storeId, 2);
        List<MonthRequestDto> list = new ArrayList<>();
        for(WorkInfo work : works){
            list.add(new MonthRequestDto(userRepository.findByUserId((work.getUserId())).getRealname(),work.getUserId(),0));
        }

        for(WorkInfo work : works2){
            list.add(new MonthRequestDto(userRepository.findByUserId((work.getUserId())).getRealname(),work.getUserId(),0));
        }

        List<Commute> commute = commuteRepository.findCommuteByStoreIdAndYearAndMonth(storeId, monthDto.getYear(), monthDto.getMonth());

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

        // for(Commute x: commute)
        for(int i =0;i<tt.size();i++)
        {
            Commute x = commute.get(i);
            for(int j = 0; j < commute.size(); j++){
                if(Objects.equals(list.get(i).getUserId(), commute.get(j).getUserId())){
                    list.get(i).setTime(tt.get(commute.get(j).getUserId()));
                }
            }
        }
        return list;
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
                work = workInfoRepository.findByStoreIdAndMonStartNotAndActivatedNot(storeId, "null", 3);
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
                work = workInfoRepository.findByStoreIdAndTueStartIsNotAndActivatedNot(storeId, "null", 3);
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
                work = workInfoRepository.findByStoreIdAndWedStartIsNotAndActivatedNot(storeId, "null", 3);
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
                work = workInfoRepository.findByStoreIdAndThuStartIsNotAndActivatedNot(storeId, "null", 3);
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
                work = workInfoRepository.findByStoreIdAndFriStartIsNotAndActivatedNot(storeId, "null", 3);
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
                work = workInfoRepository.findByStoreIdAndSatStartIsNotAndActivatedNot(storeId, "null", 3);
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
                work = workInfoRepository.findByStoreIdAndSunStartIsNotAndActivatedNot(storeId, "null", 3);
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
        WorkInfo work = workInfoRepository.findByUserIdAndStoreIdAndActivated(userId, storeId, 1);
        work.setWage(infoDto.getWage());
        work.setAccount(infoDto.getAccount());

        work.setMonStart(infoDto.getMonStart());
        work.setMonEnd(infoDto.getMonEnd());
        work.setTueStart(infoDto.getTueStart());
        work.setTueEnd(infoDto.getTueEnd());
        work.setWedStart(infoDto.getWedStart());
        work.setWedEnd(infoDto.getWedEnd());
        work.setThuStart(infoDto.getThuStart());
        work.setThuEnd(infoDto.getThuEnd());
        work.setFriStart(infoDto.getFriStart());
        work.setFriEnd(infoDto.getFriEnd());
        work.setSatStart(infoDto.getSatStart());
        work.setSatEnd(infoDto.getSatEnd());
        work.setSunStart(infoDto.getSunStart());
        work.setSunEnd(infoDto.getSunEnd());
        return workInfoRepository.save(work);
    }
}