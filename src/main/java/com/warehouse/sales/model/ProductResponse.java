package com.warehouse.sales.model;

import java.time.Instant;

public record ProductResponse
        (String product, String quantity, Long price,
         String status,Long productId,String code) {
}
