package com.booklog.payment.global.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public enum ErrorCode {
	// 중고 제품
	NOT_EXIST_PRODUCT(HttpStatus.BAD_REQUEST, "P-001", "존재하지 않는 상품입니다."),
	NOT_EXIST_TRADE_LOCATION(HttpStatus.BAD_REQUEST, "P-003", "배달 지역이 설정되지 않았습니다."),
	NOT_EXIST_DELIVERY_FEE(HttpStatus.BAD_REQUEST, "P-002", "택배비가 설정되지 않았습니다.");

	// 찜하기 기능

	ErrorCode(HttpStatus httpStatus, String errorCode, String message) {
		this.httpStatus = httpStatus;
		this.errorCode = errorCode;
		this.message = message;
	}

	private HttpStatus httpStatus;
	private String errorCode;
	private String message;
}
