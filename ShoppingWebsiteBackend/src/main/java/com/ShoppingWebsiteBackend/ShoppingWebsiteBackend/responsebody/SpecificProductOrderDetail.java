package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SpecificProductOrderDetail {
    private UUID productID;
    private String productName;
    private int unitPrice;
    private int totalPurchasedPrice;
}
