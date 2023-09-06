package com.booklog.payment.domain.wishlist.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booklog.payment.domain.wishlist.entity.MemberWishlist;

public interface MemberWishlistRepository extends MongoRepository<MemberWishlist, String> {
	Optional<MemberWishlist> findProductWishlistByMemberId(Long memberId);
}
