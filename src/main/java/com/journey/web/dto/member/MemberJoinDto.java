package com.journey.web.dto.member;

import com.journey.web.domain.member.Member;
import com.journey.web.domain.member.MemberStatus;
import com.journey.web.domain.member.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinDto {

    @Email
    @NotBlank
    private String memberEmail;

    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{6,16}$",
            message = "비밀번호는 6~16자리수여야 합니다. 영문 대소문자, 숫자, 특수문자를 1개 이상 포함해야 합니다.")
    private String pwd;

    @NotBlank
    private String passwordConfirm;

    @NotBlank
    private String firstname;

    @NotBlank
    private String lastname;

    @NotBlank
    @Size(min = 3, max = 20, message = "닉네임은 3~20자리수여야 합니다.")
    private String nickName;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberEmail(memberEmail)
                .pwd(passwordEncoder.encode(pwd))
                .memberType(MemberType.ROLE_USER)
                .memberStatus(MemberStatus.OFFLINE)
                .firstname(firstname)
                .lastname(lastname)
                .nickname(nickName)
                .build();
    }

    public Member toGoogleMember() {
        return Member.builder()
                .memberEmail(memberEmail)
                .pwd(pwd)
                .memberType(MemberType.ROLE_USER)
                .memberStatus(MemberStatus.OFFLINE)
                .firstname(firstname)
                .lastname(lastname)
                .nickname(nickName)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(memberEmail, pwd);
    }
}
