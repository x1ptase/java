
-- DATABASE: SalesSystem
CREATE DATABASE  SalesSystem;
USE SalesSystem;

-- TABLE: Customers
CREATE TABLE Customers (
    CustomerID INT  PRIMARY KEY,
    Password VARCHAR(100) NOT NULL,
    ContactName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    Phone VARCHAR(20)
);

-- TABLE: Suppliers
CREATE TABLE Suppliers (
    SupplierID INT  PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL,
    Address VARCHAR(255),
    Phone VARCHAR(20)
);
-- TABLE: Categories
ALTER TABLE Categories
ALTER COLUMN Description VARCHAR(100);

CREATE TABLE Categories (
    CategoryID INT  PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL,
    Description VARCHAR(100)

);


-- TABLE: Products
ALTER TABLE Products
ALTER COLUMN UnitPrice FLOAT;
CREATE TABLE Products (
    ProductID INT  PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    SupplierID INT,
    CategoryID INT,
    QuantityPerUnit VARCHAR(50),
    UnitPrice FLOAT,
    ProductImage VARCHAR(255),
    FOREIGN KEY (SupplierID) REFERENCES Suppliers(SupplierID),
    FOREIGN KEY (CategoryID) REFERENCES Categories(CategoryID)
);
-- TABLE: Orders

CREATE TABLE Orders (
    OrderID INT  PRIMARY KEY,
    CustomerID INT,
    OrderDate VARCHAR(50),
    RequiredDate VARCHAR(50),
    ShippedDate VARCHAR(50),
    Freight FLOAT,
    ShipAddress VARCHAR(255),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);
-- TABLE: OrderDetails 


CREATE TABLE OrderDetails (
    OrderID INT,
    ProductID INT,
    UnitPrice FLOAT,
    Quantity INT,
    PRIMARY KEY (OrderID, ProductID),
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);

-- Lệnh 1: Xóa bảng Account (Nếu nó tồn tại)
IF OBJECT_ID('Account', 'U') IS NOT NULL
    DROP TABLE Account;
GO

-- Lệnh 2: Tạo lại bảng với IDENTITY(1,1)
CREATE TABLE Account (
    AccountID INT PRIMARY KEY IDENTITY(1,1), -- Thuộc tính IDENTITY bắt buộc
    UserName VARCHAR(50) NOT NULL UNIQUE,
    Password VARCHAR(100) NOT NULL,
    FullName VARCHAR(100),
    Type BIT NOT NULL DEFAULT 0
);
GO


-- Lệnh INSERT TỐI ƯU cho SQL Server (AccountID tự động tăng)

INSERT INTO Account (UserName, Password, FullName, Type) VALUES 
('U001', '111', 'Lamine Yamal', 1),    -- Admin
('U002', '222', 'Pedri', 0),           -- User
('U003', '333', 'Raphinha', 1),        -- Admin
('U004', '444', 'Gavi', 0),            -- User
('U005', '555', 'Xavi', 0);            -- User

SELECT AccountID, UserName, FullName, Type 
FROM Account;