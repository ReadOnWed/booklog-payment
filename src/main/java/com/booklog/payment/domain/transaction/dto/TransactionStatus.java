package com.booklog.payment.domain.transaction.dto;

public enum TransactionStatus {
	DOING("거래 중"),
	CANCEL("거래 취소"),
	FINISH("거래 완료");

	TransactionStatus(String state) {
		this.state = state;
	}

	private final String state;
}
