package com.booklog.payment.wishlist.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WishlistService {
	List<Object> wishProduct(Map<String, Long> map);

	Long getProductWishNum(String productNo);

	Set<Object> getWishList(String userNo);

	List<Object> unwishProduct(Map<String, String> map);
}
