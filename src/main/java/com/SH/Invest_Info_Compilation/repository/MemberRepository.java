package com.SH.Invest_Info_Compilation.repository;

import com.SH.Invest_Info_Compilation.domain.Member;
import com.SH.Invest_Info_Compilation.domain.Notice_subscribe;
import com.SH.Invest_Info_Compilation.domain.QMember;
import com.SH.Invest_Info_Compilation.domain.Time;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;

@Repository
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MemberRepository {

    @PersistenceContext
    private final EntityManager em;
    private final JPAQueryFactory query;

    public Member findMember(Member member){
        QMember qMember = QMember.member;

        return query.select(qMember)
                .from(qMember)
                .where(QMember.member.uid.like(member.getUid()))
                .fetchOne();
    }

    public boolean saveMember(Member member){
        try {
            em.persist(member);

            return true;
        } catch (Exception e) {
            log.warn("Error in action saveMember : " + e);
            return false;
        }
    }

    public Member updateMember(Member member){
        Member findMember = findMember(member);

        findMember.setAccessToken(member.getAccessToken());
        findMember.setEmail(member.getEmail());
        findMember.setDisplayName(member.getDisplayName());
        findMember.setTime(member.getTime());

        return findMember;
    }

}
