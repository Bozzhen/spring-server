package com.portfolio.simplecrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.portfolio.simplecrud.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
}