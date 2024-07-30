package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private UUID id;
    @Column(nullable = false,unique = true)

    private String productName;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)
    private int quantity;
    private double rating;
    @Column(nullable = false)
    private int totalSoldQuantity;
    @Column(nullable = false)
    private String productCategory;
    @ManyToOne
    AppUser seller;
}
