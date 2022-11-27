package com.albba.albbaDaeta.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DaetaAcceptDto {
    private Long no;
    private Long requestId;
    private Long storeId;
    private Long acceptId;
}
