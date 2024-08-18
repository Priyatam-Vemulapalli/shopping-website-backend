package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.requestBody;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProductRequestBody {
    private String productName;
    private int quantity;
}
