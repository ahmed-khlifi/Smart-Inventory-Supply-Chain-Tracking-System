package com.invent.inventory.Iservice;

import com.invent.inventory.entity.ShipmentItem;

import java.util.List;
import java.util.Optional;

public interface IShipmentItemService {
    List<ShipmentItem> findAll();
    Optional<ShipmentItem> findById(Long id);
    ShipmentItem save(ShipmentItem item);
    void deleteById(Long id);
}
