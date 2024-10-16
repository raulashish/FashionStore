package com.fashionStore.service;

import com.fashionStore.model.Product;
import com.fashionStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Get all products
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Get product by ID with error handling
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    // Save or update a product
    @Transactional
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    // Delete a product by ID with validation
    @Transactional
    public void deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    // Custom: Get products by category
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    // Custom: Search products by name (case-insensitive)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // Custom: Get products by price range
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    // Custom: Get products by brand
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    // Custom: Get products by color
    public List<Product> getProductsByColor(String color) {
        return productRepository.findByColor(color);
    }

    // Custom: Get products by size
    public List<Product> getProductsBySize(String size) {
        return productRepository.findBySize(size);
    }

    // Custom: Get discounted products
    public List<Product> getDiscountedProducts() {
        return productRepository.findByDiscountPriceIsNotNull();
    }

    // Custom: Get products with low stock (below a threshold)
    public List<Product> getLowStockProducts(Integer threshold) {
        return productRepository.findByStockQuantityLessThan(threshold);
    }

    // Custom: Get products sorted by price in ascending order
    public List<Product> getProductsSortedByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    // Custom: Get products sorted by price in descending order
    public List<Product> getProductsSortedByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }

    // Custom: Find product by SKU
    public Optional<Product> getProductBySku(String sku) {
        return productRepository.findBySku(sku);
    }

    // Custom: Get products added in the last 'n' days
    public List<Product> getProductsAddedInLastDays(int days) {
        Date recentDate = Date.from(Instant.now().minus(days, ChronoUnit.DAYS));
        return productRepository.findByCreatedAtAfter(recentDate);
    }

    // Custom: Get products with pagination (by category)
    //public List<Product> getProductsByCategoryWithPagination(String category, int page, int size, String sortBy, boolean asc) {
      //  Sort sort = asc ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //Pageable pageable = PageRequest.of(page, size, sort);
       // return productRepository.findByCategory(category, pageable);}
}
