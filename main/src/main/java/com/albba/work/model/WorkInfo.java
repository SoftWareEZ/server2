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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer no;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "store_id")
    private Long storeId;

    @Column
    private int wage;
    @Column
    private int account;

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

    public WorkInfo(Long userId, Long storeId, int wage, int account,
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

    public WorkInfo(WorkInfoDto workInfoDto){
        this.userId = workInfoDto.getUserId();
        this.storeId = workInfoDto.getStoreId();
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