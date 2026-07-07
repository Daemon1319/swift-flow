package com.allan.swift_flow.controller;

import com.allan.swift_flow.dto.ProductResponse;
import com.allan.swift_flow.dto.ProductSummary;
import com.allan.swift_flow.entity.Product;
import com.allan.swift_flow.service.ProductService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

  private final ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  /** Lists all available products (for the frontend picker). */
  @GetMapping
  List<ProductSummary> listAll() {
    return productService.listAll();
  }

  /** Direct database lookup — no caching, every call hits the DB. */
  @GetMapping("/{id}")
  ProductResponse getDirect(@PathVariable Long id) {
    long start = System.nanoTime();
    Product product = productService.getProductDirect(id);
    double elapsedMs = (System.nanoTime() - start) / 1_000_000.0;
    return ProductResponse.from(product, elapsedMs, false);
  }

  /** Cached lookup — first call hits DB, subsequent calls return from Caffeine. */
  @GetMapping("/{id}/cached")
  ProductResponse getCached(@PathVariable Long id) {
    long start = System.nanoTime();
    Product product = productService.getProductCached(id);
    double elapsedMs = (System.nanoTime() - start) / 1_000_000.0;
    return ProductResponse.from(product, elapsedMs, true);
  }

  /** Clears the product cache so the next cached call is a fresh miss. */
  @DeleteMapping("/cache")
  void clearCache() {
    productService.clearCache();
  }
}