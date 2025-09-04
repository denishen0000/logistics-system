package com.logistics.system.service;

import com.logistics.system.entity.Shipment;
import com.logistics.system.entity.ShipmentStatus;
import com.logistics.system.entity.Driver;
import com.logistics.system.repository.ShipmentRepository;
import com.logistics.system.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

@Service
public class ShipmentService {

    private final ShipmentRepository shipmentRepository;
    private final DriverRepository driverRepository;

    public ShipmentService(ShipmentRepository shipmentRepository, DriverRepository driverRepository) {
        this.shipmentRepository = shipmentRepository;
        this.driverRepository = driverRepository;
    }

    public Shipment assignShipment(Long driverId, Long shipmentId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        shipment.setAssignedDriver(driver);
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipment(Long id, Map<String, Object> updates) {
        Shipment shipment = shipmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));

        if (updates.containsKey("weight")) {
            shipment.setWeight(Double.valueOf(updates.get("weight").toString()));
        }

        if (updates.containsKey("pickupDate") && updates.get("pickupDate") != null) {
            shipment.setPickupDate(parseToLocalDateTime(updates.get("pickupDate").toString()));
        }

        if (updates.containsKey("estimatedDelivery") && updates.get("estimatedDelivery") != null) {
            shipment.setEstimatedDelivery(parseToLocalDateTime(updates.get("estimatedDelivery").toString()));
        }

        if (updates.containsKey("deliveryDate") && updates.get("deliveryDate") != null) {
            shipment.setDeliveryDate(parseToLocalDateTime(updates.get("deliveryDate").toString()));
        }

        // Auto update status
        if (shipment.getDeliveryDate() != null) {
            shipment.setStatus(ShipmentStatus.DELIVERED);
        } else if (shipment.getEstimatedDelivery() != null &&
                shipment.getEstimatedDelivery().isBefore(LocalDateTime.now())) {
            shipment.setStatus(ShipmentStatus.DELAYED);
        } else if (shipment.getPickupDate() != null) {
            shipment.setStatus(ShipmentStatus.IN_TRANSIT);
        } else {
            shipment.setStatus(ShipmentStatus.PENDING);
        }

        return shipmentRepository.save(shipment);
    }


    private LocalDateTime parseToLocalDateTime(String dateStr) {
        try {
            if (dateStr.endsWith("Z") || dateStr.matches(".*[+-]\\d\\d:\\d\\d$")) {
                return OffsetDateTime.parse(dateStr).toLocalDateTime();
            }
            return LocalDateTime.parse(dateStr);
        } catch (Exception e) {
            throw new RuntimeException("Invalid date format: " + dateStr, e);
        }
    }

    public Shipment updateShipmentStatus(Long shipmentId, ShipmentStatus newStatus) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment.setStatus(newStatus);
        return shipmentRepository.save(shipment);
    }

    public Shipment updateShipmentWeight(Long shipmentId, Double newWeight) {
        Shipment shipment = shipmentRepository.findById(shipmentId)
                .orElseThrow(() -> new RuntimeException("Shipment not found"));
        shipment.setWeight(newWeight);
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getShipmentsByStatus(ShipmentStatus status) {
        List<Shipment> shipments = shipmentRepository.findByStatus(status);
        if (shipments.isEmpty()) {
            throw new RuntimeException("No shipments with this status");
        }
        return shipments;
    }

    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    public List<Shipment> getDelayedShipments() {
        return shipmentRepository.findDelayedShipments();
    }
}
