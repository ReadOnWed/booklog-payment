package com.booklog.payment.api.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.booklog.payment.api.product.dto.ProductApiDto;
import com.booklog.payment.domain.product.dto.ProductDto;
import com.booklog.payment.domain.product.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;

	@GetMapping("/{productId}")
	public ResponseEntity<ProductApiDto.Response> getProduct(@PathVariable Long productId) {
		ProductDto productDto = productService.getProduct(productId);

		return ResponseEntity.ok(ProductApiDto.Response.from(productDto));
	}

	@GetMapping
	public ResponseEntity<List<ProductApiDto.Response>> getProductList(
		@RequestParam(required = false, defaultValue = "0", value = "page") Integer pageNo,
		@RequestParam(required = false, defaultValue = "createdTime", value = "criteria") String criteria) {
		List<ProductDto> productDtoList = productService.getProductlist(pageNo, criteria);

		return ResponseEntity.ok(toProductsResponse(productDtoList));
	}

	@PostMapping("/regist")
	public ResponseEntity<ProductApiDto.Response> addProduct(@RequestBody ProductApiDto.Request request) {
		ProductDto productDto = productService.addProduct(ProductDto.from(request));

		return ResponseEntity.ok(ProductApiDto.Response.from(productDto));
	}

	@PatchMapping("/update")
	public ResponseEntity<ProductApiDto.Response> updateProduct(@RequestBody ProductApiDto.Request request) {
		ProductDto productDto = productService.updateProduct(ProductDto.from(request));

		return ResponseEntity.ok(ProductApiDto.Response.from(productDto));
	}

	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<?> removeProduct(@PathVariable Long productId) {
		productService.removeProduct(productId);

		return ResponseEntity.ok(null);
	}

	private static List<ProductApiDto.Response> toProductsResponse(List<ProductDto> productDtoList) {
		return productDtoList.stream()
			.map(ProductApiDto.Response::from)
			.toList();
	}

}
