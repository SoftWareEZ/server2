package com.albba.work.model;

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

    @Column(name = "mon_start")
    private String monStart;

    @Column(name = "mon_end")
    private String monEnd;

    @Column(name = "tue_start")
    private String tueStart;
    @Column(name = "tue_end")
    private String tueEnd;

    @Column(name = "wed_start")
    private String wedStart;
    @Column(name = "wed_end")
    private String wedEnd;

    @Column(name = "thu_start")
    private String thuStart;
    @Column(name = "thu_end")
    private String thuEnd;

    @Column(name = "fri_start")
    private String friStart;

    @Column(name = "fri_end")
    private String friEnd;

    @Column(name = "sat_start")
    private String satStart;
    @Column(name = "sat_end")
    private String satEnd;

    @Column(name = "sun_start")
    private String sunStart;
    @Column(name = "sun_end")
    private String sunEnd;

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