package com.journey.web.controller.member;

import com.journey.web.domain.member.Member;
import com.journey.web.dto.member.*;
import com.journey.web.dto.item.*;
import com.journey.web.dto.response.ResponseDto;
import com.journey.web.service.MemberService;
import com.journey.web.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "회원 프로필 조회", notes = "회원 프로필을 반환합니다.")
    @GetMapping("/")
    public ResponseEntity memberProfile() {
        Long memberId = SecurityUtil.getCurrentMemberId();

        Member member = memberService.findByMemberId(memberId);

        MemberResponseDto memberProfile = MemberResponseDto.builder()
                .memberId(member.getId())
                .email(member.getMemberEmail())
                .firstname(member.getFirstname())
                .lastname(member.getLastname())
                .nickname(member.getNickname())
                .build();

        return new ResponseEntity<ResponseDto>(new ResponseDto<MemberResponseDto>(200, "success",
                memberProfile), HttpStatus.OK);
    }

    @ApiOperation(value = "회원 프로필 편집 화면 진입 시 기본 data")
    @GetMapping("/update")
    public ResponseEntity<?> memberProfileEdit() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Member member = memberService.findByMemberId(memberId);
        MemberUpdateProfileDto memberUpdateProfileDto = MemberUpdateProfileDto.builder()
                .nickName(member.getNickname())
                .build();
        return new ResponseEntity<ResponseDto>(new ResponseDto(200, "success", memberUpdateProfileDto)
                , HttpStatus.OK);
    }

    @ApiOperation(value = "회원 프로필 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 프로필 수정 완료")
    })
    @PutMapping("/")
    public ResponseEntity memberUpdate(@Valid @RequestPart MemberUpdateDto memberUpdateDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        memberService.updateMember(memberId, memberUpdateDto);

        return new ResponseEntity<ResponseDto>(new ResponseDto(200, "success", "회원 수정 완료")
                , HttpStatus.OK);
    }

    @ApiOperation(value = "상품 즐겨찾기 등록 및 해제")
    @PostMapping("detail/item/favorite")
    public ResponseEntity<?> myItemFavorite(@RequestBody MemberItemFavoriteReqDto memberItemFavoriteReqDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        memberService.memberItemFavoriteToggle(memberId, memberItemFavoriteReqDto.getItemId());
        return new ResponseEntity<ResponseDto>(new ResponseDto(200, "즐겨찾기 토글", null), HttpStatus.OK);
    }
}
