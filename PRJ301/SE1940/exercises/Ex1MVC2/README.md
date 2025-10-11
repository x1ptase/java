# Product Management System

A Java web application built using MVC2 pattern with JSP and EL (Expression Language).

## Features

- View product list
- View product details
- Add new products
- Update existing products
- Modern, responsive UI

## Requirements

- Java 8 or higher
- Apache Tomcat 9.0 or higher
- SQL Server 2016 or higher
- NetBeans IDE (recommended)

## Database Setup

1. Open SQL Server Management Studio
2. Run the `database_setup.sql` script to create the database and table
3. Update database connection details in `src/java/util/DBUtil.java` if needed:
   - Server: localhost:1433
   - Database: SaleDB
   - Username: sa
   - Password: 123456

## Project Structure

```
src/java/
├── controller/
│   └── MainController.java    # Main servlet controller
├── dao/
│   └── ProductDAO.java        # Data Access Object
├── model/
│   └── Product.java           # Product model class
└── util/
    └── DBUtil.java            # Database utility class

web/
├── viewProductList.jsp        # Product list page
├── detailProduct.jsp          # Product details page
├── addNewProduct.jsp          # Add new product page
├── error.jsp                  # Error page
├── index.html                 # Welcome page
└── WEB-INF/
    └── web.xml                # Web application configuration
```

## How to Run

1. Open the project in NetBeans IDE
2. Make sure the SQL Server is running
3. Deploy the project to Tomcat server
4. Access the application at: `http://localhost:8080/Ex1MVC2`

## Application Flow

1. **Main Controller**: All requests are handled by `MainController` servlet
2. **View Product List**: Shows all products in a table format
3. **Product Details**: View and update individual product information
4. **Add New Product**: Create new products with validation
5. **Error Handling**: Centralized error handling with user-friendly messages

## Database Schema

### Products Table
- `ProductID` (int, Primary Key, Identity)
- `ProductName` (varchar(50))
- `UnitPrice` (float)
- `Quantity` (int)

## Technologies Used

- **Backend**: Java Servlet, JSP, EL
- **Database**: SQL Server
- **Frontend**: HTML, CSS, JavaScript
- **Architecture**: MVC2 Pattern
- **Build Tool**: Apache Ant (NetBeans)

## Configuration

The application uses the following configuration:
- **Context Path**: `/Ex1MVC2`
- **Main Servlet**: `MainController`
- **URL Pattern**: `/MainController`
- **Database Driver**: SQL Server JDBC Driver

## Sample Data

The application comes with sample data:
- Product 1: caphe, $100, Quantity: 20
- Product 2: sua, $23, Quantity: 12
