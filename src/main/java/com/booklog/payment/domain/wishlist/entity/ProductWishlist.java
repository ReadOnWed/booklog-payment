package com.booklog.payment.domain.wishlist.entity;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Document(collection = "productWishList")
public class ProductWishlist {

	@Id
	private String id;

	private Long productId;
	private Set<Long> memberIds;

	public ProductWishlist(Long productId, Set<Long> memberIds) {
		this.productId = productId;
		this.memberIds = memberIds;
	}

	public void updateMemberIds(Set<Long> memberIds) {
		this.memberIds = memberIds;
	}
}
