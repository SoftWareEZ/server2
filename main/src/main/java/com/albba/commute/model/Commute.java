package com.albba.commute.model;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.StartDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Commute {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "commute_id")
    private Long no;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false)
    @JsonFormat(pattern = "YYYY-MM-dd")
    private String date;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private String start;

    @Column
    @JsonFormat(pattern = "HH:mm")
    private String end;

    public Commute(StartDto startdto){
        this.userId = startdto.getUserId();
        this.storeId = startdto.getStoreId();
        this.date = startdto.getDate();
        this.start = startdto.getStart();
    }
    public Commute(EndDto endDto){
        this.userId = endDto.getUserId();
        this.storeId = endDto.getStoreId();
        this.date = endDto.getDate();
        this.end = endDto.getEnd();
    }
}