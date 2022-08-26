package com.SH.Invest_Info_Compilation.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
public class Time {

    protected Time() {
    }

    private LocalDateTime firstTime;
    private LocalDateTime lastTime;

    public Time(LocalDateTime firstTime, LocalDateTime lastTime) {
        this.firstTime = firstTime;
        this.lastTime = lastTime;
    }
}
