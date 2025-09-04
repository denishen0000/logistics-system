package com.logistics.system.controller;

import com.logistics.system.entity.Driver;
import com.logistics.system.entity.Shipment;
import com.logistics.system.entity.Vehicle;
import com.logistics.system.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(origins = "http://localhost:3000")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    
    @GetMapping
    public ResponseEntity<List<Driver>> getAllDrivers() {
        // Return all drivers; you could extend service for getAllDrivers()
        List<Driver> drivers = driverService.getBusyDrivers(0); // returns all if minShipments=0
        return ResponseEntity.ok(drivers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long id) {
        Driver driver = driverService.getDriverByUserId(id);
        return ResponseEntity.ok(driver);
    }

    // ------------------- GET /drivers/{id}/shipments -------------------
    @GetMapping("/{id}/shipments")
    public ResponseEntity<List<Shipment>> getDriverShipments(@PathVariable Long id) {
        List<Shipment> shipments = driverService.getShipmentsByDriver(id);
        return ResponseEntity.ok(shipments);
    }

    // ------------------- GET /drivers/{id}/workload -------------------
    @GetMapping("/{id}/workload")
    public ResponseEntity<Long> getDriverWorkload(@PathVariable Long id) {
        long workload = driverService.getDriverWorkload(id);
        return ResponseEntity.ok(workload);
    }

    // ------------------- GET /drivers/busy?minShipments=5 -------------------
    @GetMapping("/busy")
    public ResponseEntity<List<Driver>> getBusyDrivers(@RequestParam int minShipments) {
        List<Driver> drivers = driverService.getBusyDrivers(minShipments);
        return ResponseEntity.ok(drivers);
    }

    // ------------------- GET /drivers/{id}/vehicles -------------------
    @GetMapping("/{id}/vehicles")
    public ResponseEntity<List<Vehicle>> getVehiclesByDriver(@PathVariable Long id) {
        List<Vehicle> vehicles = driverService.getVehiclesByDriver(id);
        return ResponseEntity.ok(vehicles);
    }

    // ------------------- GET /drivers/no-vehicles -------------------
    @GetMapping("/no-vehicles")
    public ResponseEntity<List<Driver>> getDriversWithoutVehicles() {
        List<Driver> drivers = driverService.getDriversWithoutVehicles();
        return ResponseEntity.ok(drivers);
    }
}
