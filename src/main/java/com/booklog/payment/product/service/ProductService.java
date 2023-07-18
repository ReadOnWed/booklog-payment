package com.booklog.payment.product.service;

import com.booklog.payment.product.domain.ProductDto;

public interface ProductService {
	boolean removeProduct(long productNum);

	boolean registProduct(ProductDto productDto);

	boolean updateProduct(ProductDto productDto);
}
