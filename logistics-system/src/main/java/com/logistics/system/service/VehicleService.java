package com.logistics.system.service;

import com.logistics.system.entity.Vehicle;
import com.logistics.system.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getVehiclesByDriver(Long driverId) {
        return vehicleRepository.findByDriverDriverId(driverId);
    }

    public long countVehiclesByDriver(Long driverId) {
        return vehicleRepository.countVehiclesByDriverId(driverId);
    }

    public List<Vehicle> getVehiclesOfBusyDrivers(int minVehicles) {
        return vehicleRepository.findVehiclesOfBusyDrivers(minVehicles);
    }

    public List<Vehicle> getVehiclesOfDriversWithoutShipments() {
        return vehicleRepository.findVehiclesOfDriversWithoutShipments();
    }
}
