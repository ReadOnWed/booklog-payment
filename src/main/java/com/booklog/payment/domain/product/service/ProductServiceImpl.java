package com.booklog.payment.domain.product.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.booklog.payment.domain.product.dto.ProductDeliveryPoss;
import com.booklog.payment.domain.product.dto.ProductDto;
import com.booklog.payment.domain.product.entity.Product;
import com.booklog.payment.domain.product.repository.ProductRepository;
import com.booklog.payment.global.error.ErrorCode;
import com.booklog.payment.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;

	private static final Integer PAGE_SIZE = 10;

	@Override
	public ProductDto getProduct(Long productId) {
		Optional<Product> product = productRepository.findByProductId(productId);

		return ProductDto.from(product.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_PRODUCT)));
	}

	@Override
	public List<ProductDto> getProductlist(Integer pageNo, String criteria) {
		Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE, Sort.by(Sort.Direction.DESC, criteria));
		Page<ProductDto> page = productRepository.findAll(pageable).map(ProductDto::from);

		return page.getContent();
	}

	@Override
	public void removeProduct(Long productId) {
		productRepository.findByProductId(productId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_PRODUCT));
		productRepository.deleteByProductId(productId);
	}

	@Override
	public ProductDto addProduct(ProductDto productDto) {
		// 유효성 검사
		if (deliveryPoss(productDto)) {
			throw new BusinessException(ErrorCode.NOT_EXIST_TRADE_LOCATION);
		}
		if (deliveryImposs(productDto)) {
			throw new BusinessException(ErrorCode.NOT_EXIST_DELIVERY_FEE);
		}

		Product product = productRepository.save(Product.of(productDto));

		return ProductDto.from(product);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto) {
		Product product = productRepository.findByProductId(productDto.getProductId())
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_PRODUCT));

		updateProduct(productDto, product);

		return ProductDto.from(product);
	}

	private static void updateProduct(ProductDto productDto, Product product) {
		if (productDto.getTitle() != null)
			product.updateTitle(productDto.getTitle());
		if (productDto.getPrice() != null)
			product.updatePrice(productDto.getPrice());
		if (productDto.getCondition() != null)
			product.updateCondition(productDto.getCondition());
		if (productDto.getDeliveryPoss() != null)
			product.updateDeliveryPoss(productDto.getDeliveryPoss());
		if (productDto.getTradeStatus() != null)
			product.updateTradeStatus(productDto.getTradeStatus());
		if (productDto.getDescription() != null)
			product.updateDescription(productDto.getDescription());
		if (productDto.getDeliveryFee() != null)
			product.updateDeliveryFee(productDto.getDeliveryFee());
		if (productDto.getTradeLocation() != null)
			product.updateTradeLocation(productDto.getTradeLocation());
		product.updateUpdateTime(LocalDateTime.now());
	}

	private static boolean deliveryImposs(ProductDto productDto) {
		return productDto.getDeliveryPoss() == ProductDeliveryPoss.IMPOSSIBLE
			&& productDto.getTradeLocation() != "직거래 불가능";
	}

	private static boolean deliveryPoss(ProductDto productDto) {
		return productDto.getDeliveryPoss() == ProductDeliveryPoss.POSSIBLE
			&& productDto.getDeliveryFee() != -1;
	}

	private boolean productExistsById(long productNum) {
		return productRepository.existsById(productNum);
	}
}
