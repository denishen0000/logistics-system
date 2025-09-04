package com.logistics.system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    private Long shipmentId;

    @ManyToOne
    @JoinColumn(name = "assigned_driver_id")
    @JsonIgnore
    private Driver assignedDriver;

    @Column(name = "weight")
    private Double weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ShipmentStatus status;

    @Column(name = "pickup_date")
    private LocalDateTime pickupDate;

    @Column(name = "delivery_date")
    private LocalDateTime deliveryDate;

    @Column(name = "estimated_date")
    private LocalDateTime estimatedDelivery;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DeliveryLog> deliveryLogs = new ArrayList<>();

    // Constructors
    public Shipment() {}

    public Shipment(Long shipmentId, Driver assignedDriver, Double weight, ShipmentStatus status,
                    LocalDateTime pickupDate, LocalDateTime deliveryDate, LocalDateTime estimatedDelivery) {
        this.shipmentId = shipmentId;
        this.assignedDriver = assignedDriver;
        this.weight = weight;
        this.status = status;
        this.pickupDate = pickupDate;
        this.deliveryDate = deliveryDate;
        this.estimatedDelivery = estimatedDelivery;
    }

    // Getters and Setters
    public Long getShipmentId() { return shipmentId; }
    public void setShipmentId(Long shipmentId) { this.shipmentId = shipmentId; }

    public Driver getAssignedDriver() { return assignedDriver; }
    public void setAssignedDriver(Driver assignedDriver) { this.assignedDriver = assignedDriver; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public ShipmentStatus getStatus() { return status; }
    public void setStatus(ShipmentStatus status) { this.status = status; }

    public LocalDateTime getPickupDate() { return pickupDate; }
    public void setPickupDate(LocalDateTime pickupDate) { this.pickupDate = pickupDate; }

    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDateTime deliveryDate) { this.deliveryDate = deliveryDate; }

    public LocalDateTime getEstimatedDelivery() { return estimatedDelivery; }
    public void setEstimatedDelivery(LocalDateTime estimatedDelivery) { this.estimatedDelivery = estimatedDelivery; }

    public List<Route> getRoutes() { return routes; }
    public void setRoutes(List<Route> routes) { this.routes = routes; }

    public List<DeliveryLog> getDeliveryLogs() { return deliveryLogs; }
    public void setDeliveryLogs(List<DeliveryLog> deliveryLogs) { this.deliveryLogs = deliveryLogs; }
}
