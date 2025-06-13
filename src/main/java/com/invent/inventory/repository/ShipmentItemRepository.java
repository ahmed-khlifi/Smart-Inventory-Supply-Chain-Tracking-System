package com.invent.inventory.repository;

import com.invent.inventory.entity.ShipmentItem;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {
    List<ShipmentItem> findByShipmentId(Long shipmentId);
}
