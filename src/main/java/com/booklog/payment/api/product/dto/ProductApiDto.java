package com.booklog.payment.api.product.dto;

import java.time.LocalDateTime;

import com.booklog.payment.domain.product.dto.ProductCondition;
import com.booklog.payment.domain.product.dto.ProductDeliveryPoss;
import com.booklog.payment.domain.product.dto.ProductDto;
import com.booklog.payment.domain.product.dto.ProductTradeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

public class ProductApiDto {
	@Getter
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private Long sellerId;
		private Long bookId;
		private String title;
		private Integer price;
		private ProductCondition condition;
		private ProductDeliveryPoss deliveryPoss;
		private ProductTradeStatus tradeStatus;
		private String description;
		private Integer deliveryFee;
		private String tradeLocation;

		@Builder
		public Request(Long sellerId, Long bookId, String title, Integer price, ProductCondition condition,
			ProductDeliveryPoss deliveryPoss, ProductTradeStatus tradeStatus, String description, Integer deliveryFee,
			String tradeLocation) {
			this.sellerId = sellerId;
			this.bookId = bookId;
			this.title = title;
			this.price = price;
			this.condition = condition;
			this.deliveryPoss = deliveryPoss;
			this.tradeStatus = tradeStatus;
			this.description = description;
			this.deliveryFee = deliveryFee;
			this.tradeLocation = tradeLocation;
		}
	}

	@Getter
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Response {
		private Long productId;
		private String title;
		private Integer price;
		private ProductCondition condition;
		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
		private LocalDateTime createTime;
		private ProductDeliveryPoss deliveryPoss;
		private ProductTradeStatus tradeStatus;
		private String description;
		private Integer deliveryFee;
		private String tradeLocation;

		@Builder
		public Response(Long productId, String title, Integer price, ProductCondition condition,
			LocalDateTime createTime,
			ProductDeliveryPoss deliveryPoss, ProductTradeStatus tradeStatus, String description, Integer deliveryFee,
			String tradeLocation) {
			this.productId = productId;
			this.title = title;
			this.price = price;
			this.condition = condition;
			this.createTime = createTime;
			this.deliveryPoss = deliveryPoss;
			this.tradeStatus = tradeStatus;
			this.description = description;
			this.deliveryFee = deliveryFee;
			this.tradeLocation = tradeLocation;
		}

		public static Response from(ProductDto productDto) {
			return Response.builder()
				.productId(productDto.getProductId())
				.title(productDto.getTitle())
				.price(productDto.getPrice())
				.condition(productDto.getCondition())
				.createTime(productDto.getCreateTime())
				.deliveryPoss(productDto.getDeliveryPoss())
				.tradeStatus(productDto.getTradeStatus())
				.description(productDto.getDescription())
				.deliveryFee(productDto.getDeliveryFee())
				.tradeLocation(productDto.getTradeLocation())
				.build();
		}
	}
}
