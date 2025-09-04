package com.logistics.system.service;

import com.logistics.system.entity.Route;
import com.logistics.system.repository.RouteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService {

    private final RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public List<Route> getRoutesByShipment(Long shipmentId) {
        return routeRepository.findByShipmentShipmentId(shipmentId);
    }

    public long countRoutesForShipment(Long shipmentId) {
        return routeRepository.countRoutesByShipmentId(shipmentId);
    }

    public List<Route> getRoutesByDriver(Long driverId) {
        return routeRepository.findRoutesByDriverId(driverId);
    }

    public List<Route> getRoutesOfDelayedShipments() {
        return routeRepository.findRoutesOfDelayedShipments();
    }
}
