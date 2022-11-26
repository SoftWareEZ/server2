package com.albba.commute.model;

import com.albba.commute.dto.EndDto;
import com.albba.commute.dto.MonthDto;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "commute_id")
    private Long no;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Long storeId;

    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy")
    private String year;

    @JsonFormat(pattern = "MM")
    private String month;

    @JsonFormat(pattern = "dd")
    private String day;

    @Column(nullable = false)
    @JsonFormat(pattern = "HH:mm")
    private String start;

    @Column
    @JsonFormat(pattern = "HH:mm")
    private String end;

    @Column
    private double time;

    public Commute(StartDto startdto){
        this.userId = startdto.getUserId();
        this.storeId = startdto.getStoreId();
        this.year = startdto.getYear();
        this.month = startdto.getMonth();
        this.day = startdto.getDay();
        this.start = startdto.getStart();
    }
}