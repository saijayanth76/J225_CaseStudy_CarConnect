/* Case Study
   CarConnect, a Car Rental Platform */

-- Database Creation
CREATE DATABASE CarConnect;
USE CarConnect;

-- Customer Table 
CREATE TABLE Customer
(
	CustomerID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50),
    Email VARCHAR(60) UNIQUE,
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL,
    Address VARCHAR(100) NOT NULL,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(50) NOT NULL,
    RegistrationDate DATE DEFAULT(current_date())
);

-- Vehicle Table
CREATE TABLE Vehicle (
    VehicleID INT PRIMARY KEY AUTO_INCREMENT,
    Model VARCHAR(100) NOT NULL,
    Make VARCHAR(100) NOT NULL,
    Year INT,
    Color VARCHAR(50),
    RegistrationNumber VARCHAR(20) UNIQUE NOT NULL,
    Availability BOOLEAN NOT NULL,
    DailyRate DECIMAL(10, 2) NOT NULL
);

-- Reservation Table
CREATE TABLE Reservation (
    ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    CustomerID INT,
    VehicleID INT,
    StartDate DATE NOT NULL DEFAULT(CURRENT_DATE()),
    EndDate DATE NOT NULL,
    TotalCost DECIMAL(10, 2) NOT NULL,
    Status VARCHAR(20) NOT NULL,
    CONSTRAINT reservation_fk_customerId FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID) ON DELETE CASCADE,
    CONSTRAINT reservation_fk_vehicleId FOREIGN KEY (VehicleID) REFERENCES Vehicle(VehicleID) ON DELETE CASCADE
);

-- Admin Table
CREATE TABLE Admin (
    AdminID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50),
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL,
    Username VARCHAR(50) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL,
    Role VARCHAR(50) NOT NULL,
    JoinDate DATE NOT NULL
);

-- Inserting records into Customer table
INSERT INTO Customer (FirstName, LastName, Email, PhoneNumber, Address, Username, Password,RegistrationDate)
VALUES 
    ('John', 'Doe', 'john@gmail.com', '1234567890', '123 Main St', 'johndoe', 'password1','2023-11-25'),
    ('Jane', 'Doe', 'jane@gmail.com', '0987654321', '456 Elm St', 'janedoe', 'password2', '2024-01-30'),
    ('Alice', 'Smith', 'alice@gmail.com', '5551234567', '789 Oak St', 'alicesmith', 'password3', '2023-12-07'),
    ('Bob', 'Johnson', 'bob@gmail.com', '9876543000', '321 Maple St', 'bobjohnson', 'password4','2023-05-07'),
    ('Emily', 'Brown', 'emily@gmail.com', '1239876543', '654 Pine St', 'emilybrown', 'password5', '2024-02-09'),
    ('Michael', 'Jones', 'michael@gmail.com', '3332221111', '987 Cedar St', 'michaeljones', 'password6', '2022-03-09'),
    ('Samantha', 'Davis', 'samantha@gmail.com', '7778889999', '741 Birch St', 'samanthadavis', 'password7', '2023-07-17'),
    ('David', 'Wilson', 'david@gmail.com', '1112223333', '852 Walnut St', 'davidwilson', 'password8','2024-01-01'),
    ('Sarah', 'Martinez', 'sarah@gmail.com', '6665554444', '159 Spruce St', 'sarah', 'password9','2024-03-08'),
    ('Daniel', 'Garcia', 'daniel@gmail.com', '6665550000', '364 Spruce St', 'danielgarcia', 'password10','2024-01-31');

-- Inserting records into vehicle table

INSERT INTO Vehicle (VehicleID, Model, Make, Year, Color, RegistrationNumber, Availability, DailyRate)
VALUES 
    (1, 'Civic', 'Honda', 2022, 'Black', 'ABC123', 1, 50.00),
    (2, 'Corolla', 'Toyota', 2021, 'Red', 'DEF456', 1, 55.00),
    (3, 'Accord', 'Honda', 2023, 'White', 'GHI789', 0, 60.00),
    (4, 'Camry', 'Toyota', 2022, 'Blue', 'JKL012', 1, 65.00),
    (5, 'Sentra', 'Nissan', 2022, 'Silver', 'MNO345', 1, 55.00),
    (6, 'Altima', 'Nissan', 2023, 'Gray', 'PQR678', 0, 60.00),
    (7, 'Fusion', 'Ford', 2021, 'Black', 'STU901', 1, 70.00),
    (8, 'Focus', 'Ford', 2022, 'White', 'VWX234', 1, 65.00),
    (9, 'Malibu', 'Chevrolet', 2022, 'Red', 'YZA567',1 , 70.00),
    (10, 'C-class', 'Mercedes', 2023, 'Blue', 'BCD890',0, 65.00);
    
-- Inserting records into reservation table

INSERT INTO Reservation (ReservationID, CustomerID, VehicleID, StartDate, EndDate, TotalCost, Status)
VALUES 
    (1, 1, 7, '2024-03-10', '2024-03-15', 250.00, 'Confirmed'),
    (2, 3, 2, '2024-01-12', '2024-04-18', 330.00, 'Completed'),
    (3, 1, 6, '2024-02-14', '2024-03-20', 360.00, 'Confirmed'),
    (4, 2, 1, '2023-03-16', '2023-03-22', 390.00, 'Completed'),
    (5, 4, 9, '2023-03-18', '2023-03-24', 330.00, 'pending'),
    (6, 7, 5, '2024-03-20', '2024-03-26', 360.00, 'Completed'),
    (7, 6, 7, '2022-04-22', '2022-05-28', 420.00, 'Confirmed'),
    (8, 9, 8, '2024-03-24', '2024-03-30', 390.00, 'pending'),
    (9, 3, 10, '2024-01-26', '2024-04-01', 420.00, 'Confirmed'),
    (10, 5, 3, '2024-02-28', '2024-03-03', 390.00, 'pending');

-- Inserting into Admin table
INSERT INTO Admin (AdminID, FirstName, LastName, Email, PhoneNumber, Username, Password, Role, JoinDate)
VALUES 
    (1, 'oliver', 'Doe', 'oliver.doe@gmail.com', '1234567890', 'johndoe', '123', 'Super Admin', '2023-01-15'),
    (2, 'john', 'Smith', 'john.smith@gmail.com', '0987654321', 'janesmith', 'abc', 'fleet manager', '2023-02-20'),
    (3, 'sai', 'Johnson', 'johnson@gmail.com', '5551234567', 'alicejohnson', '456', 'Super Admin', '2023-03-25'),
    (4, 'divya', 'Williams', 'divya.williams@gmail.com', '9876543210', 'bobwilliams', 'defg', 'super Admin', '2023-04-30'),
    (5, 'George', 'white', 'george@gmail.com', '1239876543', 'emilybrown', '123456', 'fleet manager', '2023-05-05');



