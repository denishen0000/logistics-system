# Logistics Management System

**Tech Stack:** Spring Boot · Java · PostgreSQL · JPA/Hibernate · Spring Security  

A full-stack logistics management system that allows admins to manage shipments, track drivers, and update shipment statuses with automated status tracking.

---

## Features

- **Shipment Management:** Create, update, and delete shipments with detailed information including weight, pickup date, estimated delivery, and actual delivery date.  
- **Driver Assignment:** Assign shipments to drivers and track driver assignments.  
- **Automated Status Updates:** Shipment status is automatically updated to **PENDING**, **IN_TRANSIT**, **DELAYED**, or **DELIVERED** based on pickup and delivery dates.  
- **Shipment Filtering:** Retrieve shipments by status, including delayed shipments.  
- **Secure Access:** Spring Security integration for role-based access control.  

---

## Screenshots

**Shipment List Page**  
<img width="1912" height="940" alt="Shipment List" src="https://via.placeholder.com/800x400.png?text=Shipment+List+Page" />

**Shipment Details Page**  
<img width="1912" height="940" alt="Shipment Details" src="https://via.placeholder.com/800x400.png?text=Shipment+Details+Page" />

**Driver Assignment Page**  
<img width="1912" height="940" alt="Driver Assignment" src="https://via.placeholder.com/800x400.png?text=Driver+Assignment+Page" />

---

## Architecture

- **Backend:** Spring Boot exposes RESTful APIs for shipment CRUD, driver assignment, and shipment tracking.  
- **Database:** PostgreSQL with JPA/Hibernate handles persistent storage for shipments, drivers, and statuses.  
- **Security:** Spring Security provides role-based authentication and authorization.  
- **Date Handling:** Supports parsing both offset (`2024-05-01T08:30:00Z`) and local datetime formats (`2024-05-01T08:30:00`).  

---

## API Endpoints

- `POST /api/shipments` — Create new shipment  
- `GET /api/shipments` — List all shipments  
- `GET /api/shipments/{id}` — Get shipment details  
- `PUT /api/shipments/{id}` — Update shipment details (weight, dates, etc.)  
- `PUT /api/shipments/{id}/status` — Update shipment status manually  
- `PUT /api/shipments/{id}/assign/{driverId}` — Assign a driver to shipment  
- `GET /api/shipments/status/{status}` — Get shipments by status  
- `GET /api/shipments/delayed` — Get delayed shipments  

---

## Usage

1. Start the Spring Boot application:  
   ```bash
   mvn spring-boot:run
