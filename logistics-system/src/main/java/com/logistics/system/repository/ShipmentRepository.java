package com.logistics.system.repository;

import com.logistics.system.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
   List<Shipment> findByAssignedDriverDriverId(Long driverId);
   List<Shipment> findByStatus(ShipmentStatus status);
   List<Shipment> findByAssignedDriver(Driver driver);
   List<Shipment> findByStatusAndAssignedDriverDriverId(ShipmentStatus status, Long driverId);
   List<Shipment> findAll();

   @Query("SELECT s.status, COUNT(s) FROM Shipment s GROUP BY s.status")
    List<Object[]> countShipmentsByStatus();

   @Query("SELECT s.assignedDriver.driverId, COUNT(s) FROM Shipment s GROUP BY s.assignedDriver.driverId")
   List<Object[]> getShipmentCountPerDriver();

   @Query(value = "SELECT * FROM shipments s WHERE s.delivery_date >= CURRENT_DATE - INTERVAL '7 days'", nativeQuery = true)
   List<Shipment> findShipmentsDeliveredLast7Days();

   @Query("SELECT s.assignedDriver.driverId, COUNT(s) as cnt FROM Shipment s GROUP BY s.assignedDriver.driverId ORDER BY cnt DESC")
   List<Object[]> findTopDrivers(Pageable pageable);

   @Query("SELECT s FROM Shipment s")
   List<Shipment> getAllShipments();

   @Query("SELECT s FROM Shipment s WHERE s.deliveryDate < CURRENT_DATE AND s.status <> 'DELIVERED'")
   List<Shipment> findDelayedShipments();

   @Query("SELECT COUNT(s) FROM Shipment s WHERE s.deliveryDate < CURRENT_DATE AND s.status <> 'DELIVERED'")
   Long countDelayedShipments();

   @Query("SELECT s.assignedDriver.driverId, SUM(s.weight) FROM Shipment s GROUP BY s.assignedDriver.driverId")
   List<Object[]> getTotalWeightPerDriver();

    }
