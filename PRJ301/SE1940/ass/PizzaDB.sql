
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

-- Đảm bảo không còn bảng cũ tồn tại
IF OBJECT_ID('Categories', 'U') IS NOT NULL
    DROP TABLE Categories;
GO
CREATE TABLE Categories (
    -- Thêm IDENTITY(1,1)
    CategoryID INT PRIMARY KEY IDENTITY(1,1), 
    CategoryName VARCHAR(100) NOT NULL,
    Description VARCHAR(100)
);
GO


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

-- Dữ liệu mẫu cho bảng Categories
INSERT INTO Categories (CategoryName, Description) VALUES 
('Pepperoni', 'Sliced pepperoni, parmesan cheese, mozzarella cheese, sauc'),
('BBQ Chicken Pizza', 'BBQ sauce, chicken, mozzarella, cheese, tomatoes, red onion'),
('Cheese Pizza', 'Tomato sauce, mozzarella cheese, parmesan cheese, basil'),
('Ultimate Meat Pizza', 'Sausage, seasoned ground beef, pepperoni, ham, salami, bacon');

SELECT * FROM Categories;

-- Dữ liệu mẫu cho bảng Suppliers
INSERT INTO Suppliers (SupplierID, CompanyName, Address, Phone) VALUES 
(1, 'Downtown', '8721 M Central Avenue, Los Angeles, CA 90036', '7896543210'),
(2, 'Hollywood', '678 W Hollywood Way, Burbank CA 91505', '7896450123');

SELECT * FROM Suppliers;

-- Dữ liệu mẫu cho bảng Products (Pizza)
-- Giả định SupplierID 1 và 2 đã tồn tại
INSERT INTO Products (ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage) VALUES
(1, 'Pepperoni', 1, 1, 'Medium', 25.00, 'images/classic_pep.jpg'),
(2, 'BBQ Chicken', 1, 2, 'Small', 27.00, 'images/bbq_chicken.jpg'),
(3, 'Cheese Pizza', 2, 3, 'Medium', 22.50, 'images/cheese_supreme.jpg'),
(4, 'Ultimate Meat Pizza', 2, 4, 'Large', 32.75, 'images/meat_lovers.jpg');

SELECT * FROM Products;

-- Dữ liệu mẫu cho bảng Customers
INSERT INTO Customers (CustomerID, Password, ContactName, Address, Phone) VALUES
(1001, 'custpass1', 'John Wick', '123 Main St, New York', '5551234567'),
(1002, 'custpass2', 'Jane Doe', '456 Oak Ave, Los Angeles', '5559876543'),
(1003, 'custpass3', 'Robert Baratheon', '789 King Rd, Seattle', '5551122334');

SELECT * FROM Customers;

-- Dữ liệu mẫu cho bảng Orders
-- OrderID được quản lý thủ công (không phải IDENTITY)
INSERT INTO Orders (OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress) VALUES
(5001, 1001, '2025-10-25', '2025-10-27', '2025-10-26', 15.50, '123 Main St, New York'),
(5002, 1002, '2025-10-26', '2025-10-28', NULL, 12.00, '456 Oak Ave, Los Angeles'),
(5003, 1001, '2025-10-27', '2025-10-29', '2025-10-28', 18.75, '123 Main St, New York');

SELECT * FROM Orders;

-- Dữ liệu mẫu cho bảng OrderDetails
-- OrderID và ProductID là khóa chính kép
INSERT INTO OrderDetails (OrderID, ProductID, UnitPrice, Quantity) VALUES
-- Chi tiết cho OrderID 5001 (John Wick)
(5001, 1, 25.00, 2), -- 2 Pizza Pepperoni
(5001, 3, 22.50, 1), -- 1 Pizza Cheese

-- Chi tiết cho OrderID 5002 (Jane Doe)
(5002, 2, 27.00, 1), -- 1 BBQ Chicken

-- Chi tiết cho OrderID 5003 (John Wick)
(5003, 4, 32.75, 1); -- 1 Ultimate Meat Pizza
GO

SELECT * FROM OrderDetails;