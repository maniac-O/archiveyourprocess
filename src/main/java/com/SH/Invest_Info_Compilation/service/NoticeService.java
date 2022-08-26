package com.SH.Invest_Info_Compilation.service;

import com.SH.Invest_Info_Compilation.domain.*;
import com.SH.Invest_Info_Compilation.repository.MemberRepository;
import com.SH.Invest_Info_Compilation.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeService {

    private final CrawlingThumbnailAndProfile crawling;
    private final NoticeRepository noticeRepository;
    private final ProcessingYoutubeUrl processingYoutubeUrl;

    public Boolean modifyChannel(Long index, Member member, String description, String noticeType){

        try{
            if (!getChannel(index).getMember().getId().equals(member.getId()))
                return false;

            noticeRepository.modifyChannel(index, description, NoticeType.valueOf(noticeType), LocalDateTime.now());

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean modifyNotice(Long index, Member member, String description, String noticeType){

        try{
            if (!getNotice(index).getMember().getId().equals(member.getId()))
                return false;

            noticeRepository.modifyNotice(index, description, NoticeType.valueOf(noticeType), LocalDateTime.now());

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteChannel(Long index, Member member){
        try{
            if(!getChannel(index).getMember().getId().equals(member.getId()))
                return false;
            noticeRepository.deleteChannel(index);

            return true;
        }catch (Exception e){
            return false;
        }

    }public Boolean deleteNotice(Long index, Member member){
        try{
            if (!getNotice(index).getMember().getId().equals(member.getId()))
                return false;
            noticeRepository.deleteNotice(index);

            return true;
        }catch (Exception e){
            return false;
        }

    }


    public Boolean setChannelSubscribe(Long index, Boolean stat, Member member) {
        Notice_channel findChannel = getChannel(index);
        Notice_channel_subscribe findChannelSubscribe = getChannelSubscribe(index, member);

        if (findChannelSubscribe == null){
            findChannelSubscribe = new Notice_channel_subscribe(member, findChannel, stat, new Time(LocalDateTime.now(), LocalDateTime.now()));

        }else{
            findChannelSubscribe.setSubscribe(stat);

            // setTime (setLastTime)
            Time time = findChannelSubscribe.getTime();
            time.setLastTime(LocalDateTime.now());
            findChannel.setTime(time);
        }

        try{
            noticeRepository.setChannelSubscribe(findChannelSubscribe);
            return true;
        }catch (Exception e){
            log.warn("Error during setChannelSubscribe : "+e);
            return false;
        }

    }

    public Boolean setSubscribe(Long index, Boolean stat, Member member) {
        Notice notice = getNotice(index);
        Notice_subscribe notice_subscribe = getSubscribe(index, member);

        if (notice_subscribe == null){
            notice_subscribe = new Notice_subscribe(member, notice, stat, new Time(LocalDateTime.now(), LocalDateTime.now()));
        }else{
            notice_subscribe.setSubscribe(stat);

            // setTime (setLastTime)
            Time time = notice_subscribe.getTime();
            time.setLastTime(LocalDateTime.now());
            notice.setTime(time);
        }

        try{
            noticeRepository.setSubscribe(notice_subscribe);
            return true;
        }catch (Exception e){
            log.warn("Error during setSubscribe (setNoticeSubscribe) : "+e);
            return false;
        }
    }

    public String setNotice(Notice notice, Notice_channel notice_channel, Member member) {

        if (notice.getNoticeType() == null)
            return null;

        if (notice.getYoutubeUrl() != null && notice_channel.getChannelUrl() == null) {
            List<String> videoToData;
            if(notice.getYoutubeUrl().contains("youtube.com/shorts/")){
                videoToData = crawling.shortsToData(notice.getYoutubeUrl());
                notice.setYoutubeUrl(processingYoutubeUrl.shortsToDB(notice.getYoutubeUrl()));
            }
            else{
                videoToData = crawling.videoToData(notice.getYoutubeUrl());
                notice.setYoutubeUrl(processingYoutubeUrl.toDB(notice.getYoutubeUrl()));
            }


            notice.setTime(new Time(LocalDateTime.now(), LocalDateTime.now()));
            notice.setMember(member);

            notice.setThumbnail(videoToData.get(0));
            notice.setChannelName(videoToData.get(1));

            if(noticeRepository.setNotice(notice))
                return "notice";
        }
        if (notice.getYoutubeUrl() == null && notice_channel.getChannelUrl() != null) {
            if(!notice_channel.getChannelUrl().contains("https://www.youtube.com/c/") && !notice_channel.getChannelUrl().contains("https://www.youtube.com/channel/"))
                return null;

            List<String> channelToData = crawling.channelToData(notice_channel.getChannelUrl());

            notice_channel.setTime(new Time(LocalDateTime.now(), LocalDateTime.now()));
            notice_channel.setMember(member);

            notice_channel.setThumbnail(channelToData.get(0));
            notice_channel.setChannelName(channelToData.get(1));

            if(noticeRepository.setNoticeChannel(notice_channel))
                return "channel";
        }

        return null;
    }

    public List<Notice_channelDto> getChannelDto_Rest(Long index, Member member){
        return noticeRepository.getChannelDto_Rest(index, member);
    }

    public Notice_channel_subscribe getChannelSubscribe(Long index, Member member){
        return noticeRepository.getChannelSubscribe(index, member);
    }

    public List<Notice_channelDto> getSubscribeChannel(Long index, Member member){
        return noticeRepository.getChannelWithSubscribe(index, member);
    }

    public List<Notice_channelDto> getChannelSubscribe(Member member){
        return noticeRepository.getSubscribeChannel(member);
    }

    public List<Notice_channelDto> getChannelDto(Long index, Boolean isIndex, String noticeType){

        return noticeRepository.getChannelDto(index, isIndex, noticeType);
    }

    public Notice_channel getChannel(Long index){
        return noticeRepository.getChannel(index);
    }

    public Notice_subscribe getSubscribe(Long index, Member member){
        return noticeRepository.getSubscribe(index ,member);
    }

    /* start notice Service */
    public List<NoticeDto> getNoticeDto_Rest(Long index, Member member){
        return noticeRepository.getNoticeDto_Rest(index, member);
    }

    public Notice getNotice(Long index){
        return noticeRepository.getNotice(index);
    }

    public List<NoticeDto> getNoticeDto(Long id, Boolean isIndex, String noticeType){
        return noticeRepository.getNoticeDto(id, isIndex, noticeType);
    }

    public List<NoticeDto> getAllNotice(Long index, Member member) {
        return noticeRepository.getNoticeIndex(index, member);
    }

    public List<NoticeDto> getSubscribeNotice(Member member){
        return noticeRepository.getSubscribeNotice(member);
    }

    public ModelAndView addNoticeTypes(ModelAndView previous){
        previous.addObject("noticeType_name", NoticeType.getNames());
        previous.addObject("noticeType_label", NoticeType.getLabels());

        return previous;
    }
}
