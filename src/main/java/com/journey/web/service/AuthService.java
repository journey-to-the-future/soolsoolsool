package com.journey.web.service;

import com.journey.web.config.jwt.TokenProvider;
import com.journey.web.domain.member.Member;
import com.journey.web.domain.member.MemberStatus;
import com.journey.web.dto.member.MemberJoinDto;
import com.journey.web.dto.member.MemberJoinResponseDto;
import com.journey.web.dto.member.MemberLoginDto;
import com.journey.web.dto.member.PasswordResetDto;
import com.journey.web.dto.token.TokenDto;
import com.journey.web.dto.token.TokenRequestDto;
import com.journey.web.exception.CustomException;
import com.journey.web.exception.ErrorCode;
import com.journey.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.journey.web.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
//    private final ClientGoogle clientGoogle;
//    private final AwsS3Service awsS3Service;

    /**
     * 회원 가입
     */
    @Transactional
    public MemberJoinResponseDto signup(MemberJoinDto memberJoinDto) {
        if (memberRepository.existsByMemberEmail(memberJoinDto.getEmail())) {
            throw new CustomException(ErrorCode.ALREADY_SAVED_MEMBER);
        }

        Member member = memberJoinDto.toMember(passwordEncoder);

        Member saveMember = memberRepository.save(member);

        return MemberJoinResponseDto.of(saveMember);
    }

    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto) throws RuntimeException{

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();

        Member member = memberRepository.findByMemberEmail(memberLoginDto.getMemberEmail()).orElseThrow(() ->
                new CustomException(MEMBER_EMAIL_NOT_FOUND));

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        member.updateMemberStatus(MemberStatus.ONLINE);
        // 5. 토큰 발급
        return tokenDto;
    }

    @Transactional
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new CustomException(INVALID_REFRESH_TOKEN);
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // redis에 있는 refreshToken과 비교
        tokenProvider.checkRefreshToken(authentication.getName(), tokenRequestDto.getRefreshToken());

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);


        // 토큰 발급
        return tokenDto;
    }

    @Transactional
    public void logout(String accessToken) {
        Authentication authentication = tokenProvider.getAuthentication(accessToken);
        tokenProvider.logout(authentication.getName(), accessToken);
    }

    @Transactional
    public void passwordReset(PasswordResetDto passwordResetDto) {
        Member member = memberRepository.findByMemberEmail(passwordResetDto.getEmail()).orElseThrow(
                () -> new CustomException(MEMBER_EMAIL_NOT_FOUND));
        member.updatePwd(passwordEncoder.encode(passwordResetDto.getPassword()));

    }
}
