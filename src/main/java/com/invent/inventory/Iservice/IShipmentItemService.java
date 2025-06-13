package com.invent.inventory.Iservice;

import com.invent.inventory.entity.ShipmentItem;

import java.util.List;

public interface IShipmentItemService {
    /*
     * List<ShipmentItem> findAll();
     * 
     * Optional<ShipmentItem> findById(Long id);
     * 
     * ShipmentItem save(ShipmentItem item);
     * 
     * void deleteById(Long id);
     */
    ShipmentItem addItem(Long shipmentId, ShipmentItem item);

    List<ShipmentItem> listItems(Long shipmentId);
}
