package com.booklog.payment.api.wishlist.dto;

import java.util.Set;

import com.booklog.payment.domain.wishlist.dto.WishlistDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

public class WishlistApiDto {
	@Getter
	@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class Request {
		private Long productId;
		private Long memberId;

		@Builder
		public Request(Long productId, Long memberId) {
			this.productId = productId;
			this.memberId = memberId;
		}
	}

	@Getter
	@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class ProductsResponse {
		private Long memberId;
		private Set<Long> productIds;

		@Builder
		public ProductsResponse(Long memberId, Set<Long> productIds) {
			this.memberId = memberId;
			this.productIds = productIds;
		}

		public static ProductsResponse from(WishlistDto.MemberWishlistDto wishlistDto) {
			return ProductsResponse.builder()
				.memberId(wishlistDto.getMemberId())
				.productIds(wishlistDto.getProductIds())
				.build();
		}
	}

	@Getter
	@JsonNaming(value = PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class MembersCountResponse {
		private Long productId;
		private Integer membersCount;

		@Builder
		public MembersCountResponse(Long productId, Integer membersCount) {
			this.productId = productId;
			this.membersCount = membersCount;
		}

		public static MembersCountResponse from(WishlistDto.MemberCountDto wishlistDto) {
			return MembersCountResponse.builder()
				.productId(wishlistDto.getProductId())
				.membersCount(wishlistDto.getMemberCount())
				.build();
		}
	}
}
