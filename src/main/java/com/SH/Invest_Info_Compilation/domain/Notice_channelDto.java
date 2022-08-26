package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Notice_channelDto {

    private Long id;

    private String channelName;
    private String channelUrl;
    private String description;
    private String thumbnail;
    private Time time;
    private Member member;
    private NoticeType noticeType;

    private Boolean subscribe;

    public Notice_channelDto(Long id, String channelName, String channelUrl, String description, String thumbnail, Time time, Member member, NoticeType noticeType, Boolean subscribe) {
        this.id = id;
        this.channelName = channelName;
        this.channelUrl = channelUrl;
        this.description = description;
        this.thumbnail = thumbnail;
        this.time = time;
        this.member = member;
        this.noticeType = noticeType;
        this.subscribe = subscribe;
    }

    @Override
    public String toString() {
        return "Notice_channelDto{" +
                "id=" + id +
                ", channelName='" + channelName + '\'' +
                ", channelUrl='" + channelUrl + '\'' +
                ", description='" + description + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", time=" + time +
                ", member=" + member +
                ", subscribe=" + subscribe +
                '}';
    }
}
