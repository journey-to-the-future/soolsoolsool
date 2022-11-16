package com.journey.web.service;

import com.journey.web.domain.member.Member;
import com.journey.web.domain.member.MemberStatus;
import com.journey.web.dto.member.MemberJoinDto;
import com.journey.web.dto.member.MemberJoinResponseDto;
import com.journey.web.dto.member.MemberUpdateDto;
import com.journey.web.exception.CustomException;
import com.journey.web.exception.ErrorCode;
import com.journey.web.repository.MemberRepository;
import com.journey.web.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.journey.web.exception.ErrorCode.MEMBER_NOT_FOUND;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(Member member) {

        memberRepository.save(member);
        return member.getId();
    }

    // 이메일로 회원 조회
    public Optional<Member> findByMemberEmail(String email) {
        return memberRepository.findByMemberEmail(email);
    }

    // 회원 ID 조회
    public Member findByMemberId(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

    // 닉네임 중복 조회
    public boolean existsByNickName(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 닉네임 수정
    @Transactional
    public void updateMember(Long memberId, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        member.setNickname(memberUpdateDto.getNickname());

    }

    // 로그아웃 상태
    @Transactional
    public void logoutStatus(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        member.updateMemberStatus(MemberStatus.OFFLINE);
    }


    // 회원 탈퇴
    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.deleteById(memberId);
    }

    // 현재 SecurityContext 에 있는 유저 정보 가져오기
    @Transactional(readOnly = true)
    public MemberJoinResponseDto getMyInfo() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberJoinResponseDto::of)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
    }

}
