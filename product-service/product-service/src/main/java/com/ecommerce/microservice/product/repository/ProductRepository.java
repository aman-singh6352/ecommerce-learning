package com.ecommerce.microservice.product.repository;

import com.ecommerce.microservice.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
