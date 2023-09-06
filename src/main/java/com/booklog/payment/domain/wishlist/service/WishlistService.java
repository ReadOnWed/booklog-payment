package com.booklog.payment.domain.wishlist.service;

import com.booklog.payment.domain.wishlist.dto.WishlistDto;

public interface WishlistService {

	WishlistDto.MemberWishlistDto addWishList(WishlistDto wishlistDto);

	WishlistDto.MemberWishlistDto deleteWishList(WishlistDto wishlistDto);

	WishlistDto.MemberCountDto getWishMemberCount(Long productId);

	WishlistDto.MemberWishlistDto getMyWishlist(Long memberId);
}
