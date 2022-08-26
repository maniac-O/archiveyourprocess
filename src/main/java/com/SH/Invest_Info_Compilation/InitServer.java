package com.SH.Invest_Info_Compilation;

import com.SH.Invest_Info_Compilation.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class InitServer {


    private final InitDb initDb;

    @PostConstruct
    public void init(){

        //initDb.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitDb {

        private final EntityManager em;


        public void init(){

            Member member = new Member("11SDDZI6z5daJC8nFLvuc8a8ZLk2", "true", "cj456456@gmail.com","귀여워",
                    new Time(LocalDateTime.now(), LocalDateTime.now()),Role.NORMAL);

            Notice notice1 = new Notice("Norazo - Mackerel, Show! MusicCore(쇼! 음악중심), EP173, 2009/07/11, MBC TV, South Korea",
                    "https://www.youtube.com/embed/SwFF_HSfmXE", "MBCkpop", "https://yt3.ggpht.com/ytc/AKedOLTYADrZ9TxOPX0zf76UkdIjvZ_XgFygOK7B4Yzm_g=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.INVEST_STOCK, member);
            Notice notice2 = new Notice("Norazo - Mackerel, Show! MusicCore(쇼! 음악중심), EP173, 2009/07/11, MBC TV, South Korea",
                    "https://www.youtube.com/embed/SwFF_HSfmXE", "MBCkpop", "https://yt3.ggpht.com/ytc/AKedOLTYADrZ9TxOPX0zf76UkdIjvZ_XgFygOK7B4Yzm_g=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.INVEST_COIN, member);
            Notice notice3 = new Notice("Norazo - Mackerel, Show! MusicCore(쇼! 음악중심), EP173, 2009/07/11, MBC TV, South Korea",
                    "https://www.youtube.com/embed/SwFF_HSfmXE", "MBCkpop", "https://yt3.ggpht.com/ytc/AKedOLTYADrZ9TxOPX0zf76UkdIjvZ_XgFygOK7B4Yzm_g=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.INVEST_FUTURES, member);
            Notice notice4 = new Notice("Norazo - Mackerel, Show! MusicCore(쇼! 음악중심), EP173, 2009/07/11, MBC TV, South Korea",
                    "https://www.youtube.com/embed/SwFF_HSfmXE", "MBCkpop", "https://yt3.ggpht.com/ytc/AKedOLTYADrZ9TxOPX0zf76UkdIjvZ_XgFygOK7B4Yzm_g=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.ASSET_RISK, member);
            Notice notice5 = new Notice("Norazo - Mackerel, Show! MusicCore(쇼! 음악중심), EP173, 2009/07/11, MBC TV, South Korea Norazo - Mackerel, Show! MusicCore(쇼! 음악중심), EP173, 2009/07/11, MBC TV, South Korea",
                    "https://www.youtube.com/embed/SwFF_HSfmXE", "MBCkpop", "https://yt3.ggpht.com/ytc/AKedOLTYADrZ9TxOPX0zf76UkdIjvZ_XgFygOK7B4Yzm_g=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.ASSET_RATIO, member);

            Notice_channel notice_channel = new Notice_channel("MBCkpop", "https://www.youtube.com/user/MBCkpop", "노라조 MBCkpop 채널",
                    "https://yt3.ggpht.com/ytc/AMLnZu-aHTHvY_WU4Cj6URDgF6ufqmlDHQHTFtgNJTqADA=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.ASSET_RATIO ,new Time(LocalDateTime.now(), LocalDateTime.now()), member);

            em.persist(member);
            em.persist(notice1);
            em.persist(notice2);
            em.persist(notice3);
            em.persist(notice4);
            em.persist(notice5);
            em.persist(notice_channel);

            Member member2 = new Member("11SDDZI6z5daJC8nFLvuc8a8ZLk3", "true", "cj4564562@gmail.com","귀여워2",
                    new Time(LocalDateTime.now(), LocalDateTime.now()),Role.NORMAL);

            Notice notice6 = new Notice("해외 메이플은 뭐가 다를까? - S_Hee석희 [직업, 맵, 아이템]",
                    "https://www.youtube.com/embed/-y_q4TZMUlE", "돌희", "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.STUDY_THEORY, member2);

            Notice notice7 = new Notice("해외 메이플은 뭐가 다를까? - S_Hee석희 [직업, 맵, 아이템]",
                    "https://www.youtube.com/embed/-y_q4TZMUlE", "돌희", "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.STUDY_ACTION, member2);

            Notice notice8 = new Notice("해외 메이플은 뭐가 다를까? - S_Hee석희 [직업, 맵, 아이템]",
                    "https://www.youtube.com/embed/-y_q4TZMUlE", "돌희", "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.INVEST_STOCK, member2);

            Notice notice9 = new Notice("해외 메이플은 뭐가 다를까? - S_Hee석희 [직업, 맵, 아이템]",
                    "https://www.youtube.com/embed/-y_q4TZMUlE", "돌희", "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.INVEST_COIN, member2);
            Notice notice10 = new Notice("해외 메이플은 뭐가 다를까? - S_Hee석희 [직업, 맵, 아이템]",
                    "https://www.youtube.com/embed/-y_q4TZMUlE", "돌희", "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s48-c-k-c0x00ffffff-no-rj",
                    new Time(LocalDateTime.now(), LocalDateTime.now()), NoticeType.INVEST_FUTURES, member2);

            Notice_channel notice_channel2 = new Notice_channel("돌희", "https://www.youtube.com/c/%EB%8F%8C%ED%9D%AC", "돌희의 메이플스토리",
                    "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.INVEST_FUTURES, new Time(LocalDateTime.now(), LocalDateTime.now()), member2);
            Notice_channel notice_channel3 = new Notice_channel("돌희", "https://www.youtube.com/c/%EB%8F%8C%ED%9D%AC", "돌희의 메이플스토리",
                    "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.STUDY_THEORY, new Time(LocalDateTime.now(), LocalDateTime.now()), member2);
            Notice_channel notice_channel4 = new Notice_channel("돌희", "https://www.youtube.com/c/%EB%8F%8C%ED%9D%AC", "돌희의 메이플스토리",
                    "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.STUDY_ACTION, new Time(LocalDateTime.now(), LocalDateTime.now()), member2);
            Notice_channel notice_channel5 = new Notice_channel("돌희", "https://www.youtube.com/c/%EB%8F%8C%ED%9D%AC", "돌희의 메이플스토리",
                    "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.ASSET_RISK, new Time(LocalDateTime.now(), LocalDateTime.now()), member2);
            Notice_channel notice_channel6 = new Notice_channel("돌희", "https://www.youtube.com/c/%EB%8F%8C%ED%9D%AC", "돌희의 메이플스토리",
                    "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.ASSET_RISK, new Time(LocalDateTime.now(), LocalDateTime.now()), member2);
            Notice_channel notice_channel7 = new Notice_channel("돌희", "https://www.youtube.com/c/%EB%8F%8C%ED%9D%AC", "돌희의 메이플스토리",
                    "https://yt3.ggpht.com/lDmxa721madXMRmn4vvYENGqjJEJEskDu9IwNqLUsnUoHN7-pKVhCcMf_q-1o-CAwpCB9bCNnRs=s88-c-k-c0x00ffffff-no-rj",
                    NoticeType.ASSET_RATIO, new Time(LocalDateTime.now(), LocalDateTime.now()), member2);

            Notice_subscribe notice_subscribe = new Notice_subscribe(member, notice1, true, new Time(LocalDateTime.now(), LocalDateTime.now()));
            Notice_subscribe notice_subscribe1 = new Notice_subscribe(member, notice3, true, new Time(LocalDateTime.now(), LocalDateTime.now()));
            Notice_subscribe notice_subscribe2 = new Notice_subscribe(member, notice5, true, new Time(LocalDateTime.now(), LocalDateTime.now()));

            Notice_subscribe notice_subscribe3 = new Notice_subscribe(member2, notice6, true, new Time(LocalDateTime.now(), LocalDateTime.now()));
            Notice_subscribe notice_subscribe4 = new Notice_subscribe(member2, notice7, true, new Time(LocalDateTime.now(), LocalDateTime.now()));
            Notice_subscribe notice_subscribe5 = new Notice_subscribe(member2, notice10, true, new Time(LocalDateTime.now(), LocalDateTime.now()));

            new Notice_channel_subscribe(member, notice_channel, true, new Time(LocalDateTime.now(), LocalDateTime.now()));

            em.persist(member2);
            em.persist(notice6);
            em.persist(notice7);
            em.persist(notice8);
            em.persist(notice9);
            em.persist(notice10);
            em.persist(notice_channel2);
            em.persist(notice_channel3);
            em.persist(notice_channel4);
            em.persist(notice_channel5);
            em.persist(notice_channel6);
            em.persist(notice_channel7);

            em.persist(notice_subscribe);
            em.persist(notice_subscribe1);
            em.persist(notice_subscribe2);
            em.persist(notice_subscribe3);
            em.persist(notice_subscribe4);
            em.persist(notice_subscribe5);

            em.flush();
            em.clear();
        }
    }
}
