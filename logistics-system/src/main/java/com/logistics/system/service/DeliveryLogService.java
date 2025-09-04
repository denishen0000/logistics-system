package com.logistics.system.service;

import com.logistics.system.entity.DeliveryLog;
import com.logistics.system.repository.DeliveryLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeliveryLogService {

    private final DeliveryLogRepository deliveryLogRepository;

    public DeliveryLogService(DeliveryLogRepository deliveryLogRepository) {
        this.deliveryLogRepository = deliveryLogRepository;
    }

    public List<DeliveryLog> getLogsByShipment(Long shipmentId) {
        return deliveryLogRepository.findByShipmentShipmentId(shipmentId);
    }

  
    public List<DeliveryLog> getLogsBetweenDates(LocalDate startDate, LocalDate endDate) {
        return deliveryLogRepository.findByDeliveryDateBetween(startDate, endDate);
    }

    public Long countLogsForShipment(Long shipmentId) {
        return deliveryLogRepository.countLogsByShipmentId(shipmentId);
    }

    public List<DeliveryLog> getLogsByDriver(Long driverId) {
        return deliveryLogRepository.findLogsByDriverId(driverId);
    }

    public List<DeliveryLog> getLogsOfDelayedShipments() {
        return deliveryLogRepository.findLogsOfDelayedShipments();
    }
}
