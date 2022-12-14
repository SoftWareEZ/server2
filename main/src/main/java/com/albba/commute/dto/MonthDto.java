package com.albba.commute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class MonthDto {
    @JsonFormat(pattern = "yyyy")
    private String year;

    @JsonFormat(pattern = "MM")
    private String month;

    private double time; // 기준은 분

   // private String userName;
}
