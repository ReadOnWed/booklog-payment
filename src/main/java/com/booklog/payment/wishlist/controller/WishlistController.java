package com.booklog.payment.wishlist.controller;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booklog.payment.wishlist.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {
	private final WishlistService wishlistService;

	@PostMapping("/product")
	public ResponseEntity<Map<String, String>> wishProduct(@RequestBody Map<String, Long> map) {
		Map<String, String> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.NO_CONTENT;
		try {
			List<Object> result = wishlistService.wishProduct(map);

			if (result != null) {
				setHttpHeaders();

				if (userSaveSuccess(result))
					resultMap.put("user", "success");
				if (productSaveSuccess(result))
					resultMap.put("product", "success");

				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<>(resultMap, status);
	}

	@PostMapping("/product/delete")
	public ResponseEntity<Map<String, String>> unwishProduct(@RequestBody Map<String, String> map) {
		Map<String, String> resultMap = new HashMap<>();

		List<Object> result = wishlistService.unwishProduct(map);

		if (result != null) {
			if (userSaveSuccess(result))
				resultMap.put("user", "success");
			if (productSaveSuccess(result))
				resultMap.put("product", "success");

			return ResponseEntity.ok()
				.headers(setHttpHeaders())
				.body(resultMap);
		}

		return ResponseEntity.internalServerError()
			.headers(setHttpHeaders())
			.body(resultMap);
	}

	@PostMapping("/product/{productNo}")
	public ResponseEntity<Long> getProductWishNum(@PathVariable String productNo) {
		Long result = wishlistService.getProductWishNum(productNo);

		if (result != null) {
			setHttpHeaders();

			return ResponseEntity.ok()
				.headers(setHttpHeaders())
				.body(result);
		}

		return ResponseEntity.internalServerError()
			.headers(setHttpHeaders())
			.body(null);
	}

	@PostMapping("/product/user/{userNo}")
	public ResponseEntity<Set<Object>> getWishList(@PathVariable String userNo) {
		Set<Object> result = wishlistService.getWishList(userNo);

		if (result != null) {
			setHttpHeaders();

			return ResponseEntity.ok()
				.headers(setHttpHeaders())
				.body(result);
		}

		return ResponseEntity.internalServerError()
			.headers(setHttpHeaders())
			.body(null);
	}

	private static boolean productSaveSuccess(List<Object> result) {
		return (Integer)result.get(1) == 1;
	}

	private static boolean userSaveSuccess(List<Object> result) {
		return (Integer)result.get(0) == 1;
	}

	private static HttpHeaders setHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		return headers;
	}
}
