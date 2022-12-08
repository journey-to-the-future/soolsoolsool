package com.journey.web.service;

import com.journey.web.domain.member.Member;
import com.journey.web.repository.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 중복_닉네임_체크() {
        boolean check = memberService.existsByNickName("kim");
        Assertions.assertThat(check).isEqualTo(true);
    }

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setMemberEmail("kim@naver.com");
        member.setPwd("1234asfd!");
        member.setFirstname("kim");
        member.setLastname("dol");
        member.setNickname("ironman");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findById(savedId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_체크() throws Exception {
        //given
        Member member1 = new Member();
        member1.setNickname("kim");

        Member member2 = new Member();
        member2.setNickname("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); // 예외 발생

        //then
        fail("예외가 발생해야 한다.");
    }

}