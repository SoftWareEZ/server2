package com.albba.work.model;

import com.albba.work.dto.StoreDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Store {

    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;

    @Column(name="user_id")
    private Long userId;

    @Column(name="code")
    private String code;

    @Column(name = "store_name", length = 500)
    private String storeName;

    @Column(name = "store_addr", length = 500)
    private String storeAddr;

    public Store(StoreDto storeDto){
        this.userId =  storeDto.getUserId();
        this.storeName = storeDto.getStoreName();
        this.storeAddr = storeDto.getStoreAddr();
    }
}
