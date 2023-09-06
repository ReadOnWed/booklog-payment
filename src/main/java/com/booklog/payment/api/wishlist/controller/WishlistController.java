package com.booklog.payment.api.wishlist.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
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

	@PostMapping("/product")
	public ResponseEntity<WishlistApiDto.ProductsResponse> addWishlist(@RequestBody WishlistApiDto.Request request) {
		wishlistService.addWishList(WishlistDto.from(request));

		return ResponseEntity.ok(null);
	}

	@PostMapping("/product/delete")
	public ResponseEntity<?> deleteWishlist(@RequestBody Map<String, String> map) {

		return ResponseEntity.ok(null);
	}

	// @GetMapping("/product/{productNo}")
	// public ResponseEntity<> getWishMemberCount(@PathVariable String productNo) {
	//
	// }
	//
	// @GetMapping("/product/user/{userNo}")
	// public ResponseEntity<> getWishList(@PathVariable String userNo) {
	//
	// }

}
