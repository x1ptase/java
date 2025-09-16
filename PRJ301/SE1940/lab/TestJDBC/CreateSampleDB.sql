CREATE DATABASE SampleDB;
GO

USE SampleDB;
GO

CREATE TABLE Items (
    ItemID varchar(15) NOT NULL,
    ItemName varchar(50) NOT NULL,
    Quantity int NOT NULL
);
GO

INSERT INTO Items (ItemID, ItemName, Quantity) VALUES
('001', 'Coffee', 100),
('002', 'Milk', 200),
('003', 'Cake', 300);
GO