package com.journey.web.domain.member;

import com.journey.web.domain.Address;
import com.journey.web.domain.BaseEntity;
import com.journey.web.domain.order.Order;
import com.journey.web.domain.Review;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String email;           // 회원 email

    @Column(nullable = false, length = 100)
    private String pwd;             // 회원 비밀번호

    @Column(nullable = false, length = 20)
    private String firstname;       // 이름

    @Column(nullable = false, length = 20)
    private String lastname;        // 성

    @Column(unique = true, nullable = false, length = 30)
    private String nickname;        // 별명(화면 내에서 사용)

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", length = 20)
    private MemberType memberType;  // SELLER, USER

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private MemberUsable memberUsable; // 회원 탈퇴여부 (IN, OUT)

    @Enumerated(EnumType.STRING)
    @Column(length = 10)
    private MemberStatus memberStatus;

    @Column(updatable = false)
    private LocalDateTime reg_date;  // 회원 가입일 (default sysdate)

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();
}
