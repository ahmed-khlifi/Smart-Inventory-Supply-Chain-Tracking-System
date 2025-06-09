package com.invent.inventory.controller;

import com.invent.inventory.entity.ShipmentItem;
import com.invent.inventory.service.ShipmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment-items")
public class ShipmentItemController {
    @Autowired
    private ShipmentItemService service;
    @GetMapping
    public List<ShipmentItem> getAll() { return service.findAll(); }
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentItem> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ShipmentItem create(@RequestBody ShipmentItem item) { return service.save(item); }
    @PutMapping("/{id}")
    public ResponseEntity<ShipmentItem> update(@PathVariable Long id, @RequestBody ShipmentItem updated) {
        return service.findById(id).map(i -> {
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