-- 1. Tạo database PartyDB
CREATE DATABASE PartyDB;
GO

USE PartyDB;
GO

-- 2. Bảng Admins (quản lý login)
CREATE TABLE Admins (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username NVARCHAR(50) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL -- nên hash trong thực tế
);

-- Thêm dữ liệu mẫu
INSERT INTO Admins (username, password)
VALUES 
    ('admin', '12345'),
    ('manager', 'pass123');

-- 3. Bảng Persons (CRUD)
CREATE TABLE Persons (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    age INT NOT NULL
);

-- Thêm dữ liệu mẫu
INSERT INTO Persons (name, age)
VALUES 
    (N'Nguyễn Văn A', 25),
    (N'Trần Thị B', 30),
    (N'Lê Văn C', 22);

SELECT * FROM Persons;
