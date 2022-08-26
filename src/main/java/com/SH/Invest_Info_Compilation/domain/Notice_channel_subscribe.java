package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notice_channel_subscribe {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notice_channel_id")
    private Notice_channel notice_channel;

    private Boolean subscribe;

    @Embedded
    private Time time;

    public Notice_channel_subscribe(Member member, Notice_channel notice_channel, Boolean subscribe, Time time) {
        this.member = member;
        this.notice_channel = notice_channel;
        this.subscribe = subscribe;
        this.time = time;
    }
}
