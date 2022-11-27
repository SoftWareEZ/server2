package com.albba.commute.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ListyearDto {
    private int month;

    public ListyearDto(int s) {
        this.month = s;
    }
    @Override
    public int hashCode() {
        return Objects.hash(month);
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ListyearDto other = (ListyearDto) obj;
        return month == other.month;
    }
}
