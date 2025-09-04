CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL,
    password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE drivers (
    driver_id SERIAL PRIMARY KEY,
    user_id INT UNIQUE NOT NULL REFERENCES users(user_id)
);

CREATE TABLE vehicles (
    vehicle_id SERIAL PRIMARY KEY,
    driver_id INT NOT NULL REFERENCES drivers(driver_id)
);

CREATE TABLE shipments (
    shipment_id SERIAL PRIMARY KEY,
    assigned_driver_id INT REFERENCES drivers(driver_id),
    weight DOUBLE PRECISION,
    status VARCHAR(20) CHECK (status IN ('PENDING', 'IN_TRANSIT', 'DELIVERED', 'DELAYED')),
    pickup_date TIMESTAMP,
    delivery_date TIMESTAMP,
    estimated_date TIMESTAMP
);

CREATE TABLE routes (
    route_id SERIAL PRIMARY KEY,
    shipment_id INT NOT NULL REFERENCES shipments(shipment_id)
);

CREATE TABLE delivery_logs (
    log_id SERIAL PRIMARY KEY,
    pickup_date DATE,
    delivery_date DATE,
    shipment_id INT NOT NULL REFERENCES shipments(shipment_id)
);