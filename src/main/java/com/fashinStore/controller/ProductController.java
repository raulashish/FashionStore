package com.fashinStore.controller;

import com.fashionStore.model.Product;
import com.fashionStore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveOrUpdateProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        try {
            product.setId(id); // Ensure the ID from the path is set on the product object
            Product updatedProduct = productService.saveOrUpdateProduct(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Custom: Get products by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductsByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductsByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Custom: Search products by name
    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProductsByName(@RequestParam String name) {
        List<Product> products = productService.searchProductsByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Custom: Get products by price range
    @GetMapping("/price-range")
    public ResponseEntity<List<Product>> getProductsByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<Product> products = productService.getProductsByPriceRange(minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Custom: Get discounted products
    @GetMapping("/discounted")
    public ResponseEntity<List<Product>> getDiscountedProducts() {
        List<Product> products = productService.getDiscountedProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Custom: Get low stock products
    @GetMapping("/low-stock")
    public ResponseEntity<List<Product>> getLowStockProducts(@RequestParam Integer threshold) {
        List<Product> products = productService.getLowStockProducts(threshold);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Custom: Get products sorted by price
    @GetMapping("/sort-by-price")
    public ResponseEntity<List<Product>> getProductsSortedByPrice(@RequestParam(defaultValue = "asc") String sort) {
        List<Product> products = sort.equalsIgnoreCase("asc") ?
                productService.getProductsSortedByPriceAsc() :
                productService.getProductsSortedByPriceDesc();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Custom: Get products added in the last 'n' days
    @GetMapping("/recent")
    public ResponseEntity<List<Product>> getRecentProducts(@RequestParam int days) {
        List<Product> products = productService.getProductsAddedInLastDays(days);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Uncomment this method if you want to enable pagination for products by category
    /*
    @GetMapping("/category/{category}/page")
    public ResponseEntity<List<Product>> getProductsByCategoryWithPagination(
            @PathVariable String category,
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "price") String sortBy,
            @RequestParam(defaultValue = "asc") String sort) {
        List<Product> products = productService.getProductsByCategoryWithPagination(category, page, size, sortBy, sort.equalsIgnoreCase("asc"));
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    */
}
