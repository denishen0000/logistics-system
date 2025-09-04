package com.logistics.system.service;

import com.logistics.system.entity.Driver;
import com.logistics.system.entity.Shipment;
import com.logistics.system.entity.Vehicle;
import com.logistics.system.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver getDriverByUserId(Long userId) {
        return driverRepository.findByUserUserId(userId);
    }

    public List<Shipment> getShipmentsByDriver(Long driverId) {
        return driverRepository.findShipmentsByDriverId(driverId);
    }

    public long getDriverWorkload(Long driverId) {
        return driverRepository.countShipmentsByDriverId(driverId);
    }

    public List<Driver> getBusyDrivers(int minShipments) {
        return driverRepository.findBusyDrivers(minShipments);
    }

    public List<Vehicle> getVehiclesByDriver(Long driverId) {
        return driverRepository.findVehiclesByDriverId(driverId);
    }

    public List<Driver> getDriversWithoutVehicles() {
        return driverRepository.findDriversWithoutVehicles();
    }
}
