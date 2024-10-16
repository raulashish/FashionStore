package com.fashionStore.model;

import java.util.Date;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Description is mandatory")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    @Column(nullable = false, length = 500)
    private String description;

    @NotNull(message = "Price is mandatory")
    @Column(nullable = false)
    private Double price;

    @Size(max = 255, message = "Image URL cannot exceed 255 characters")
    private String imageUrl;

    @NotBlank(message = "Category is mandatory")
    private String category;

    private String brand;

    private String size;

    private String color;

    @NotNull(message = "Stock quantity is mandatory")
    private Integer stockQuantity;

    private Double discountPrice;

    //Stock Keeping unit
    private String sku;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

}
