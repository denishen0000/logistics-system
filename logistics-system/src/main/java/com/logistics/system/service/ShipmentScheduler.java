package com.logistics.system.service;

import com.logistics.system.entity.Shipment;
import com.logistics.system.entity.ShipmentStatus;
import com.logistics.system.repository.ShipmentRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ShipmentScheduler {

    private final ShipmentRepository shipmentRepository;

    public ShipmentScheduler(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }

    @Scheduled(cron = "0 0 0 * * ?") // runs every day at midnight
    public void markDelayedShipments() {
        List<Shipment> inTransit = shipmentRepository.findByStatus(ShipmentStatus.IN_TRANSIT);

        for (Shipment s : inTransit) {
            if (s.getEstimatedDelivery() != null &&
                s.getEstimatedDelivery().isBefore(LocalDateTime.now())) {
                s.setStatus(ShipmentStatus.DELAYED);
            }
        }
        shipmentRepository.saveAll(inTransit);
    }
}
