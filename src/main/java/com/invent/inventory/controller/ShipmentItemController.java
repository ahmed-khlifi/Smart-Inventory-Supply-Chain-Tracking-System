package com.invent.inventory.controller;

import com.invent.inventory.entity.ShipmentItem;
import com.invent.inventory.service.ShipmentItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipment-items")
public class ShipmentItemController {
    @Autowired
    private ShipmentItemService service;

    @GetMapping
    public List<ShipmentItem> list(@PathVariable Long shipmentId) {
        return service.listItems(shipmentId);
    }

    @PostMapping
    public ShipmentItem add(@PathVariable Long shipmentId,
            @RequestBody ShipmentItem item) {
        return service.addItem(shipmentId, item);
    }
}