package com.logistics.system.repository;

import com.logistics.system.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;


@Repository
public interface DeliveryLogRepository extends JpaRepository<DeliveryLog, Long> {

    List<DeliveryLog> findByShipmentShipmentId(Long shipmentId);

    List<DeliveryLog> findByDeliveryDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT COUNT(d) FROM DeliveryLog d WHERE d.shipment.shipmentId = :shipmentId")
    Long countLogsByShipmentId(Long shipmentId);

    @Query("SELECT d FROM DeliveryLog d JOIN d.shipment s WHERE s.assignedDriver.driverId = :driverId")
    List<DeliveryLog> findLogsByDriverId(Long driverId);

    @Query("SELECT d FROM DeliveryLog d JOIN d.shipment s WHERE s.status = 'delayed'")
    List<DeliveryLog> findLogsOfDelayedShipments();
}
