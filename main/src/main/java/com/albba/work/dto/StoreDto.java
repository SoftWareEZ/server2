package com.albba.work.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class StoreDto {
    private Long userId;
    private String storeName;
    private String storeAddr;
}
