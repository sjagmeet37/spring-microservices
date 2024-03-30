package com.microservice.orderservice.service;

import com.microservice.orderservice.dto.OrderLineItemDto;
import com.microservice.orderservice.dto.OrderRequest;
import com.microservice.orderservice.dto.OrderResponse;
import com.microservice.orderservice.repository.Order;
import com.microservice.orderservice.repository.OrderLineItem;
import com.microservice.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;


    public void createOrder(OrderRequest orderRequest) {

        Order order = new Order();

        List<OrderLineItem> orderLineItems = orderRequest.getOrderLineItems().stream()
                .map(this::mapToDto).toList();

        order.setOrderLineItems(orderLineItems);

        String uri = "";
        boolean inStock = webClient.get().uri("").retrieve().bodyToMono(Boolean.class).block();
        //check for inventory
        if(inStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product out of stock");
        }

    }

    private OrderLineItem mapToDto(OrderLineItemDto orderLineItemDto) {

        OrderLineItem lineItem = new OrderLineItem();
        lineItem.setQuantity(orderLineItemDto.getQuantity());
        lineItem.setSkuCode(orderLineItemDto.getSkuCode());
        lineItem.setPrice(orderLineItemDto.getPrice());

        return lineItem;
    }

    public List<OrderResponse> getAllOrders() {

        List<OrderResponse> orders = new LinkedList<>();

        List<Order> order = orderRepository.findAll();

        return order.stream().map(this::orderResponse).toList();


    }

    public List<OrderResponse> getOrderById(String orderId) {

        Optional<Order> order = orderRepository.findById(orderId);
        List<OrderResponse> orderResponses = new ArrayList<>();
        order.ifPresent(value -> orderResponses.add(this.orderResponse(value)));
        return orderResponses;
    }
    private OrderResponse orderResponse(Order o) {

        OrderResponse response = new OrderResponse();
        response.setOrderId(o.getOrderId());


        List<OrderLineItemDto> lineItemList = o.getOrderLineItems().stream().map(orderLineItem -> {
            OrderLineItemDto itemDto = new OrderLineItemDto();
            itemDto.setPrice(orderLineItem.getPrice());
            itemDto.setOrderId(orderLineItem.getOrderId());
            itemDto.setQuantity(orderLineItem.getQuantity());
            itemDto.setPrice(orderLineItem.getPrice());
            itemDto.setSkuCode(orderLineItem.getSkuCode());
            itemDto.setLineItemId(orderLineItem.getLineItemId());
            return itemDto;
        }).toList();

        response.setOrderLineItems(lineItemList);
        return response;

    }
}

