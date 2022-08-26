package com.SH.Invest_Info_Compilation.repository;

import com.SH.Invest_Info_Compilation.domain.*;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.Projections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class NoticeRepository {

    private final PAGE_SIZE PAGE_SIZE;

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public Boolean modifyChannel(Long index, String description, NoticeType noticeType, LocalDateTime lastTime){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;

        try{
            queryFactory.update(qNotice_channel)
                    .set(qNotice_channel.description, description)
                    .set(qNotice_channel.noticeType, noticeType)
                    .set(qNotice_channel.time.lastTime, lastTime)
                    .where(qNotice_channel.id.eq(index))
                    .execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean modifyNotice(Long index, String description, NoticeType noticeType, LocalDateTime lastTime){
        QNotice qNotice = QNotice.notice;

        try{
            queryFactory.update(qNotice)
                    .set(qNotice.description, description)
                    .set(qNotice.noticeType, noticeType)
                    .set(qNotice.time.lastTime, lastTime)
                    .where(qNotice.id.eq(index))
                    .execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteChannel(Long index){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;
        QNotice_channel_subscribe qNotice_channel_subscribe = QNotice_channel_subscribe.notice_channel_subscribe;

        try{
            queryFactory.delete(qNotice_channel_subscribe)
                            .where(qNotice_channel_subscribe.notice_channel.id.eq(index))
                            .execute();

            queryFactory.delete(qNotice_channel)
                    .where(qNotice_channel.id.eq(index))
                    .execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean deleteNotice(Long index){
        QNotice qNotice = QNotice.notice;
        QNotice_subscribe qNotice_subscribe = QNotice_subscribe.notice_subscribe;

        try{
            queryFactory.delete(qNotice_subscribe)
                            .where(qNotice_subscribe.notice.id.eq(index))
                            .execute();

            queryFactory.delete(qNotice)
                    .where(qNotice.id.eq(index))
                    .execute();

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean setChannelSubscribe(Notice_channel_subscribe notice_channel_subscribe) {
        try {
            em.persist(notice_channel_subscribe);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean setSubscribe(Notice_subscribe notice_subscribe){
        try {
            em.persist(notice_subscribe);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean setNotice(Notice notice){
        try {
            em.persist(notice);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean setNoticeChannel(Notice_channel notice_channel) {
        try {
            em.persist(notice_channel);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Notice_channelDto> getChannelDto_Rest(Long index, Member member){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;
        QNotice_channel_subscribe qNotice_channel_subscribe = QNotice_channel_subscribe.notice_channel_subscribe;
        Long id = 0L;
        if (member != null)
            id = member.getId();

        return queryFactory
                .select(Projections.bean(Notice_channelDto.class,
                        qNotice_channel.id, qNotice_channel.description, qNotice_channel.channelUrl,
                        qNotice_channel.channelName, qNotice_channel.thumbnail, qNotice_channel.time,
                        qNotice_channel.noticeType, qNotice_channel.member, qNotice_channel_subscribe.subscribe))
                .from(qNotice_channel)
                .leftJoin(qNotice_channel_subscribe)
                .on(qNotice_channel.id.in(
                                JPAExpressions.select(qNotice_channel_subscribe.notice_channel.id)
                                        .from(qNotice_channel_subscribe)
                                        .where(qNotice_channel_subscribe.member.id.eq(id)))
                        .and(qNotice_channel.id.eq(qNotice_channel_subscribe.notice_channel.id))
                )
                .where(qNotice_channel.id.lt(index))
                .orderBy(qNotice_channel.id.desc())
                .limit(PAGE_SIZE.getPAGE_SIZE())
                .fetch();
    }

    // channelDto를 조회
    public List<Notice_channelDto> getChannelWithSubscribe(Long index, Member member){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;
        QNotice_channel_subscribe qNotice_channel_subscribe = QNotice_channel_subscribe.notice_channel_subscribe;
        Long id = 0L;
        if (member != null)
            id = member.getId();

        return queryFactory
                .select(Projections.bean(Notice_channelDto.class,
                        qNotice_channel.id, qNotice_channel.description, qNotice_channel.channelUrl,
                        qNotice_channel.channelName, qNotice_channel.thumbnail, qNotice_channel.time,
                        qNotice_channel.noticeType, qNotice_channel.member, qNotice_channel_subscribe.subscribe))
                .from(qNotice_channel)
                .leftJoin(qNotice_channel_subscribe)
                .on(qNotice_channel.id.in(
                                JPAExpressions.select(qNotice_channel_subscribe.notice_channel.id)
                                        .from(qNotice_channel_subscribe)
                                        .where(qNotice_channel_subscribe.member.id.eq(id)))
                        .and(qNotice_channel.id.eq(qNotice_channel_subscribe.notice_channel.id))
                )
                .where(qNotice_channel.id.goe(index))
                .orderBy(qNotice_channel.id.desc())
                .limit(PAGE_SIZE.getPAGE_SIZE())
                .fetch();
    }

    // 1개 조회
    public Notice_channel_subscribe getChannelSubscribe(Long index, Member member){
        QNotice_channel_subscribe qNotice_channel_subscribe = QNotice_channel_subscribe.notice_channel_subscribe;

        return queryFactory.selectFrom(qNotice_channel_subscribe)
                .where(
                        qNotice_channel_subscribe.notice_channel.id.eq(index)
                                .and(qNotice_channel_subscribe.member.id.eq(member.getId()))
                ).fetchOne();
    }

    // 개인의 채널 구독 조회
    public List<Notice_channelDto> getSubscribeChannel(Member member){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;
        QNotice_channel_subscribe qNotice_channel_subscribe = QNotice_channel_subscribe.notice_channel_subscribe;

        return queryFactory.select(Projections.bean(Notice_channelDto.class,
                        qNotice_channel.id, qNotice_channel.channelName, qNotice_channel.channelUrl,
                        qNotice_channel.description, qNotice_channel.thumbnail, qNotice_channel.time,
                        qNotice_channel.member, qNotice_channel.noticeType, qNotice_channel_subscribe.subscribe))
                .from(qNotice_channel)
                .join(qNotice_channel_subscribe)
                .on(qNotice_channel.id.eq(qNotice_channel_subscribe.notice_channel.id))
                .where(
                        qNotice_channel_subscribe.member.id.eq(member.getId())
                                .and(qNotice_channel_subscribe.subscribe.eq(true))
                )
                .orderBy(qNotice_channel.id.desc())
                .fetch();
    }

    public List<Notice_channelDto> getChannelDto(Long index, Boolean isIndex, String noticeType){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;
        QNotice_channel_subscribe qNotice_channel_subscribe = QNotice_channel_subscribe.notice_channel_subscribe;

        JPAQuery<Notice_channelDto> query = queryFactory.select(Projections.bean(Notice_channelDto.class,
                        qNotice_channel.id, qNotice_channel.channelName, qNotice_channel.channelUrl,
                        qNotice_channel.description, qNotice_channel.thumbnail, qNotice_channel.time,
                        qNotice_channel.member, qNotice_channel.noticeType, qNotice_channel_subscribe))
                .from(qNotice_channel)
                .leftJoin(qNotice_channel_subscribe)
                .on(qNotice_channel.id.in(
                                JPAExpressions.select(qNotice_channel_subscribe.notice_channel.id)
                                        .from(qNotice_channel_subscribe)
                                        .where(qNotice_channel_subscribe.member.id.eq(index)))
                        .and(qNotice_channel.id.eq(qNotice_channel_subscribe.notice_channel.id))
                ).orderBy(qNotice_channel.id.desc());
        /*
        if (!isAll)
            query.where(qNotice_channel.member.id.eq(index));

        return query.orderBy(qNotice_channel.id.desc())
                .limit(PAGE_SIZE.getPAGE_SIZE()).fetch();
         */

        if (noticeType != null && isIndex){
            query.where(qNotice_channel.noticeType.eq(NoticeType.valueOf(noticeType)));
        }else if (isIndex){
            query.where(qNotice_channel.member.id.eq(index));
        }else {
            query.limit(PAGE_SIZE.getPAGE_SIZE());
        }



        return query.fetch();
    }

    public Notice_channel getChannel(Long index){
        QNotice_channel qNotice_channel = QNotice_channel.notice_channel;

        return queryFactory.selectFrom(qNotice_channel)
                .where(qNotice_channel.id.eq(index))
                .fetchOne();
    }

    public List<NoticeDto> getSubscribeNotice(Member member){
        QNotice qNotice = QNotice.notice;
        QNotice_subscribe qNotice_subscribe = QNotice_subscribe.notice_subscribe;

        return queryFactory.select(Projections.bean(NoticeDto.class,
                        qNotice.id, qNotice.description, qNotice.youtubeUrl,
                        qNotice.channelName, qNotice.thumbnail, qNotice.time,
                        qNotice.noticeType, qNotice.member, qNotice_subscribe.subscribe))
                .from(qNotice)
                .join(qNotice_subscribe)
                .on(qNotice.id.eq(qNotice_subscribe.notice.id))
                .where(
                        qNotice_subscribe.member.id.eq(member.getId())
                                .and(qNotice_subscribe.subscribe.eq(true))
                )
                .orderBy(qNotice.id.desc())
                .fetch();

    }
    public List<NoticeDto> getNoticeDto(Long index, Boolean isIndex, String noticeType){
        QNotice qNotice = QNotice.notice;
        QNotice_subscribe qNotice_subscribe = QNotice_subscribe.notice_subscribe;
        List<NoticeDto> result;
        /*
        select * from notice AS n
            left join notice_subscribe AS s
            on n.notice_id =s.notice_id
         */
        /*
        select * from notice AS n
            left join notice_subscribe AS s
            on n.notice_id in ( select ss.notice_id from notice_subscribe AS ss
                                         where ss.member_id = 1) and (n.notice_id = s.notice_id)
         */
        // member id가 필요함
        JPAQuery<NoticeDto> query = queryFactory
                .select(Projections.bean(NoticeDto.class,
                        qNotice.id, qNotice.description, qNotice.youtubeUrl,
                        qNotice.channelName, qNotice.thumbnail, qNotice.time,
                        qNotice.noticeType, qNotice.member, qNotice_subscribe.subscribe))
                .from(qNotice)
                .leftJoin(qNotice_subscribe)
                .on(qNotice.id.in(
                                JPAExpressions.select(qNotice_subscribe.notice.id)
                                        .from(qNotice_subscribe)
                                        .where(qNotice_subscribe.member.id.eq(index)))
                        .and(qNotice.id.eq(qNotice_subscribe.notice.id))
                ).orderBy(qNotice.id.desc());

        if (noticeType != null && isIndex){
            query.where(qNotice.noticeType.eq(NoticeType.valueOf(noticeType)));
        }else if (isIndex){
             query.where(qNotice.member.id.eq(index));
        }else {
            query.limit(PAGE_SIZE.getPAGE_SIZE());
        }


        return query.fetch();
    }

    public Notice_subscribe getSubscribe(Long index, Member member){
        QNotice_subscribe qNotice_subscribe = QNotice_subscribe.notice_subscribe;
        QNotice qNotice = QNotice.notice;

        return queryFactory.selectFrom(qNotice_subscribe)
                .where(
                        qNotice.id.eq(index)
                                .and(qNotice_subscribe.member.id.eq(member.getId()))
                ).fetchOne();
    }

    public Notice getNotice(Long index){
        QNotice qNotice = QNotice.notice;

        return queryFactory.selectFrom(qNotice)
                .where(qNotice.id.eq(index)).fetchOne();
    }

    public List<NoticeDto> getNoticeDto_Rest(Long index, Member member){
        QNotice qNotice = QNotice.notice;
        QNotice_subscribe qNotice_subscribe = QNotice_subscribe.notice_subscribe;
        Long id = 0L;

        if(member != null)
            id = member.getId();

        List<NoticeDto> notices = queryFactory.select(Projections.bean(NoticeDto.class,
                        qNotice.id, qNotice.description, qNotice.youtubeUrl,
                        qNotice.channelName, qNotice.thumbnail, qNotice.time,
                        qNotice.noticeType, qNotice.member, qNotice_subscribe.subscribe))
                .from(qNotice)
                .leftJoin(qNotice_subscribe)
                .on(qNotice.id.in(
                                JPAExpressions.select(qNotice_subscribe.notice.id)
                                        .from(qNotice_subscribe)
                                        .where(qNotice_subscribe.member.id.eq(id)))
                        .and(qNotice.id.eq(qNotice_subscribe.notice.id))
                )
                .where(qNotice.id.lt(index))
                .orderBy(qNotice.id.desc())
                .limit(PAGE_SIZE.getPAGE_SIZE()).fetch();

        if(notices.size() <= 0)
            return null;


        return notices;
    }

    public List<NoticeDto> getNoticeIndex(Long index, Member member){
        QNotice qNotice = QNotice.notice;
        QNotice_subscribe qNotice_subscribe = QNotice_subscribe.notice_subscribe;
        Long id = 0L;

        if(member != null)
            id = member.getId();

        List<NoticeDto> notices = queryFactory.select(Projections.bean(NoticeDto.class,
                        qNotice.id, qNotice.description, qNotice.youtubeUrl,
                        qNotice.channelName, qNotice.thumbnail, qNotice.time,
                        qNotice.noticeType, qNotice.member, qNotice_subscribe.subscribe))
                .from(qNotice)
                .leftJoin(qNotice_subscribe)
                .on(qNotice.id.in(
                                JPAExpressions.select(qNotice_subscribe.notice.id)
                                        .from(qNotice_subscribe)
                                        .where(qNotice_subscribe.member.id.eq(id)))
                        .and(qNotice.id.eq(qNotice_subscribe.notice.id))
                )
                .where(qNotice.id.goe(index))
                .orderBy(qNotice.id.desc())
                .limit(PAGE_SIZE.getPAGE_SIZE()).fetch();

        if(notices.size() <= 0)
            return null;


        return notices;
    }

}
