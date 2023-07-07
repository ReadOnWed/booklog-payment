package com.booklog.payment.product.controller;

import com.booklog.payment.product.domain.ProductDto;
import com.booklog.payment.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    /**
     * 생성자 의존성 주입 부분
     * @param productService
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Rest API 구현부

    @PostMapping("/regist")
    public ResponseEntity<String> regist(@RequestBody ProductDto productDto) {
        if (productService.registProduct(productDto))
            return new ResponseEntity<>("제품이 성공적으로 등록되었습니다.", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("제품 등록을 실패했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
