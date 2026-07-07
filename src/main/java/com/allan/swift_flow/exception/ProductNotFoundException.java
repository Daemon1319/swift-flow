package com.allan.swift_flow.exception;

public class ProductNotFoundException extends RuntimeException {

  public ProductNotFoundException(Long id) {
    super("Product not found: " + id);
  }
}
