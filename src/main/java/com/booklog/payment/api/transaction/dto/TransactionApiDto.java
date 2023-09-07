package com.booklog.payment.api.transaction.dto;

import java.time.LocalDateTime;

import com.booklog.payment.domain.transaction.dto.TransactionDto;
import com.booklog.payment.domain.transaction.dto.TransactionStatus;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Getter;

public class TransactionApiDto {

	@Getter
	@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
	public static class Request {
		private Long transactionId;
		private Long productId;
		private Long sellerId;
		private Long buyerId;
		private Integer transactionPrice;

		@Builder
		public Request(Long transactionId, Long productId, Long sellerId, Long buyerId, Integer transactionPrice) {
			this.transactionId = transactionId;
			this.productId = productId;
			this.sellerId = sellerId;
			this.buyerId = buyerId;
			this.transactionPrice = transactionPrice;
		}
	}

	public class Response {
		private Long transactionId;
		private Long productId;
		private Long sellerId;
		private Long buyerId;
		private LocalDateTime transactionStartTime;
		private LocalDateTime transactionEndTime;

		private TransactionStatus transactionState;
		private Integer transactionPrice;

		@Builder
		public Response(Long transactionId, Long productId, Long sellerId, Long buyerId,
			LocalDateTime transactionStartTime,
			LocalDateTime transactionEndTime, TransactionStatus transactionState, Integer transactionPrice) {
			this.transactionId = transactionId;
			this.productId = productId;
			this.sellerId = sellerId;
			this.buyerId = buyerId;
			this.transactionStartTime = transactionStartTime;
			this.transactionEndTime = transactionEndTime;
			this.transactionState = transactionState;
			this.transactionPrice = transactionPrice;
		}

		public static Response from(TransactionDto transactionDto) {
			return Response.builder()
				.transactionId(transactionDto.getTransactionId())
				.productId(transactionDto.getProductId())
				.sellerId(transactionDto.getSellerId())
				.buyerId(transactionDto.getBuyerId())
				.transactionStartTime(transactionDto.getTransactionStartTime())
				.transactionEndTime(transactionDto.getTransactionEndTime())
				.transactionState(transactionDto.getTransactionState())
				.transactionPrice(transactionDto.getTransactionPrice())
				.build();
		}
	}
}
