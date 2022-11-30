package com.albba.albbaDaeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DaetaViewDto {
    private Long date;
    private Long storeId;

    private Long approved;
}
