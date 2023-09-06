package com.booklog.payment.domain.transaction.service;

import org.springframework.stereotype.Service;

import com.booklog.payment.domain.transaction.dto.TransactionDto;
import com.booklog.payment.domain.transaction.entity.Transaction;
import com.booklog.payment.domain.transaction.repository.TransactionRepository;
import com.booklog.payment.global.error.ErrorCode;
import com.booklog.payment.global.error.exception.BusinessException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

	private final TransactionRepository transactionRepository;

	@Override
	public TransactionDto saveTransaction(TransactionDto transactionDto) {
		Transaction transaction = transactionRepository.save(Transaction.from(transactionDto));

		return TransactionDto.from(transaction);
	}

	@Override
	public TransactionDto getTransaction(Long transactionId) {
		Transaction transaction = transactionRepository.findByTransactionId(transactionId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_TRANSACTION));

		return TransactionDto.from(transaction);
	}

	@Override
	public void removeTransaction(Long transactionId) {
		Transaction transaction = transactionRepository.findByTransactionId(transactionId)
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_TRANSACTION));

		transaction.updateStateToCancel();
	}

	@Override
	public TransactionDto updateTransaction(TransactionDto transactionDto) {
		Transaction transaction = transactionRepository.findByTransactionId(transactionDto.getTransactionId())
			.orElseThrow(() -> new BusinessException(ErrorCode.NOT_EXIST_TRANSACTION));

		return null;
	}
}
