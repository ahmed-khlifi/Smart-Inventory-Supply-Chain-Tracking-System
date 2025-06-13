package com.invent.inventory.service;

import com.invent.inventory.Iservice.IProductService;
import com.invent.inventory.entity.Product;
import com.invent.inventory.entity.Warehouse;
import com.invent.inventory.repository.ProductRepository;
import com.invent.inventory.repository.WarehouseRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {
    @Autowired
    private ProductRepository repo;
    @Autowired
    WarehouseRepository warehouseRepo;

    public List<Product> findAll() {
        return repo.findAll();
    }

    public Optional<Product> findById(Long id) {
        return repo.findById(id);
    }

    public Product save(Product product) {
        return repo.save(product);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Page<Product> list(int page, int size) {
        return repo.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Product> findLowStock() {
        return repo.findAll()
                .stream()
                .filter(p -> p.getQuantity() < p.getReorderThreshold())
                .collect(Collectors.toList());
    }

    @Override
    public void transferStock(Long productId, Long toWarehouseId, int qty) {
        Product p = repo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        Warehouse dest = warehouseRepo.findById(toWarehouseId)
                .orElseThrow(() -> new EntityNotFoundException("Warehouse not found"));
        if (p.getQuantity() < qty) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        p.setQuantity(p.getQuantity() - qty);
        repo.save(p);

        // create or update a new product entry in the destination warehouse
        Optional<Product> maybe = repo.findBySkuAndWarehouseId(p.getSku(), toWarehouseId);
        Product target = maybe.orElse(
                Product.builder()
                        .name(p.getName())
                        .sku(p.getSku())
                        .unitPrice(p.getUnitPrice())
                        .quantity(0)
                        .reorderThreshold(p.getReorderThreshold())
                        .warehouse(dest)
                        .build());
        target.setQuantity(target.getQuantity() + qty);
        repo.save(target);
    }

    @Override
    public void incrementStock(Long productId, Long warehouseId, int qty) {
        Product p = repo.findById(productId)
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));
        // if you track warehouse per product:
        if (!p.getWarehouse().getId().equals(warehouseId)) {
            throw new IllegalArgumentException("Product does not belong to warehouse");
        }
        p.setQuantity(p.getQuantity() + qty);
        repo.save(p);
    }
}