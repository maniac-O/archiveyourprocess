package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class NoticeDto{

    private Long id;

    private String description;

    private String youtubeUrl;
    private String channelName;
    private String thumbnail;
    private Time time;
    private NoticeType noticeType;
    private Member member;
    private Boolean subscribe;

    public NoticeDto(Long id, String description, String youtubeUrl, String channelName, String thumbnail, Time time, NoticeType noticeType, Member member, Boolean subscribe) {
        this.id = id;
        this.description = description;
        this.youtubeUrl = youtubeUrl;
        this.channelName = channelName;
        this.thumbnail = thumbnail;
        this.time = time;
        this.noticeType = noticeType;
        this.member = member;
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "NoticeDto{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", youtubeUrl='" + youtubeUrl + '\'' +
                ", channelName='" + channelName + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", time=" + time +
                ", noticeType=" + noticeType +
                ", member=" + member +
                ", subscribe=" + subscribe +
                '}';
    }
}
