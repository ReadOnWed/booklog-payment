package com.booklog.payment.api.wishlist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booklog.payment.api.wishlist.dto.WishlistApiDto;
import com.booklog.payment.domain.wishlist.dto.WishlistDto;
import com.booklog.payment.domain.wishlist.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
	private final WishlistService wishlistService;

	@PostMapping()
	public ResponseEntity<WishlistApiDto.ProductsResponse> addWishlist(@RequestBody WishlistApiDto.Request request) {
		WishlistDto.MemberWishlistDto wishlistDto = wishlistService.addWishList(WishlistDto.from(request));

		return ResponseEntity.ok(WishlistApiDto.ProductsResponse.from(wishlistDto));
	}

	@PostMapping("/delete")
	public ResponseEntity<WishlistApiDto.ProductsResponse> deleteWishlist(@RequestBody WishlistApiDto.Request request) {
		WishlistDto.MemberWishlistDto wishlistDto = wishlistService.deleteWishList(WishlistDto.from(request));

		return ResponseEntity.ok(WishlistApiDto.ProductsResponse.from(wishlistDto));
	}

	@GetMapping("/count/{productId}")
	public ResponseEntity<WishlistApiDto.MembersCountResponse> getWishMemberCount(@PathVariable Long productId) {
		WishlistDto.MemberCountDto wishMemberCount = wishlistService.getWishMemberCount(productId);

		return ResponseEntity.ok(WishlistApiDto.MembersCountResponse.from(wishMemberCount));
	}

	@GetMapping("/{memberId}")
	public ResponseEntity<WishlistApiDto.ProductsResponse> getMyWishlist(@PathVariable Long memberId) {
		WishlistDto.MemberWishlistDto myWishlist = wishlistService.getMyWishlist(memberId);

		return ResponseEntity.ok(WishlistApiDto.ProductsResponse.from(myWishlist));
	}

}
