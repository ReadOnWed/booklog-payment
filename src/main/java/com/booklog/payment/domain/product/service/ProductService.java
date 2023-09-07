package com.booklog.payment.domain.product.service;

import java.util.List;

import com.booklog.payment.domain.product.dto.ProductDto;

public interface ProductService {
	void removeProduct(Long productId);

	ProductDto addProduct(ProductDto productDto);

	ProductDto updateProduct(ProductDto productDto);

	ProductDto getProduct(Long productId);

	List<ProductDto> getProductlist(Integer pageNo, String criteria);
}
