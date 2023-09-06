package com.booklog.payment.domain.transaction.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.booklog.payment.domain.transaction.dto.TransactionDto;
import com.booklog.payment.domain.transaction.dto.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long transactionId;

	@Column(nullable = false)
	private Long productId;

	@Column(nullable = false)
	private Long sellerId;

	@Column(nullable = false)
	private Long buyerId;

	@CreatedDate
	private LocalDateTime transactionStartTime;

	private LocalDateTime transactionEndTime;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionState;

	@Column(nullable = false)
	private Integer transactionPrice;

	@Builder
	public Transaction(Long productId, Long sellerId, Long buyerId,
		TransactionStatus transactionState,
		Integer transactionPrice) {
		this.productId = productId;
		this.sellerId = sellerId;
		this.buyerId = buyerId;
		this.transactionState = transactionState;
		this.transactionPrice = transactionPrice;
	}

	public static Transaction from(TransactionDto transactionDto) {
		return Transaction.builder()
			.productId(transactionDto.getProductId())
			.sellerId(transactionDto.getSellerId())
			.buyerId(transactionDto.getBuyerId())
			.transactionState(TransactionStatus.DOING)
			.transactionPrice(transactionDto.getTransactionPrice())
			.build();
	}

	public void updateStateToCancel() {
		this.transactionState = TransactionStatus.CANCEL;
		this.transactionEndTime = LocalDateTime.now();
	}
}
