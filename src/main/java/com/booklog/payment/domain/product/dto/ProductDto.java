package com.booklog.payment.domain.product.dto;

import java.time.LocalDateTime;

import com.booklog.payment.api.product.dto.ProductApiDto;
import com.booklog.payment.domain.product.entity.Product;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ProductDto {
	private Long productId;
	private Long sellerId;
	private Long bookId;
	private String title;
	private Integer price;
	private ProductCondition condition;
	private LocalDateTime createTime;
	private ProductDeliveryPoss deliveryPoss;
	private ProductTradeStatus tradeStatus;
	private String description;

	private Integer deliveryFee = -1;
	private String tradeLocation = null;

	private LocalDateTime updateTime;

	@Builder
	public ProductDto(Long productId, Long sellerId, Long bookId, String title, Integer price,
		ProductCondition condition,
		LocalDateTime createTime, ProductDeliveryPoss deliveryPoss, ProductTradeStatus tradeStatus, String description,
		Integer deliveryFee, String tradeLocation, LocalDateTime updateTime) {
		this.productId = productId;
		this.sellerId = sellerId;
		this.bookId = bookId;
		this.title = title;
		this.price = price;
		this.condition = condition;
		this.createTime = createTime;
		this.deliveryPoss = deliveryPoss;
		this.tradeStatus = tradeStatus;
		this.description = description;
		this.deliveryFee = deliveryFee;
		this.tradeLocation = tradeLocation;
		this.updateTime = updateTime;
	}

	public static ProductDto from(ProductApiDto.Request request) {
		return ProductDto.builder()
			.sellerId(request.getSellerId())
			.bookId(request.getBookId())
			.title(request.getTitle())
			.price(request.getPrice())
			.condition(request.getCondition())
			.deliveryPoss(request.getDeliveryPoss())
			.tradeStatus(request.getTradeStatus())
			.description(request.getDescription())
			.deliveryFee(request.getDeliveryFee())
			.tradeLocation(request.getTradeLocation())
			.build();
	}

	public static ProductDto from(Product product) {
		return ProductDto.builder()
			.productId(product.getProductId())
			.sellerId(product.getSellerId())
			.bookId(product.getBookId())
			.title(product.getTitle())
			.price(product.getPrice())
			.condition(product.getCondition())
			.createTime(product.getCreateTime())
			.deliveryPoss(product.getDeliveryPoss())
			.tradeStatus(product.getTradeStatus())
			.description(product.getDescription())
			.deliveryFee(product.getDeliveryFee())
			.tradeLocation(product.getTradeLocation())
			.updateTime(product.getUpdateTime())
			.build();
	}
}
