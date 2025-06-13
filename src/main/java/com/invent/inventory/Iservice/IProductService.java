package com.invent.inventory.Iservice;

import com.invent.inventory.entity.Product;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    void deleteById(Long id);

    Page<Product> list(int page, int size);

    List<Product> findLowStock();

    void transferStock(Long productId, Long toWarehouseId, int qty);

    void incrementStock(Long productId, Long warehouseId, int qty);

}