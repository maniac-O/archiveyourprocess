package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notice_subscribe {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_id")
    private Notice notice;

    private Boolean subscribe;

    @Embedded
    private Time time;

    public Notice_subscribe(Member member, Notice notice, Boolean subscribe, Time time) {
        this.member = member;
        this.notice = notice;
        this.subscribe = subscribe;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Notice_subscribe{" +
                "id=" + id +
                ", member=" + member +
                ", notice=" + notice +
                ", subscribe=" + subscribe +
                ", time=" + time +
                '}';
    }
}
