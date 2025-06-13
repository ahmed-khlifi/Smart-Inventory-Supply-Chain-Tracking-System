package com.invent.inventory.Iservice;

import com.invent.inventory.entity.Shipment;

import java.util.List;
import java.util.Optional;

public interface IShipmentService {
    List<Shipment> findAll();

    Optional<Shipment> findById(Long id);

    Shipment save(Shipment shipment);

    void deleteById(Long id);

    Shipment receiveShipment(Long shipmentId);
}
