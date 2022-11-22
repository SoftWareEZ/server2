package com.albba.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkInfoDto {
    private Long userId;
    private Long storeId;
    private int wage;
    private int account;

    private String mon_start, mon_end;
    private String tue_start, tue_end;
    private String wed_start, wed_end;
    private String thu_start, thu_end;
    private String fri_start, fri_end;
    private String sat_start, sat_end;
    private String sun_start, sun_end;
}