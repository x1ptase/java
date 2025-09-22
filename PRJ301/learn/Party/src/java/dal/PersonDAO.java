package dal;

import model.Person;
import java.sql.*;
import java.util.ArrayList;

public class PersonDAO extends DBContext {

    public ArrayList<Person> getAllPersons() throws SQLException {
    ArrayList<Person> list = new ArrayList<>();
    String sql = "SELECT * FROM Persons";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Person person = new Person();
            person.setId(rs.getInt("id"));
            person.setName(rs.getString("name"));
            person.setAge(rs.getInt("age"));
            list.add(person);
        }
    }
    return list;
}


    public Person getPersonByID(int id) throws SQLException {
        String sql = "SELECT * FROM Persons WHERE id = ?";
        Person person = null;
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    person = new Person();
                    person.setId(rs.getInt("id"));
                    person.setName(rs.getString("name"));
                    person.setAge(rs.getInt("age"));
                }
            }
        }
        return person;
    }

    public void addPerson(Person person) throws SQLException {
        String sql = "INSERT INTO Persons (name, age) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.executeUpdate();
        }
    }

    public int updatePerson(Person person) throws SQLException {
        String sql = "UPDATE Persons SET name = ?, age = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, person.getName());
            pstmt.setInt(2, person.getAge());
            pstmt.setInt(3, person.getId());
            return pstmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM Persons WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }
}
