package com.invent.inventory.controller;

import com.invent.inventory.entity.Product;
import com.invent.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService service;

    // ——— Paginated list ———
    @GetMapping
    public Page<Product> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return service.list(page, size);
    }

    // ——— Low-stock summary ———
    @GetMapping("/low-stock")
    public List<Product> lowStock() {
        return service.findLowStock();
    }

    // ——— Transfer stock ———
    @PostMapping("/{id}/transfer")
    public ResponseEntity<?> transfer(
            @PathVariable Long id,
            @RequestParam Long toWarehouse,
            @RequestParam int qty) {
        service.transferStock(id, toWarehouse, qty);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product updated) {
        return service.findById(id).map(p -> {
            updated.setId(id);
            return ResponseEntity.ok(service.save(updated));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
