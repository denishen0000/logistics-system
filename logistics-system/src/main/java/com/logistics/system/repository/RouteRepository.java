package com.logistics.system.repository;

import com.logistics.system.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {

    List<Route> findByShipmentShipmentId(Long shipmentId);

    @Query("SELECT COUNT(r) FROM Route r WHERE r.shipment.shipmentId = :shipmentId")
    long countRoutesByShipmentId(Long shipmentId);

    @Query("SELECT r FROM Route r JOIN r.shipment s WHERE s.assignedDriver.driverId = :driverId")
    List<Route> findRoutesByDriverId(Long driverId);

    @Query("SELECT r FROM Route r JOIN r.shipment s WHERE s.status = 'delayed'")
    List<Route> findRoutesOfDelayedShipments();
}
