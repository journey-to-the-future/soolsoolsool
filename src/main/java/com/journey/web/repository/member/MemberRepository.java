package com.journey.web.repository.member;

import com.journey.web.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    List<Member> findAll();

    Optional<Member> findByMemberEmail(String memberEmail);

    boolean existsByMemberEmail(String memberEmail);

    boolean existsByNickname(String nickname);

}
