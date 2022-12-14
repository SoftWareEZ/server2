package com.albba.albbaDaeta.table;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Daeta {
    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "date")
    private Long date;
    @Column(name="storeId")
    private Long storeId;

    @Column(name ="requestId")
    private Long requestId;

    @Column(name="acceptName")
    private String acceptName;

    @Column(name ="approved")
    private Long approved;

    @Column(name = "requestName")
    private String requestName;

    public Daeta(DaetaRequest requestDto, String realname)

    {
        this.date = requestDto.getDate();
        this.requestId=requestDto.getRequestId();
        this.requestName=requestDto.getRequestName();
        this.storeId = requestDto.getStoreId();
        this.acceptName = realname;
        this.approved = (long)0;

    }
}
