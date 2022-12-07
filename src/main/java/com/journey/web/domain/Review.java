package com.journey.web.domain;

import com.journey.web.domain.item.Item;
import com.journey.web.domain.member.Member;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private String review;
    private int starPoint;
    private Boolean is_favorite;

    public static Review createReview(Member member, Item item) {
        Review review = Review.builder()
                .build();
        review.setMember(member);
        review.setItem(item);
        return review;
    }

//    public void setItem(Item item) {
//        this.item = item;
//        item.getMemberItemList().add(this);
//    }

    public void updateFavorite(boolean is_favorite) {
        this.is_favorite = is_favorite;
    }
}
