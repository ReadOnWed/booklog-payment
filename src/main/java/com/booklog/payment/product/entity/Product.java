package com.booklog.payment.product.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.UpdateTimestamp;

import com.booklog.payment.product.domain.ProductDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@DynamicUpdate
public class Product {
	// 필수 Column
	@Id
	@Column(name = "product_id", nullable = false, insertable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long productId;
	@Column(name = "seller_id", nullable = false)
	private long sellerId;
	@Column(name = "book_id", nullable = false)
	private long bookId;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "price", nullable = false)
	private int price;
	@Column(name = "cond", nullable = false)
	private int condition;
	@Column(name = "reg_date")
	@CreationTimestamp
	private OffsetDateTime regDate;
	@Column(name = "delivery_poss", nullable = false)
	private int deliveryPoss;
	@Column(name = "trade_status", nullable = false)
	private int tradeStatus;
	@Column(name = "description", nullable = false)
	private String description;

	// 선택 Column
	@Column(name = "delivery_fee")
	private int deliveryFee = -1;
	@Column(name = "trade_location")
	private String tradeLocation = null;

	// Update 시에만 변경되는 Column
	@Column(name = "update_date")
	@UpdateTimestamp
	private OffsetDateTime updateDate = null;

	public Product update() {

		return this;
	}

	@Builder
	public Product(long productId, long sellerId, long bookId, String name, int price, int condition,
		int deliveryPoss, int tradeStatus, String description, int deliveryFee,
		String tradeLocation) {
		this.productId = productId;
		this.sellerId = sellerId;
		this.bookId = bookId;
		this.name = name;
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
			.name(product.getName())
			.price(product.getPrice())
			.condition(product.getCondition())
			.deliveryPoss(product.getDeliveryPoss())
			.deliveryFee(product.getDeliveryFee())
			.tradeLocation(product.getTradeLocation())
			.tradeStatus(product.getTradeStatus())
			.description(product.getDescription())
			.build();
	}
}
