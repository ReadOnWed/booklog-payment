package com.booklog.payment.domain.wishlist.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.booklog.payment.domain.wishlist.entity.ProductWishlist;

public interface ProductWishlistRepository extends MongoRepository<ProductWishlist, String> {
	@Query("{productId: '?0'}")
	Optional<ProductWishlist> findProductWishlistByProductId(Long productId);
}
