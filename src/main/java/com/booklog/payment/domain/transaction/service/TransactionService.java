package com.booklog.payment.domain.transaction.service;

import com.booklog.payment.domain.transaction.dto.TransactionDto;

public interface TransactionService {

	TransactionDto saveTransaction(TransactionDto transactionDto);

	TransactionDto getTransaction(Long transactionId);

	void removeTransaction(Long transactionId);

	TransactionDto updateTransaction(TransactionDto transactionDto);
}
