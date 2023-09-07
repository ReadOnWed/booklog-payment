package com.booklog.payment.domain.product.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.booklog.payment.domain.product.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findAll(Pageable pageable);

	Optional<Product> findByProductId(Long productId);

	void deleteByProductId(Long productId);
}
