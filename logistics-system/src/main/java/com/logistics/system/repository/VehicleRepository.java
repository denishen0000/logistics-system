package com.logistics.system.repository;

import com.logistics.system.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> findByDriverDriverId(Long driverId);

    @Query("SELECT COUNT(v) FROM Vehicle v WHERE v.driver.driverId = :driverId")
    long countVehiclesByDriverId(Long driverId);

    @Query("SELECT v FROM Vehicle v WHERE v.driver IN " +
           "(SELECT d FROM Driver d JOIN d.vehicles dv GROUP BY d HAVING COUNT(dv) > :minVehicles)")
    List<Vehicle> findVehiclesOfBusyDrivers(int minVehicles);

    @Query("SELECT v FROM Vehicle v WHERE v.driver.shipments IS EMPTY")
    List<Vehicle> findVehiclesOfDriversWithoutShipments();
}
