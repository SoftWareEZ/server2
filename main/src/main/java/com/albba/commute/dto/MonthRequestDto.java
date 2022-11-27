package com.albba.commute.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MonthRequestDto {
    private Long userId;
    private String userName;
    private double time;

    public MonthRequestDto(String userName,Long userId, double time)
    {
        this.userId = userId;
        this.time = time;
        this.userName = userName;



    }


}
