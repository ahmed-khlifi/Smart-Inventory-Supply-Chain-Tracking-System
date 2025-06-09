package com.invent.inventory.repository;

import com.invent.inventory.entity.ShipmentItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentItemRepository extends JpaRepository<ShipmentItem, Long> {}
