package com.ecommerce.microservice.order.repository;

import com.ecommerce.microservice.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
