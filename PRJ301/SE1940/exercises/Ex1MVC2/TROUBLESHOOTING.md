# Troubleshooting Guide - Fix Error 500

## Common Causes of Error 500

### 1. Database Connection Issues
**Symptoms:** Error 500 when accessing any page
**Solutions:**
- Check if SQL Server is running
- Verify database `SaleDB` exists
- Update database credentials in `src/java/util/Config.java`
- Ensure `sqljdbc4.jar` is in the `lib` folder

### 2. Missing Servlet Configuration
**Symptoms:** Error 500 with "Servlet not found"
**Solutions:**
- Check `web.xml` has correct servlet mapping
- Verify `@WebServlet` annotation in MainController
- Ensure servlet class is compiled

### 3. JSP Compilation Errors
**Symptoms:** Error 500 when accessing JSP pages
**Solutions:**
- Check JSP syntax
- Verify JSTL library is available
- Check for missing imports

### 4. Classpath Issues
**Symptoms:** ClassNotFoundException
**Solutions:**
- Add `sqljdbc4.jar` to project libraries
- Check all Java files are compiled
- Verify package structure

## Quick Fix Steps

### Step 1: Test Database Connection
```bash
# Run the database test
java -cp "lib/sqljdbc4.jar:build/classes" util.DBTest
```

### Step 2: Check Configuration
Update `src/java/util/Config.java` with your database settings:
```java
public static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=SaleDB;encrypt=false;trustServerCertificate=true";
public static final String DB_USER = "your_username";
public static final String DB_PASSWORD = "your_password";
```

### Step 3: Verify Project Structure
```
src/java/
├── controller/MainController.java
├── dao/ProductDAO.java
├── model/Product.java
└── util/
    ├── DBUtil.java
    ├── Config.java
    └── DBTest.java

web/
├── viewProductList.jsp
├── detailProduct.jsp
├── addNewProduct.jsp
├── error.jsp
├── index.html
└── WEB-INF/web.xml

lib/
└── sqljdbc4.jar
```

### Step 4: Check Logs
- Check Tomcat logs in `logs/` directory
- Look for specific error messages
- Check console output for exceptions

## Database Setup
1. Run `database_setup.sql` in SQL Server Management Studio
2. Verify table structure:
```sql
USE SaleDB;
SELECT * FROM Products;
```

## Common Error Messages and Solutions

| Error Message | Solution |
|---------------|----------|
| "SQL Server JDBC Driver not found" | Add sqljdbc4.jar to lib folder |
| "Database connection failed" | Check database credentials and server status |
| "Product not found" | Verify database has data |
| "Invalid input format" | Check form validation |
| "Servlet not found" | Check web.xml configuration |

## Testing the Application
1. Start Tomcat server
2. Deploy the application
3. Access: `http://localhost:8080/Ex1MVC2`
4. Should redirect to product list page

## If Still Getting Error 500
1. Check Tomcat server logs
2. Verify all files are in correct locations
3. Test database connection separately
4. Check for any compilation errors
5. Ensure all dependencies are available
