package com.invent.inventory.service;

import com.invent.inventory.Iservice.IShipmentItemService;
import com.invent.inventory.entity.ShipmentItem;
import com.invent.inventory.repository.ShipmentItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentItemService implements IShipmentItemService {
    @Autowired
    private ShipmentItemRepository repo;
    public List<ShipmentItem> findAll() { return repo.findAll(); }
    public Optional<ShipmentItem> findById(Long id) { return repo.findById(id); }
    public ShipmentItem save(ShipmentItem item) { return repo.save(item); }
    public void deleteById(Long id) { repo.deleteById(id); }
}
