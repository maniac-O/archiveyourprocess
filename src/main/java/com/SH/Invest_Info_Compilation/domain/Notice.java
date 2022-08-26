package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Notice {

    @Id @GeneratedValue
    @Column(name = "notice_id")
    private Long id;

    private String description;

    private String youtubeUrl;
    private String channelName;
    private String thumbnail;

    @Embedded
    private Time time;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    private Boolean subscribe;

    public Notice(String description, String youtubeUrl, String channelName, String thumbnail, Time time, NoticeType noticeType, Member member) {
        this.description = description;
        this.youtubeUrl = youtubeUrl;
        this.channelName = channelName;
        this.thumbnail = thumbnail;
        this.time = time;
        this.noticeType = noticeType;
        this.member = member;
    }

}
