package com.booklog.payment.domain.product.dto;

import lombok.Getter;

@Getter
public enum ProductTradeStatus {
	RESERVING("예약중"),
	DEALING("거래중"),
	SOLD("판매완료");

	ProductTradeStatus(String status) {
		this.status = status;
	}

	private final String status;
}
