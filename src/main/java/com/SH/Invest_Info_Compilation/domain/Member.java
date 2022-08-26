package com.SH.Invest_Info_Compilation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String uid;
    private String accessToken;
    private String email;
    private String displayName;

    @Embedded
    private Time time;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany
    private List<Notice> notices = new ArrayList<>();
    @OneToMany
    private List<Notice_channel> channels = new ArrayList<>();

    public Member(String uid, String accessToken, String email, String displayName, Time time, Role role) {
        this.uid = uid;
        this.accessToken = accessToken;
        this.email = email;
        this.displayName = displayName;
        this.time = time;
        this.role = role;
    }
}
