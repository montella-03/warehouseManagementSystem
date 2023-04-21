package com.warehouse.sales.model;

import java.time.Instant;

public record ProductResponse
        (String product, String quantity, String quality,
         String handler, Instant created,int id,String code) {
}
