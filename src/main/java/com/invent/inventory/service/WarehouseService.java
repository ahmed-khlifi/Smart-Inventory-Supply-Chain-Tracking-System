package com.invent.inventory.service;

import com.invent.inventory.Iservice.IWarehouseService;
import com.invent.inventory.entity.Warehouse;
import com.invent.inventory.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseService implements IWarehouseService {
    @Autowired
    private WarehouseRepository repo;

    public List<Warehouse> findAll() { return repo.findAll(); }
    public Optional<Warehouse> findById(Long id) { return repo.findById(id); }
    public Warehouse save(Warehouse warehouse) { return repo.save(warehouse); }
    public void deleteById(Long id) { repo.deleteById(id); }
}

