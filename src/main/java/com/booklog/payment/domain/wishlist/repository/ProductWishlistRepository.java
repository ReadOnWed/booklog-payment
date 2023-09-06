package com.booklog.payment.domain.wishlist.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.booklog.payment.domain.wishlist.entity.ProductWishlist;

public interface ProductWishlistRepository extends MongoRepository<ProductWishlist, String> {
	Optional<ProductWishlist> findProductWishlistByProductId(Long productId);
}
