package com.invent.inventory.service;

import com.invent.inventory.Iservice.IShipmentItemService;
import com.invent.inventory.entity.Product;
import com.invent.inventory.entity.Shipment;
import com.invent.inventory.entity.ShipmentItem;
import com.invent.inventory.repository.ProductRepository;
import com.invent.inventory.repository.ShipmentItemRepository;
import com.invent.inventory.repository.ShipmentRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipmentItemService implements IShipmentItemService {
    private final ShipmentItemRepository itemRepo;
    private final ShipmentRepository shipmentRepo;
    private final ProductRepository productRepo;

    public ShipmentItemService(ShipmentItemRepository itemRepo, ShipmentRepository shipmentRepo,
            ProductRepository productRepo) {
        this.itemRepo = itemRepo;
        this.shipmentRepo = shipmentRepo;
        this.productRepo = productRepo;
    }

    @Override
    public ShipmentItem addItem(Long shipmentId, ShipmentItem item) {
        Shipment shipment = shipmentRepo.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found"));
        Product product = productRepo.findById(item.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found"));

        item.setShipment(shipment);
        item.setProduct(product);
        return itemRepo.save(item);
    }

    @Override
    public List<ShipmentItem> listItems(Long shipmentId) {
        // verify shipment exists
        if (!shipmentRepo.existsById(shipmentId)) {
            throw new EntityNotFoundException("Shipment not found");
        }
        return itemRepo.findByShipmentId(shipmentId);
    }
}
