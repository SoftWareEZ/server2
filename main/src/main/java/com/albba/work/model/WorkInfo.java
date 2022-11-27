package com.albba.work.model;

import com.albba.work.dto.WorkInfoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class WorkInfo{
    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "store_id")
    private Long storeId;

    @Column
    private int wage;
    @Column
    private String account;
    @Column
    private int activated;

    @Column
    private String monStart, monEnd;

    @Column
    private String tueStart, tueEnd;

    @Column
    private String wedStart, wedEnd;

    @Column
    private String thuStart, thuEnd;

    @Column
    private String friStart, friEnd;

    @Column
    private String satStart, satEnd;

    @Column
    private String sunStart, sunEnd;

    public WorkInfo(Long userId, Long storeId, int wage, String account){
        this.userId = userId;
        this.storeId = storeId;
        this.wage = wage;
        this.account = account;
        this.activated = 0;

        this.monStart = null;
        this.monEnd = null;

        this.tueStart = null;
        this.tueEnd = null;

        this.wedStart = null;
        this.wedEnd = null;

        this.thuStart = null;
        this.thuEnd = null;

        this.friStart = null;
        this.friEnd = null;

        this.satStart = null;
        this.satEnd = null;

        this.sunStart = null;
        this.sunEnd = null;
    }

//    public WorkInfo(Long userId, Long storeId, WorkInfoDto workInfoDto){
//        this.userId = userId;
//        this.storeId = storeId;
//        this.wage = workInfoDto.getWage();
//        this.account = workInfoDto.getAccount();
//
//        this.mon_start = workInfoDto.getMon_start();
//        this.mon_end = workInfoDto.getMon_end();
//
//        this.tue_start = workInfoDto.getTue_start();
//        this.tue_end = workInfoDto.getTue_end();
//
//        this.wed_start = workInfoDto.getWed_start();
//        this.wed_end = workInfoDto.getWed_end();
//
//        this.thu_start = workInfoDto.getThu_start();
//        this.thu_end = workInfoDto.getThu_end();
//
//        this.fri_start = workInfoDto.getFri_start();
//        this.fri_end = workInfoDto.getFri_end();
//
//        this.sat_start = workInfoDto.getSat_start();
//        this.sat_end = workInfoDto.getSat_end();
//
//        this.sun_start = workInfoDto.getSun_start();
//        this.sun_end = workInfoDto.getSun_end();
//
//    }
}