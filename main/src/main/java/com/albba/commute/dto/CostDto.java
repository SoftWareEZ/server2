package com.albba.commute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CostDto {
    private Long storeId;

    @JsonFormat(pattern = "yyyy")
    private String year;

    @JsonFormat(pattern = "MM")
    private String month;
}
