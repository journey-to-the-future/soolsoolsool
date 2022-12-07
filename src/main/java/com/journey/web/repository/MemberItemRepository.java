package com.journey.web.repository;

import com.journey.web.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberItemRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.item.id = :itemId and r.member.id = :memberId")
    Review findByMemberIdAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);
}
