package com.albba.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class InfoDto {
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