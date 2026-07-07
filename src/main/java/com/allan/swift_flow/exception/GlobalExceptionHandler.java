package com.allan.swift_flow.exception;

import java.net.URI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Maps domain exceptions to RFC 7807 ProblemDetail responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ProductNotFoundException.class)
  ProblemDetail handleProductNotFound(ProductNotFoundException ex) {
    return problemDetail(HttpStatus.NOT_FOUND, ex.getMessage(), "product-not-found");
  }

  @ExceptionHandler(Exception.class)
  ProblemDetail handleUnexpected(Exception ex) {
    return problemDetail(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred", "internal-error");
  }

  private ProblemDetail problemDetail(HttpStatus status, String detail, String typeSuffix) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(status, detail);
    problemDetail.setType(URI.create("https://swift-flow.internal/errors/" + typeSuffix));
    return problemDetail;
  }
}