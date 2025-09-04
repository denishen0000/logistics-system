
# Logistics Management System

**Tech Stack:** Spring Boot · Java · PostgreSQL · JPA · Spring Security  

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

**Home Page**  
<img width="1901" height="939" alt="image" src="https://github.com/user-attachments/assets/4c66755f-c2f5-41c2-8763-bf91f6519bf7" />


**Shipment Page**  
<img width="1900" height="941" alt="image" src="https://github.com/user-attachments/assets/596d8239-17aa-45e9-b4bb-f01396ec7200" />

---

## Architecture

- **Backend:** Spring Boot exposes RESTful APIs for shipment CRUD, driver assignment, and shipment tracking.  
- **Database:** PostgreSQL with JPA/Hibernate handles persistent storage for shipments, drivers, and statuses.  
- **Security:** Spring Security provides role-based authentication and authorization.  
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


