package com.allan.swift_flow.config;

import com.allan.swift_flow.entity.Product;
import com.allan.swift_flow.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

  private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

  private final ProductRepository repository;

  public DataSeeder(ProductRepository repository) {
    this.repository = repository;
  }

  @Override
  public void run(String... args) {
    if (repository.count() > 0) {
      log.info("Products already seeded, skipping.");
      return;
    }

    List<Product> products = createProducts();
    repository.saveAll(products);
    log.info("Seeded {} products.", products.size());
  }

  private Product p(String name, String category, String description, String price) {
    return new Product(name, category, description, new BigDecimal(price));
  }

  private List<Product> createProducts() {
    return List.of(
        p("Keychron K2", "Keyboard", "Wireless mechanical keyboard", "79.99"),
        p("Logitech MX Keys", "Keyboard", "Wireless illuminated keyboard", "99.99"),
        p("Razer BlackWidow", "Keyboard", "Mechanical gaming keyboard", "119.99"),
        p("Corsair K70", "Keyboard", "RGB mechanical keyboard", "159.99"),
        p("NuPhy Air75", "Keyboard", "Low-profile mechanical keyboard", "109.99"),
        p("Ducky One 2 Mini", "Keyboard", "60% mechanical keyboard", "99.00"),
        p("Anne Pro 2", "Keyboard", "Wireless 60% keyboard", "89.00"),
        p("Wooting 60HE", "Keyboard", "Analog mechanical keyboard", "174.99"),
        p("SteelSeries Apex Pro", "Keyboard", "Adjustable mechanical keyboard", "199.99"),
        p("Royal Kludge RK61", "Keyboard", "Budget 60% wireless keyboard", "49.99")
    );
  }
}
