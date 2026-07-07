package com.allan.swift_flow.dto;

import com.allan.swift_flow.entity.Product;
import java.math.BigDecimal;

public record ProductResponse(
    Long id,
    String name,
    String category,
    BigDecimal price,
    String description,
    double responseTimeMs,
    boolean cached
) {

  public static ProductResponse from(Product product, double responseTimeMs, boolean cached) {
    return new ProductResponse(
        product.getId(),
        product.getName(),
        product.getCategory(),
        product.getPrice(),
        product.getDescription(),
        responseTimeMs,
        cached
    );
  }
}