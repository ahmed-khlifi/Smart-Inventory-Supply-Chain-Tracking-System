package com.invent.inventory.repository;

import com.invent.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);

    Optional<Product> findBySkuAndWarehouseId(String sku, Long warehouseId);
}
