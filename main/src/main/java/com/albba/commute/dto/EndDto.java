package com.albba.commute.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EndDto {
    private Long userId;

    private Long storeId;

    @JsonFormat(pattern = "YYYY-MM-dd")
    private String date;

    @JsonFormat(pattern = "HH:mm")
    private String end;
}
