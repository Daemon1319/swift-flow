package com.allan.swift_flow.service;

import com.allan.swift_flow.dto.ProductSummary;
import com.allan.swift_flow.entity.Product;
import com.allan.swift_flow.exception.ProductNotFoundException;
import com.allan.swift_flow.repository.ProductRepository;
import java.util.List;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private final ProductRepository repository;

  public ProductService(ProductRepository repository) {
    this.repository = repository;
  }

  /** Returns all products for the picker dropdown. */
  public List<ProductSummary> listAll() {
    return repository.findAll().stream()
        .map(ProductSummary::from)
        .toList();
  }

  /** Hits the database every single time — no caching. */
  public Product getProductDirect(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
  }

  /** Hits the database only on cache miss — subsequent calls return from Caffeine. */
  @Cacheable(cacheNames = "products", key = "#id")
  public Product getProductCached(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ProductNotFoundException(id));
  }

  /** Evicts all entries from the product cache. */
  @CacheEvict(cacheNames = "products", allEntries = true)
  public void clearCache() {
    // Spring handles the eviction via the annotation.
  }
}