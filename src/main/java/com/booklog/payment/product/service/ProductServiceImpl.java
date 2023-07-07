package com.booklog.payment.product.service;

import com.booklog.payment.product.domain.ProductDto;
import com.booklog.payment.product.entity.Product;
import com.booklog.payment.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean registProduct(ProductDto productDto) {
        // 유효성 검사

        // 택배가 가능 하고 배송비가 올바르게 설정된 경우
        // 택배가 불가능할때 직거래 지역이 설정된 경우
        if ((productDto.getDeliveryPoss() == 1 && productDto.getDeliveryFee() != -1)
        || (productDto.getDeliveryPoss() == 0 && productDto.getTradeLocation() != null)) {
            Product product = Product.builder()
                    .sellerId(productDto.getSellerId())
                    .bookId(productDto.getBookId())
                    .name(productDto.getName())
                    .price(productDto.getPrice())
                    .condition(productDto.getCondition())
                    .deliveryPoss(productDto.getDeliveryPoss())
                    .deliveryFee(productDto.getDeliveryFee())
                    .tradeLocation(productDto.getTradeLocation())
                    .tradeStatus(productDto.getTradeStatus())
                    .description(productDto.getDescription())
                    .build();

            productRepository.save(product);
            return true;
        }

        return false;
    }
}
