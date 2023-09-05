package com.booklog.payment.domain.product.dto;

public enum ProductDeliveryPoss {
	POSSIBLE("택배 가능"),
	IMPOSSIBLE("택배 불가능");

	ProductDeliveryPoss(String message) {
		this.message = message;
	}

	private final String message;
}
