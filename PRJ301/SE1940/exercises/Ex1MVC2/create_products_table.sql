-- Script to create Products table if it doesn't exist
-- Run this script in your SQL Server database

USE SaleDB;
GO

-- Check if Products table exists, if not create it
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Products' AND xtype='U')
BEGIN
    CREATE TABLE Products (
        ProductID int IDENTITY(1,1) PRIMARY KEY,
        ProductName nvarchar(255) NOT NULL,
        UnitPrice decimal(10,2) NOT NULL,
        Quantity int NOT NULL
    );
    
    PRINT 'Products table created successfully';
    
    -- Insert some sample data
    INSERT INTO Products (ProductName, UnitPrice, Quantity) VALUES 
    ('Sample Product 1', 10.50, 100),
    ('Sample Product 2', 25.75, 50),
    ('Sample Product 3', 5.00, 200);
    
    PRINT 'Sample data inserted';
END
ELSE
BEGIN
    PRINT 'Products table already exists';
END

-- Check table structure
SELECT 
    COLUMN_NAME,
    DATA_TYPE,
    IS_NULLABLE,
    CHARACTER_MAXIMUM_LENGTH
FROM INFORMATION_SCHEMA.COLUMNS 
WHERE TABLE_NAME = 'Products'
ORDER BY ORDINAL_POSITION;

-- Show current data
SELECT * FROM Products;
