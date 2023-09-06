package com.booklog.payment.domain.wishlist.dto;

import java.util.Set;

import com.booklog.payment.api.wishlist.dto.WishlistApiDto;
import com.booklog.payment.domain.wishlist.entity.MemberWishlist;
import com.booklog.payment.domain.wishlist.entity.ProductWishlist;

import lombok.Builder;
import lombok.Getter;

@Getter
public class WishlistDto {
	private Long productId;
	private Long memberId;

	@Builder
	public WishlistDto(Long productId, Long memberId) {
		this.productId = productId;
		this.memberId = memberId;
	}

	public static WishlistDto from(WishlistApiDto.Request request) {
		return WishlistDto.builder()
			.productId(request.getProductId())
			.memberId(request.getMemberId())
			.build();
	}

	@Getter
	public static class MemberWishlistDto {
		private Long memberId;
		private Set<Long> productIds;

		@Builder
		public MemberWishlistDto(Long memberId, Set<Long> productIds) {
			this.memberId = memberId;
			this.productIds = productIds;
		}

		public static MemberWishlistDto from(MemberWishlist memberWishlist) {
			return MemberWishlistDto.builder()
				.memberId(memberWishlist.getMemberId())
				.productIds(memberWishlist.getProductIds())
				.build();
		}
	}

	@Getter
	public static class MemberCountDto {
		private Long productId;
		private Integer memberCount;

		@Builder
		public MemberCountDto(Long productId, Integer memberCount) {
			this.productId = productId;
			this.memberCount = memberCount;
		}

		public static MemberCountDto from(ProductWishlist productWishlist) {
			return MemberCountDto.builder()
				.productId(productWishlist.getProductId())
				.memberCount(productWishlist.getMemberIds().size())
				.build();
		}
	}
}
