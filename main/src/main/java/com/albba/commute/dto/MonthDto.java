package com.albba.commute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class MonthDto {
    private Long userId;

    private Long storeId;

    @JsonFormat(pattern = "MM")
    private String month;

    private double time;
}
