package com.albba.commute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndDto {
    private Long userId;

    private Long storeId;

    @JsonFormat(pattern = "yyyy")
    private String year;

    @JsonFormat(pattern = "MM")
    private String month;

    @JsonFormat(pattern = "dd")
    private String day;

    @JsonFormat(pattern = "HH:mm")
    private String end;
}
