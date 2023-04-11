package com.warehouse.warehouseManagementSystem.Repository;

import com.warehouse.warehouseManagementSystem.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
}
