package com.booklog.payment.api.transaction.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booklog.payment.api.transaction.dto.TransactionApiDto;
import com.booklog.payment.domain.transaction.dto.TransactionDto;
import com.booklog.payment.domain.transaction.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

	private final TransactionService transactionService;

	@GetMapping("/{transactionId}")
	public ResponseEntity<TransactionApiDto.Response> getTransaction(@PathVariable Long transactionId) {
		TransactionDto transactionDto = transactionService.getTransaction(transactionId);

		return ResponseEntity.ok(TransactionApiDto.Response.from(transactionDto));
	}

	@PostMapping
	public ResponseEntity<TransactionApiDto.Response> openTransaction(@RequestBody TransactionApiDto.Request request) {
		TransactionDto transactionDto = transactionService.saveTransaction(TransactionDto.from(request));

		return ResponseEntity.ok(TransactionApiDto.Response.from(transactionDto));
	}

	@DeleteMapping("/{transactionId}")
	public ResponseEntity<?> cancelTransaction(@PathVariable Long transactionId) {
		transactionService.removeTransaction(transactionId);

		return ResponseEntity.ok(null);
	}

	// @PatchMapping
	// public ResponseEntity<TransactionApiDto.Response> editTransaction(@RequestBody TransactionApiDto.Request request) {
	// 	transactionService.updateTransaction(TransactionDto.from(request));
	// }
}
