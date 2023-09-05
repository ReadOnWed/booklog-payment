package com.booklog.payment.domain.product.dto;

public enum ProductCondition {
	VERYGOOD("매우 좋음"),
	GOOD("좋음"),
	NORMAL("보통"),
	BAD("나쁨"),
	VERYBAD("매우 나쁨");

	ProductCondition(String state) {
		this.state = state;
	}

	private final String state;
}
