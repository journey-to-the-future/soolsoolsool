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

    private String pwd;

    private String name;
    private String phone_num;
    private String email;
    private String m_type;           // normal, sales
    private int use_yn;              // 회원 탈퇴여부 (탈퇴 : 0, 가입 : 1)
    private LocalDateTime reg_date;  // 회원 가입일 (default sysdate)

    @Embedded
    private Address address;

    private List<Order> orders = new ArrayList<>();
}
