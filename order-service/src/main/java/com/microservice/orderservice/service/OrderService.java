package com.microservice.orderservice.service;

import com.microservice.orderservice.dto.OrderLineItemDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.repository.Order;
import com.microservice.orderservice.repository.OrderLineItem;
import com.microservice.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void createOrder(OrderRequest orderRequest) {

        Order order = new Order();

        List<OrderLineItem> orderLineItems =    orderRequest.getOrderLineItems().stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);
        orderRepository.save(order);

    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {

        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setQuantity(orderLineItemDto.getQuantity());
        lineItem.setSkuCode(orderLineItemDto.getSkuCode());
        lineItem.setPrice(orderLineItemDto.getPrice());

        return lineItem;
    }
}
