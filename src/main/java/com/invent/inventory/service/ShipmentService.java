package com.invent.inventory.service;

import com.invent.inventory.Iservice.IShipmentService;
import com.invent.inventory.entity.Shipment;
import com.invent.inventory.entity.ShipmentItem;
import com.invent.inventory.entity.ShipmentStatus;
import com.invent.inventory.repository.ShipmentRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService implements IShipmentService {
    @Autowired
    private ShipmentRepository repo;
    @Autowired
    private ProductService productService;

    public List<Shipment> findAll() {
        return repo.findAll();
    }

    public Optional<Shipment> findById(Long id) {
        return repo.findById(id);
    }

    public Shipment save(Shipment shipment) {
        return repo.save(shipment);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    /**
     * Receive a shipment by marking it as delivered and updating stock
     */
    @Override
    public Shipment receiveShipment(Long shipmentId) {
        Shipment shp = repo.findById(shipmentId)
                .orElseThrow(() -> new EntityNotFoundException("Shipment not found"));

        if (!ShipmentStatus.DELIVERED.equals(shp.getStatus())) {
            shp.setStatus(ShipmentStatus.DELIVERED);
            repo.save(shp);
        }

        // load items (ensure items collection is fetched)
        List<ShipmentItem> items = shp.getItems();
        items.forEach(item -> {
            // note: Shipment entity should have a warehouse field for destination
            Long warehouseId = shp.getWarehouse().getId();
            productService.incrementStock(item.getProduct().getId(), warehouseId, item.getQuantity());
        });

        return shp;
    }
}
