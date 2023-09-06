package com.booklog.payment.domain.transaction.dto;

import java.time.LocalDateTime;

import com.booklog.payment.api.transaction.dto.TransactionApiDto;
import com.booklog.payment.domain.transaction.entity.Transaction;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionDto {
	private Long transactionId;
	private Long productId;
	private Long sellerId;
	private Long buyerId;
	private LocalDateTime transactionStartTime;
	private LocalDateTime transactionEndTime;
	private TransactionStatus transactionState;
	private Integer transactionPrice;

	@Builder
	public TransactionDto(Long transactionId, Long productId, Long sellerId, Long buyerId,
		LocalDateTime transactionStartTime, LocalDateTime transactionEndTime, TransactionStatus transactionState,
		Integer transactionPrice) {
		this.transactionId = transactionId;
		this.productId = productId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.transactionStartTime = transactionStartTime;
		this.transactionEndTime = transactionEndTime;
		this.transactionState = transactionState;
		this.transactionPrice = transactionPrice;
	}

	public static TransactionDto from(TransactionApiDto.Request request) {
		return TransactionDto.builder()
			.productId(request.getProductId())
			.sellerId(request.getSellerId())
			.buyerId(request.getBuyerId())
			.transactionPrice(request.getTransactionPrice())
			.build();
	}

	public static TransactionDto from(Transaction transaction) {
		return TransactionDto.builder()
			.transactionId(transaction.getTransactionId())
			.productId(transaction.getProductId())
			.sellerId(transaction.getSellerId())
			.buyerId(transaction.getBuyerId())
			.transactionStartTime(transaction.getTransactionStartTime())
			.transactionEndTime(transaction.getTransactionEndTime())
			.transactionState(transaction.getTransactionState())
			.transactionPrice(transaction.getTransactionPrice())
			.build();
	}
}
