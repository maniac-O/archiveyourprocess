package com.SH.Invest_Info_Compilation.service;

import com.SH.Invest_Info_Compilation.config.FirebaseInitializer;
import com.SH.Invest_Info_Compilation.domain.Member;
import com.SH.Invest_Info_Compilation.domain.Role;
import com.SH.Invest_Info_Compilation.domain.Time;
import com.SH.Invest_Info_Compilation.repository.MemberRepository;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MemberService {

    private final FirebaseAuth FIREAUTH;
    private final Firestore FIRESTORE;
    private final MemberRepository memberRepository;

    public MemberService(FirebaseInitializer firebase, MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

        FirebaseInitializer firebaseInitializer = firebase;

        FIREAUTH = firebaseInitializer.getFirebaseAuth();
        FIRESTORE = firebaseInitializer.getFirestore();
    }

    public Member validMember(Member member){
        UserRecord firebaseUser = getFirebaseUser(member);
        String accessToken = "";

        if(firebaseUser.getDisplayName().equals(member.getDisplayName()) &&
                firebaseUser.getUid().equals(member.getUid()) &&
                firebaseUser.getEmail().equals(member.getEmail())){

            Member findMember = findMember(member);
            member.setRole(Role.NORMAL);

            if(findMember == null){
                accessToken = member.getAccessToken();
                member.setAccessToken("true");
                member.setTime(new Time(LocalDateTime.now(), LocalDateTime.now()));

                // 회원가입 성공
                memberRepository.saveMember(member);
                member.setAccessToken(accessToken);
            }else{
                member = updateMemberTime(member);
                // 로그인 성공
                return memberRepository.updateMember(member);
            }
        }

        return member;
    }

    public Member updateMemberTime(Member member){
        Member findMember = memberRepository.findMember(member);

        Time time = findMember.getTime();
        time.setLastTime(LocalDateTime.now());

        findMember.setTime(time);
        return findMember;
    }

    public UserRecord getFirebaseUser(Member member){
        try {
            return FIREAUTH.getUser(member.getUid());
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Member findMember(Member member){

        return memberRepository.findMember(member);
    }
}
