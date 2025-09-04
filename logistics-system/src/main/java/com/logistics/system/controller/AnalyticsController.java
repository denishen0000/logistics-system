package com.logistics.system.controller;

import com.logistics.system.entity.ShipmentStatus;
import com.logistics.system.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Month;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "http://localhost:3000")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    // ------------------- GET /analytics/avg-delivery-time -------------------
    @GetMapping("/avg-delivery-time")
    public ResponseEntity<Map<Long, Double>> getAvgDeliveryTimePerDriver() {
        Map<Long, Double> avgTimes = analyticsService.avgDeliveryTimePerDriver();
        return ResponseEntity.ok(avgTimes);
    }

    // ------------------- GET /analytics/delays -------------------
    @GetMapping("/delays")
    public ResponseEntity<Map<Month, Long>> getDelayedShipmentsPerMonth() {
        Map<Month, Long> delays = analyticsService.delayedShipmentsPerMonth();
        return ResponseEntity.ok(delays);
    }

    // Optional: GET /analytics/driver-performance
    @GetMapping("/driver-performance")
    public ResponseEntity<Map<Long, Map<ShipmentStatus, Long>>> getDriverPerformance() {
        Map<Long, Map<ShipmentStatus, Long>> performance = analyticsService.driverPerformance();
        return ResponseEntity.ok(performance);
    }
}
