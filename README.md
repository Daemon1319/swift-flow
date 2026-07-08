# SwiftFlow

A product caching REST API built with **Spring Boot 4** and **Caffeine**. Demonstrates the performance difference between direct database queries and in-process cache hits, with a built-in cache eviction endpoint.

## Key Features

- **Direct vs. Cached Endpoints** — Side-by-side routes (`/api/products/{id}` vs `/{id}/cached`) expose the raw DB latency vs. sub-millisecond Caffeine cache hits
- **Caffeine Cache** — In-process, near-zero-latency LRU cache backed by `@Cacheable` / `@CacheEvict`
- **Response Timing** — Every response includes elapsed milliseconds and a `cached` flag so you can measure the speedup
- **Data Seeder** — `DataSeeder` auto-populates the database on startup for instant demo-ready state
- **CORS Config** — `WebConfig` exposes the API for local frontend integration

## Tech Stack

Java 21 · Spring Boot 4 · Spring Cache · Caffeine · Spring Data JPA · PostgreSQL · Spring Actuator

## API Endpoints

| Method | Endpoint                      | Description                                      |
|--------|-------------------------------|--------------------------------------------------|
| GET    | `/api/products`               | List all products (id + name)                    |
| GET    | `/api/products/{id}`          | Fetch product directly from DB (no cache)        |
| GET    | `/api/products/{id}/cached`   | Fetch product from Caffeine cache (or DB on miss)|
| DELETE | `/api/products/cache`         | Evict all entries from the product cache         |
