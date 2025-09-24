package DBUtils;

public class User {
    private int userId; // Changed from id to userId
    private String username;
    private String password;
    private String lastname;
    private boolean role;

    public User() {}

    public User(int userId, String username, String password, String lastname, boolean role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.role = role;
    }

    public int getUserId() { return userId; } // Changed from getId
    public void setUserId(int userId) { this.userId = userId; } // Changed from setId

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getLastname() { return lastname; }
    public void setLastname(String lastname) { this.lastname = lastname; }

    public boolean isRole() { return role; }
    public void setRole(boolean role) { this.role = role; }
}