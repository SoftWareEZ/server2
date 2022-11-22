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
    private String mon_start, mon_end;

    @Column
    private String tue_start, tue_end;

    @Column
    private String wed_start, wed_end;

    @Column
    private String thu_start, thu_end;

    @Column
    private String fri_start, fri_end;

    @Column
    private String sat_start, sat_end;

    @Column
    private String sun_start, sun_end;

    public WorkInfo(Long userId, Long storeId, int wage, String account,
                    String mon_start, String mon_end, String tue_start, String tue_end, String wed_start, String wed_end,
                    String thu_start, String thu_end, String fri_start, String fri_end, String sat_start, String sat_end,
                    String sun_start, String sun_end){
        this.userId = userId;
        this.storeId = storeId;
        this.wage = wage;
        this.account = account;

        this.mon_start = mon_start;
        this.mon_end = mon_end;

        this.tue_start = tue_start;
        this.tue_end = tue_end;

        this.wed_start = wed_start;
        this.wed_end = wed_end;

        this.thu_start = thu_start;
        this.thu_end = thu_end;

        this.fri_start = fri_start;
        this.fri_end = fri_end;

        this.sat_start = sat_start;
        this.sat_end = sat_end;

        this.sun_start = sun_start;
        this.sun_end = sun_end;
    }

    public WorkInfo(Long userId, Long storeId, int wage, String account){
        this.userId = userId;
        this.storeId = storeId;
        this.wage = wage;
        this.account = account;

        this.mon_start = null;
        this.mon_end = null;

        this.tue_start = null;
        this.tue_end = null;

        this.wed_start = null;
        this.wed_end = null;

        this.thu_start = null;
        this.thu_end = null;

        this.fri_start = null;
        this.fri_end = null;

        this.sat_start = null;
        this.sat_end = null;

        this.sun_start = null;
        this.sun_end = null;
    }

    public WorkInfo(Long userId, Long storeId, WorkInfoDto workInfoDto){
        this.userId = userId;
        this.storeId = storeId;
        this.wage = workInfoDto.getWage();
        this.account = workInfoDto.getAccount();

        this.mon_start = workInfoDto.getMon_start();
        this.mon_end = workInfoDto.getMon_end();

        this.tue_start = workInfoDto.getTue_start();
        this.tue_end = workInfoDto.getTue_end();

        this.wed_start = workInfoDto.getWed_start();
        this.wed_end = workInfoDto.getWed_end();

        this.thu_start = workInfoDto.getThu_start();
        this.thu_end = workInfoDto.getThu_end();

        this.fri_start = workInfoDto.getFri_start();
        this.fri_end = workInfoDto.getFri_end();

        this.sat_start = workInfoDto.getSat_start();
        this.sat_end = workInfoDto.getSat_end();

        this.sun_start = workInfoDto.getSun_start();
        this.sun_end = workInfoDto.getSun_end();

    }
}