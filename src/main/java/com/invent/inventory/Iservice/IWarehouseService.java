package com.invent.inventory.Iservice;

import com.invent.inventory.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface IWarehouseService {
    List<Warehouse> findAll();
    Optional<Warehouse> findById(Long id);
    Warehouse save(Warehouse warehouse);
    void deleteById(Long id);
}
