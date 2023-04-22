package com.warehouse.sales.model;

import java.time.Instant;

public record ProductResponse
        (  Long id,String product, Long quantity, Long price,
         String status,String code) {
}
