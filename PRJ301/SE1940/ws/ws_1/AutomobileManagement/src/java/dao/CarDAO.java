package dao;

import model.Car;
import dal.DBContext;

import java.sql.*;
import java.util.*;

public class CarDAO {

    public List<Car> getAllCars() throws Exception {
        List<Car> list = new ArrayList<>();
        String sql = "SELECT * FROM Cars";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Car(
                        rs.getInt("CarID"),
                        rs.getString("CarName"),
                        rs.getString("Manufacturer"),
                        rs.getDouble("Price"),
                        rs.getInt("ReleasedYear")
                ));
            }
        }
        return list;
    }

    public Car getCarById(int carID) throws Exception {
        String sql = "SELECT * FROM Cars WHERE CarID = ?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, carID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Car(
                        rs.getInt("CarID"),
                        rs.getString("CarName"),
                        rs.getString("Manufacturer"),
                        rs.getDouble("Price"),
                        rs.getInt("ReleasedYear")
                    );
                }
            }
        }
        return null;
    }

    public void insertCar(Car car) throws Exception {
        String sql = "INSERT INTO Cars (CarID, CarName, Manufacturer, Price, ReleasedYear) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, car.getCarID());
            ps.setString(2, car.getCarName());
            ps.setString(3, car.getManufacturer());
            ps.setDouble(4, car.getPrice());
            ps.setInt(5, car.getReleasedYear());
            ps.executeUpdate();
        }
    }

    public void updateCar(Car car) throws Exception {
        String sql = "UPDATE Cars SET CarName=?, Manufacturer=?, Price=?, ReleasedYear=? WHERE CarID=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, car.getCarName());
            ps.setString(2, car.getManufacturer());
            ps.setDouble(3, car.getPrice());
            ps.setInt(4, car.getReleasedYear());
            ps.setInt(5, car.getCarID());
            ps.executeUpdate();
        }
    }

    public void deleteCar(int carID) throws Exception {
        String sql = "DELETE FROM Cars WHERE CarID=?";
        try (Connection con = DBContext.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, carID);
            ps.executeUpdate();
        }
    }
}