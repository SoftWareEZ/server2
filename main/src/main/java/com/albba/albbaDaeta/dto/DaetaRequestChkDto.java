package com.albba.albbaDaeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DaetaRequestChkDto {
    private Long userId;
    private Long storeId;
    private Long date;
}