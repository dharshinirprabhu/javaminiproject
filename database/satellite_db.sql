CREATE DATABASE IF NOT EXISTS satellite_db;
USE satellite_db;

CREATE TABLE IF NOT EXISTS satellites (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(100) NOT NULL,
    orbit_type VARCHAR(30) NOT NULL,
    launch_date DATE NOT NULL,
    status VARCHAR(30) NOT NULL
);

-- Optional sample records
INSERT INTO satellites (name, country, orbit_type, launch_date, status) VALUES
('INSAT-3D', 'India', 'GEO', '2013-07-26', 'Active'),
('Cartosat-2', 'India', 'LEO', '2017-06-23', 'Active');
