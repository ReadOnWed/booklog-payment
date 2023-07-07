package com.booklog.payment.wishlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class WishlistServiceImpl implements WishlistService {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public WishlistServiceImpl(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public List wishProduct(Map<String, String> map) {
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                String key = "wishlist:product-to-user:" + map.get("productNo");
                operations.opsForSet().add((K) key, (V) map.get("userNo"));
                key = "wishlist:user-to-product:" + map.get("userNo");
                operations.opsForSet().add((K) key, (V) map.get("productNo"));
                return operations.exec();
            }
        });
        return execute;
    }

    @Override
    public Long getProductWishNum(String productNo) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.size("wishlist:product-to-user" + productNo);
    }

    @Override
    public Set getWishList(String userNo) {
        SetOperations<String, Object> setOperations = redisTemplate.opsForSet();
        return setOperations.members("wishlist:user-to-product:" + userNo);
    }

    @Override
    public List unwishProduct(Map<String, String> map) {
        List<Object> execute = redisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
                operations.multi();
                String key = "wishlist:product-to-user:" + map.get("productNo");
                operations.opsForSet().remove((K) key, (V) map.get("userNo"));
                key = "wishlist:user-to-product:" + map.get("userNo");
                operations.opsForSet().remove((K) key, (V) map.get("productNo"));
                return operations.exec();
            }
        });
        return execute;
    }
}
