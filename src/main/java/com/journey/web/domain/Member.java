package com.journey.web.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;           // 회원 id
    private String pwd;

    private String firstname;       // 이름
    private String lastname;        // 성
    private String nickname;        // 별명

//    private String phone_num;        // 보류
    private String m_type;           // normal, sales
    private int use_yn;              // 회원 탈퇴여부 (탈퇴 : 0, 가입 : 1)
    private LocalDateTime reg_date;  // 회원 가입일 (default sysdate)

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
