package com.microservice.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDto {

    private String lineItemId;
    private String skuCode;
    private String orderId;
    private Integer quantity;
    private BigDecimal price;
}
