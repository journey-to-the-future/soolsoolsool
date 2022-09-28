package com.journey.web.controller.member;

import com.journey.web.controller.ItemApiController;
import com.journey.web.domain.Member;
import com.journey.web.domain.item.Item;
import com.journey.web.dto.member.MemberResponseDto;
import com.journey.web.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

//    @GetMapping("/api/member/{id}")
//    public Result member(@PathVariable("id") Long id) {
//        Member findMember = memberService.findOne(id);
//        List<MemberResponseDto> collect = findMember.
//
//        return new Result(findMember);
//    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }
}
