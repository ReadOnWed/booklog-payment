package com.booklog.payment.wishlist.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface WishlistService {
    List wishProduct(Map<String, String> map);

    Long getProductWishNum(String productNo);

    Set getWishList(String userNo);

    List unwishProduct(Map<String, String> map);
}
