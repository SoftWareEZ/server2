package com.albba.albbaDaeta.table;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DaetaRequest {

    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    @Column(name = "date")
    private Long date;

    @Column(name = "storeId")
    private Long storeId;
    @Column(name ="requestId")
    private Long requestId;


    public DaetaRequest(Long date, Long storeId,Long requestId)
    {
        this.date = date;
        this.storeId=storeId;
        this.requestId=requestId;
    }

}
