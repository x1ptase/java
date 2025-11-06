/* ===== RESET DATABASE ===== */
IF DB_ID('PizzaDB') IS NOT NULL
BEGIN
    ALTER DATABASE PizzaDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE PizzaDB;
END;
GO

CREATE DATABASE PizzaDB;
GO
USE PizzaDB;
GO

/* ===== SCHEMA ===== */

/* Account: dùng đúng tên cột mà AccountDAO đang đọc */
IF OBJECT_ID('dbo.Account','U') IS NOT NULL DROP TABLE dbo.Account;
GO
CREATE TABLE dbo.Account(
    accountID INT IDENTITY(1,1) PRIMARY KEY,
    userName  VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(100) NOT NULL,
    fullName  VARCHAR(100) NULL,
    type      BIT          NOT NULL DEFAULT(0) -- 1 = admin, 0 = user
);
GO

/* Suppliers */
IF OBJECT_ID('dbo.Suppliers','U') IS NOT NULL DROP TABLE dbo.Suppliers;
GO
CREATE TABLE dbo.Suppliers(
    SupplierID  INT          PRIMARY KEY,
    CompanyName VARCHAR(100) NOT NULL,
    Address     VARCHAR(255) NULL,
    Phone       VARCHAR(20)  NULL
);
GO

/* Categories */
IF OBJECT_ID('dbo.Categories','U') IS NOT NULL DROP TABLE dbo.Categories;
GO
CREATE TABLE dbo.Categories(
    CategoryID   INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName VARCHAR(100) NOT NULL,
    Description  VARCHAR(100) NULL
);
GO

/* Products: KHÔNG IDENTITY để phù hợp DAO hiện tại */
IF OBJECT_ID('dbo.Products','U') IS NOT NULL DROP TABLE dbo.Products;
GO
CREATE TABLE dbo.Products(
    ProductID       INT           PRIMARY KEY,
    ProductName     VARCHAR(100)  NOT NULL,
    SupplierID      INT           NULL,
    CategoryID      INT           NULL,
    QuantityPerUnit VARCHAR(50)   NULL,
    UnitPrice       FLOAT         NOT NULL,   -- DAO đang dùng getFloat
    ProductImage    VARCHAR(255)  NULL,
    CONSTRAINT FK_Products_Suppliers FOREIGN KEY (SupplierID) REFERENCES dbo.Suppliers(SupplierID),
    CONSTRAINT FK_Products_Categories FOREIGN KEY (CategoryID) REFERENCES dbo.Categories(CategoryID)
);
GO

/* Customers */
IF OBJECT_ID('dbo.Customers','U') IS NOT NULL DROP TABLE dbo.Customers;
GO
CREATE TABLE dbo.Customers(
    CustomerID  INT          PRIMARY KEY,
    Password    VARCHAR(100) NOT NULL,
    ContactName VARCHAR(100) NOT NULL,
    Address     VARCHAR(255) NULL,
    Phone       VARCHAR(20)  NULL
);
GO

/* Orders */
IF OBJECT_ID('dbo.Orders','U') IS NOT NULL DROP TABLE dbo.Orders;
GO
CREATE TABLE dbo.Orders(
    OrderID     INT          PRIMARY KEY,
    CustomerID  INT          NULL,
    OrderDate   VARCHAR(50)  NULL,
    RequiredDate VARCHAR(50) NULL,
    ShippedDate VARCHAR(50)  NULL,
    Freight     FLOAT        NULL,
    ShipAddress VARCHAR(255) NULL,
    CONSTRAINT FK_Orders_Customers FOREIGN KEY (CustomerID) REFERENCES dbo.Customers(CustomerID)
);
GO

/* OrderDetails */
IF OBJECT_ID('dbo.OrderDetails','U') IS NOT NULL DROP TABLE dbo.OrderDetails;
GO
CREATE TABLE dbo.OrderDetails(
    OrderID   INT   NOT NULL,
    ProductID INT   NOT NULL,
    UnitPrice FLOAT NOT NULL,
    Quantity  INT   NOT NULL,
    CONSTRAINT PK_OrderDetails PRIMARY KEY (OrderID, ProductID),
    CONSTRAINT FK_OrderDetails_Orders   FOREIGN KEY (OrderID)  REFERENCES dbo.Orders(OrderID),
    CONSTRAINT FK_OrderDetails_Products FOREIGN KEY (ProductID) REFERENCES dbo.Products(ProductID)
);
GO

/* ===== SEED DATA ===== */

/* Accounts */
INSERT INTO dbo.Account(userName, password, fullName, type) VALUES
('U001','111','Administrator',1), -- admin
('U002','222','Regular User',0);

/* Suppliers */
INSERT INTO dbo.Suppliers(SupplierID, CompanyName, Address, Phone) VALUES
(1,'Downtown','8721 M Central Avenue, Los Angeles, CA 90036','7896543210'),
(2,'Hollywood','678 W Hollywood Way, Burbank CA 91505','7896450123');

/* Categories */
INSERT INTO dbo.Categories(CategoryName, Description) VALUES
('Pepperoni','Sliced pepperoni, parmesan cheese, mozzarella cheese, sauce'),
('BBQ Chicken Pizza','BBQ sauce, chicken, mozzarella, tomatoes, red onion'),
('Cheese Pizza','Tomato sauce, mozzarella, parmesan, basil'),
('Ultimate Meat Pizza','Sausage, beef, pepperoni, ham, salami, bacon');

/* Products (đường dẫn ảnh khớp thư mục web/resource/images) */
INSERT INTO dbo.Products(ProductID, ProductName, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage) VALUES
(1,'Pepperoni',         1,1,'Medium', 25.00, '/ShoppingWebsite/resource/images/classic_pep.jpg'),
(2,'BBQ Chicken',       1,2,'Small',  27.00, '/ShoppingWebsite/resource/images/bbq_chicken.jpg'),
(3,'Cheese Pizza',      2,3,'Medium', 22.50, '/ShoppingWebsite/resource/images/cheese_supreme.jpg'),
(4,'Ultimate Meat Pizza',2,4,'Large', 32.75, '/ShoppingWebsite/resource/images/meat_lovers.jpg');

/* Customers */
INSERT INTO dbo.Customers(CustomerID, Password, ContactName, Address, Phone) VALUES
(1001,'custpass1','John Wick','123 Main St, New York','5551234567'),
(1002,'custpass2','Jane Doe','456 Oak Ave, Los Angeles','5559876543');

/* Orders */
INSERT INTO dbo.Orders(OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress) VALUES
(5001,1001,'2025-10-25','2025-10-27','2025-10-26',15.50,'123 Main St, New York'),
(5002,1002,'2025-10-26','2025-10-28',NULL,12.00,'456 Oak Ave, Los Angeles');

/* OrderDetails */
INSERT INTO dbo.OrderDetails(OrderID, ProductID, UnitPrice, Quantity) VALUES
(5001,1,25.00,2),
(5001,3,22.50,1),
(5002,2,27.00,1);
GO

/* ===== QUICK CHECKS ===== */
SELECT COUNT(*) AS NumAccounts FROM dbo.Account;
SELECT COUNT(*) AS NumProducts FROM dbo.Products;
SELECT TOP 5 * FROM dbo.Products;