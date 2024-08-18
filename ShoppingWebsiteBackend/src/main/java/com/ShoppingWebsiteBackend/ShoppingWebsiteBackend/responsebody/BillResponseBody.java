package com.ShoppingWebsiteBackend.ShoppingWebsiteBackend.responsebody;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BillResponseBody {
    private List<SpecificProductOrderDetail> products;
    private int totalAmount;
}
