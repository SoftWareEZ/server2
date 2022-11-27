package com.albba.commute.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListmonthDto {
    private String info;

    public ListmonthDto(String s) {
        this.info = s;
    }
}
