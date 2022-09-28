package com.journey.web.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberResponseDto {

    private String email;
    private String firstname;
    private String lastname;
    private String nickname;
}
