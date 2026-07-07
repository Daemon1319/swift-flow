package com.allan.swift_flow.repository;

import com.allan.swift_flow.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
