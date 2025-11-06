<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Car</title>
    </head>
    <body>
        <h1>Create New Car</h1>

        <form action="CreateCarsController" method="post">
            <table cellpadding="5" cellspacing="0" border="0">
                <tr>
                    <td><label for="carID">CarID</label></td>
                    <td><input type="number" id="carID" name="carID" required /></td>
                </tr>
                <tr>
                    <td><label for="carName">Car Name</label></td>
                    <td><input type="text" id="carName" name="carName" required /></td>
                </tr>
                <tr>
                    <td><label for="manufacturer">Manufacturer</label></td>
                    <td><input type="text" id="manufacturer" name="manufacturer" required /></td>
                </tr>
                <tr>
                    <td><label for="price">Price</label></td>
                    <td><input type="number" step="0.01" id="price" name="price" required /></td>
                </tr>
                <tr>
                    <td><label for="releasedYear">Released Year</label></td>
                    <td><input type="number" id="releasedYear" name="releasedYear" required /></td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <button type="submit">Create</button>
                        <a href="CarsListController">Cancel</a>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
