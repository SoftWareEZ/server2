package com.albba.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkInfoDto {
    private Long userId;
    private Long storeId;
    private int wage;
    private String account;

    private String monStart, monEnd;
    private String tueStart, tueEnd;
    private String wedStart, wedEnd;
    private String thuStart, thuEnd;
    private String friStart, friEnd;
    private String satStart, satEnd;
    private String sunStart, sunEnd;
}