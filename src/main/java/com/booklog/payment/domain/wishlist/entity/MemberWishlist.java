package com.booklog.payment.domain.wishlist.entity;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Document(collection = "memberWishList")
public class MemberWishlist {
	@Id
	private String id;

	private Long memberId;
	private Set<Long> productIds;

	public MemberWishlist(Long memberId, Set<Long> productIds) {
		this.memberId = memberId;
		this.productIds = productIds;
	}

	public void updateProductIds(Set<Long> productIds) {
		this.productIds = productIds;
	}
}
