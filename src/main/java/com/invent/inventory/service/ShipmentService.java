package com.invent.inventory.service;

import com.invent.inventory.Iservice.IShipmentService;
import com.invent.inventory.entity.Shipment;
import com.invent.inventory.repository.ShipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentService implements IShipmentService {
    @Autowired
    private ShipmentRepository repo;
    public List<Shipment> findAll() { return repo.findAll(); }
    public Optional<Shipment> findById(Long id) { return repo.findById(id); }
    public Shipment save(Shipment shipment) { return repo.save(shipment); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
