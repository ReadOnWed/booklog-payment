package com.booklog.payment.domain.product.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.booklog.payment.domain.product.dto.ProductCondition;
import com.booklog.payment.domain.product.dto.ProductDeliveryPoss;
import com.booklog.payment.domain.product.dto.ProductDto;
import com.booklog.payment.domain.product.dto.ProductTradeStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Product {
	// 필수 Column
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@Column(nullable = false)
	private Long sellerId;
	@Column(nullable = false)
	private Long bookId;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private Integer price;
	@Column(nullable = false)
	private ProductCondition condition;
	@CreatedDate
	private LocalDateTime createTime;
	@Column(nullable = false)
	private ProductDeliveryPoss deliveryPoss;
	@Column(nullable = false)
	private ProductTradeStatus tradeStatus;
	@Column(nullable = false)
	private String description;

	// 선택 Column
	@Column(nullable = false)
	private Integer deliveryFee = -1;
	@Column(nullable = false)
	private String tradeLocation = "직거래 불가능";

	// Update 시에만 변경되는 Column
	private LocalDateTime updateTime = null;

	@Builder
	public Product(long productId, long sellerId, long bookId, String title, int price, ProductCondition condition,
		ProductDeliveryPoss deliveryPoss, ProductTradeStatus tradeStatus, String description, int deliveryFee,
		String tradeLocation) {
		this.productId = productId;
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

	public static Product of(ProductDto product) {
		return Product.builder()
			.sellerId(product.getSellerId())
			.bookId(product.getBookId())
			.title(product.getTitle())
			.price(product.getPrice())
			.condition(product.getCondition())
			.deliveryPoss(product.getDeliveryPoss())
			.deliveryFee(product.getDeliveryFee())
			.tradeLocation(product.getTradeLocation())
			.tradeStatus(product.getTradeStatus())
			.description(product.getDescription())
			.build();
	}

	public void updateTitle(String title) {
		this.title = title;
	}

	public void updatePrice(Integer price) {
		this.price = price;
	}

	public void updateCondition(ProductCondition condition) {
		this.condition = condition;
	}

	public void updateDeliveryPoss(ProductDeliveryPoss deliveryPoss) {
		this.deliveryPoss = deliveryPoss;
	}

	public void updateTradeStatus(ProductTradeStatus tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public void updateDescription(String description) {
		this.description = description;
	}

	public void updateDeliveryFee(Integer deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public void updateTradeLocation(String tradeLocation) {
		this.tradeLocation = tradeLocation;
	}

	public void updateUpdateTime(LocalDateTime now) {
		this.updateTime = now;
	}
}
