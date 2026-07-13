package com.ecommerce.microservice.order.service;

import com.ecommerce.microservice.order.ddo.OrderRequest;
import com.ecommerce.microservice.order.model.Order;
import com.ecommerce.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderRequest orderRequest){
        // map order request to Order object
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setPrice(orderRequest.price());
        order.setSkuCode(orderRequest.skuCode());
        order.setQuantity(orderRequest.quantity());
        // save order to repository
        orderRepository.save(order);
    }
}
