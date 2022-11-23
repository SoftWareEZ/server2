package com.albba.work.model;

import com.albba.work.dto.ScheduleDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @Column(name = "no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer no;

    @Column
    String name;

    @Column
    String start;

    @Column
    String end;

//    public Schedule(){
//        this.name = null;
//        this.start = null;
//        this.end = null;
//    }
}
