package com.booklog.payment.wishlist.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public List<Object> wishProduct(Map<String, Long> map) {
		return redisTemplate.execute(new SessionCallback<>() {
			@Override
			public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
				operations.multi();
				String productKey = ("wishlist:product-to-user:" + map.get("productNo"));
				operations.opsForSet().add((K)productKey, (V)map.get("userNo"));
				String userKey = ("wishlist:user-to-product:" + map.get("userNo"));
				operations.opsForSet().add((K)userKey, (V)map.get("productNo"));
				return operations.exec();
			}
		});
	}

	@Override
	public Long getProductWishNum(String productNo) {
		SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
		return setOperations.size("wishlist:product-to-user:" + productNo);
	}

	@Override
	public Set<Object> getWishList(String userNo) {
		SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
		return setOperations.members("wishlist:user-to-product:" + userNo);
	}

	@Override
	public List<Object> unwishProduct(Map<String, String> map) {
		return redisTemplate.execute(new SessionCallback<>() {
			@Override
			public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
				operations.multi();
				String productKey = "wishlist:product-to-user:" + map.get("productNo");
				operations.opsForSet().remove((K)productKey, (V)map.get("userNo"));
				String userKey = "wishlist:user-to-product:" + map.get("userNo");
				operations.opsForSet().remove((K)userKey, (V)map.get("productNo"));
				return operations.exec();
			}
		});
	}
}
