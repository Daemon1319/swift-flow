package com.allan.swift_flow.dto;

import com.allan.swift_flow.entity.Product;
import java.math.BigDecimal;

public record ProductSummary(
    Long id,
    String name,
    String category,
    BigDecimal price
) {

  public static ProductSummary from(Product product) {
    return new ProductSummary(
        product.getId(),
        product.getName(),
        product.getCategory(),
        product.getPrice()
    );
  }
}
