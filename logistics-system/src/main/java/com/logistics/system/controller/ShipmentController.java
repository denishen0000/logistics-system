package com.logistics.system.controller;

import com.logistics.system.entity.Shipment;
import com.logistics.system.entity.ShipmentStatus;
import com.logistics.system.service.ShipmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/shipments")
@CrossOrigin(origins = "http://localhost:3000")
public class ShipmentController {

    private final ShipmentService shipmentService;

    public ShipmentController(ShipmentService shipmentService) {
        this.shipmentService = shipmentService;
    }

    
    @GetMapping
    public ResponseEntity<List<Shipment>> getShipments(@RequestParam(required = false) String status) {
        List<Shipment> shipments;
        if (status != null) {
            ShipmentStatus shipmentStatus = ShipmentStatus.valueOf(status.toUpperCase());
            shipments = shipmentService.getShipmentsByStatus(shipmentStatus);
        } else {
            shipments = shipmentService.getAllShipments();
        }
        return ResponseEntity.ok(shipments);
    }

    
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment saved = shipmentService.createShipment(shipment);
        return ResponseEntity.ok(saved);
    }

    
    @PutMapping("/{id}/status")
    public ResponseEntity<Shipment> updateStatus(
            @PathVariable Long id,
            @RequestBody String newStatus) {
        ShipmentStatus shipmentStatus = ShipmentStatus.valueOf(newStatus.toUpperCase());
        Shipment updated = shipmentService.updateShipmentStatus(id, shipmentStatus);
        return ResponseEntity.ok(updated);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        Shipment updated = shipmentService.updateShipment(id, updates);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/weight")
    public ResponseEntity<Shipment> updateWeight(
            @PathVariable Long id,
            @RequestBody Double newWeight) {
        Shipment updated = shipmentService.updateShipmentWeight(id, newWeight);
        return ResponseEntity.ok(updated);
    }


    // PUT /shipments/{id}/assign?driverId=1
    @PutMapping("/{id}/assign")
    public ResponseEntity<Shipment> assignDriver(
            @PathVariable Long id,
            @RequestParam Long driverId) {
        Shipment assigned = shipmentService.assignShipment(driverId, id);
        return ResponseEntity.ok(assigned);
    }

    // GET /shipments/delayed
    @GetMapping("/delayed")
    public ResponseEntity<List<Shipment>> getDelayedShipments() {
        List<Shipment> delayed = shipmentService.getDelayedShipments();
        return ResponseEntity.ok(delayed);
    }
}
