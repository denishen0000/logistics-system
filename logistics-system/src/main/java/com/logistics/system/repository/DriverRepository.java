package com.logistics.system.repository;

import com.logistics.system.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver findByUserUserId(Long userId);

    @Query("SELECT s FROM Shipment s WHERE s.assignedDriver.driverId = :driverId")
    List<Shipment> findShipmentsByDriverId(Long driverId);

    @Query("SELECT COUNT(s) FROM Shipment s WHERE s.assignedDriver.driverId = :driverId")
    long countShipmentsByDriverId(Long driverId);

    @Query("SELECT d FROM Driver d JOIN d.shipments s WHERE s.status = 'in_transit' GROUP BY d HAVING COUNT(s) > :minShipments")
    List<Driver> findBusyDrivers(int minShipments);

    @Query("SELECT v FROM Driver d JOIN d.vehicles v WHERE d.driverId = :driverId")
    List<Vehicle> findVehiclesByDriverId(@Param("driverId") Long driverId);

    @Query("SELECT d FROM Driver d WHERE d.vehicles IS EMPTY")
    List<Driver> findDriversWithoutVehicles();
}
