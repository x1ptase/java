-- Database setup script for Product Management System
-- Run this script in SQL Server Management Studio or sqlcmd

-- Create database
CREATE DATABASE SaleDB;
GO

-- Use the database
USE SaleDB;
GO

-- Create Products table
CREATE TABLE Products (
    ProductID int IDENTITY(1,1) PRIMARY KEY,
    ProductName varchar(50) NOT NULL,
    UnitPrice float NOT NULL,
    Quantity int NOT NULL
);
GO

-- Insert sample data
INSERT INTO Products (ProductName, UnitPrice, Quantity) VALUES 
('caphe', 100, 20),
('sua', 23, 12);
GO

-- Verify the data
SELECT * FROM Products;
GO
