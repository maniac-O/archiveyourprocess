package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Notice_channel{

    @Id @GeneratedValue
    @Column(name = "notice_channel_id")
    private Long id;

    private String channelName;
    private String channelUrl;
    private String description;
    private String thumbnail;

    private Boolean subscribe;

    @Enumerated(EnumType.STRING)
    private NoticeType noticeType;

    @Embedded
    private Time time;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Notice_channel(String channelName, String channelUrl, String description, String thumbnail, NoticeType noticeType, Time time, Member member) {
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.description = description;
        this.thumbnail = thumbnail;
        this.noticeType = noticeType;
        this.time = time;
        this.member = member;
    }

    @Override
    public String toString() {
        return "Notice_channel{" +
                "id=" + id +
                ", channelName='" + channelName + '\'' +
                ", channelUrl='" + channelUrl + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", subscribe=" + subscribe +
                ", noticeType=" + noticeType +
                ", time=" + time +
                ", member=" + member +
                '}';
    }
}
