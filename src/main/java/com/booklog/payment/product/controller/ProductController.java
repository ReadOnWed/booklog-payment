package com.booklog.payment.product.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.booklog.payment.product.domain.ProductDto;
import com.booklog.payment.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	// Rest API 구현부

	@PostMapping("/regist")
	public ResponseEntity<String> regist(@RequestBody ProductDto productDto) {
		if (productService.registProduct(productDto))
			return new ResponseEntity<>("제품이 성공적으로 등록되었습니다.", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>("제품 등록을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@DeleteMapping("/delete/{productNum}")
	public ResponseEntity<String> remove(@PathVariable long productNum) {
		if (productService.removeProduct(productNum))
			return new ResponseEntity<>("제품이 성공적으로 삭제되었습니다.", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>("제품 삭제가 실패되었습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("/update")
	public ResponseEntity<String> update(@RequestBody ProductDto productDto) {
		if (productService.updateProduct(productDto))
			return new ResponseEntity<>("제품이 성공적으로 수정되었습니다.", HttpStatus.ACCEPTED);
		else
			return new ResponseEntity<>("제품 수정을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
