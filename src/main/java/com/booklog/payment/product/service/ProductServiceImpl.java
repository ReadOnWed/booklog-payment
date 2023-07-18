package com.booklog.payment.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.booklog.payment.product.domain.ProductDto;
import com.booklog.payment.product.entity.Product;
import com.booklog.payment.product.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public boolean removeProduct(long productNum) {
		if (productExistsById(productNum)) {
			return false;
		}
		productRepository.deleteById(productNum);
		return true;
	}

	@Override
	public boolean registProduct(ProductDto productDto) {
		// 유효성 검사

		if (deliveryPoss(productDto) || deliveryImposs(productDto)) {
			return false;
		}

		Product product = Product.of(productDto);

		productRepository.save(product);
		return true;
	}

	@Override
	public boolean updateProduct(ProductDto productDto) {
		if (!productRepository.existsById(productDto.getProductId())) {
			return false;
		}
		Product product = Product.of(productDto);

		productRepository.save(product);
		return true;
	}

	private static boolean deliveryImposs(ProductDto productDto) {
		return productDto.getDeliveryPoss() == 0 && productDto.getTradeLocation() != null;
	}

	private static boolean deliveryPoss(ProductDto productDto) {
		return productDto.getDeliveryPoss() == 1 && productDto.getDeliveryFee() != -1;
	}

	private boolean productExistsById(long productNum) {
		return productRepository.existsById(productNum);
	}
}
