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
    @Column(name = "user_id")
    private Long userId;

    @Column
    String name;

    @Column
    String start;

    @Column
    String end;
}
