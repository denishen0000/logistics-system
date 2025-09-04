package com.logistics.system.service;

import com.logistics.system.entity.Shipment;
import com.logistics.system.entity.ShipmentStatus;
import com.logistics.system.repository.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AnalyticsService {

    private final ShipmentRepository shipmentRepository;

    public AnalyticsService(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    // 1) Average delivery time per driver (hours)
    public Map<Long, Double> avgDeliveryTimePerDriver() {
        List<Shipment> deliveredShipments = shipmentRepository.findByStatus(ShipmentStatus.DELIVERED);

        return deliveredShipments.stream()
                .filter(s -> s.getPickupDate() != null && s.getDeliveryDate() != null && s.getAssignedDriver() != null)
                .collect(Collectors.groupingBy(
                        s -> s.getAssignedDriver().getDriverId(),
                        Collectors.averagingDouble(s ->
                                Duration.between(s.getPickupDate(), s.getDeliveryDate()).toMinutes() / 60.0
                        )
                ));
    }

    // 2) Delayed shipments per month
    public Map<Month, Long> delayedShipmentsPerMonth() {
        List<Shipment> delayedShipments = shipmentRepository.findByStatus(ShipmentStatus.DELAYED);

        return delayedShipments.stream()
                .filter(s -> s.getDeliveryDate() != null)
                .collect(Collectors.groupingBy(
                        s -> s.getDeliveryDate().getMonth(),
                        Collectors.counting()
                ));
    }

    // 3) Driver performance: count delivered vs delayed per driver
    public Map<Long, Map<ShipmentStatus, Long>> driverPerformance() {
        List<Shipment> allShipments = shipmentRepository.findAll();

        return allShipments.stream()
                .filter(s -> s.getAssignedDriver() != null)
                .collect(Collectors.groupingBy(
                        s -> s.getAssignedDriver().getDriverId(),
                        Collectors.groupingBy(
                                Shipment::getStatus,
                                Collectors.counting()
                        )
                ));
    }
}
