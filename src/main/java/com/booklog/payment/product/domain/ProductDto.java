package com.booklog.payment.product.domain;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long sellerId;
    private long bookId;
    private String name;
    private int price;
    private int condition;
    private int deliveryPoss;
    private int tradeStatus;
    private String description;

    // nullable Column
    private int deliveryFee = -1;
    private String tradeLocation = null;
}
