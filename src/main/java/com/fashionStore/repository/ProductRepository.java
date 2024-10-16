package com.fashionStore.repository;

import com.fashionStore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Find products by category
    List<Product> findByCategory(String category);

    // Search for products by partial name (case-insensitive)
    List<Product> findByNameContainingIgnoreCase(String name);

    // Find products within a price range
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    // Find products by brand
    List<Product> findByBrand(String brand);

    // Find products by color
    List<Product> findByColor(String color);

    // Find products by size
    List<Product> findBySize(String size);

    // Find products that are currently discounted
    List<Product> findByDiscountPriceIsNotNull();

    // Find products with low stock (threshold can be passed as a parameter)
    List<Product> findByStockQuantityLessThan(Integer threshold);

    // Find products sorted by price in ascending order
    List<Product> findAllByOrderByPriceAsc();

    // Find products sorted by price in descending order
    List<Product> findAllByOrderByPriceDesc();

    // Optional: Find a product by its SKU
    Optional<Product> findBySku(String sku);

    // Find products added/updated in the last 'n' days
    List<Product> findByCreatedAtAfter(java.util.Date date);

    // Find all products with pagination and sorting
    // Note: This requires Spring's Pageable object for pagination
    //List<Product> findByCategory(String category, org.springframework.data.domain.Pageable pageable);
}

