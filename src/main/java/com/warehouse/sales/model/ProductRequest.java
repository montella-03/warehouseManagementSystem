package com.warehouse.sales.model;

public record ProductRequest
        (String product,String quantity,Long price,String status) {
}
