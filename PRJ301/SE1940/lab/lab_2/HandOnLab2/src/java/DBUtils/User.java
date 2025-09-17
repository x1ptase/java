package DBUtils;
/**
 *
 * @author x1pta
 */
public class User {
    private String userName;
    private String password;
    private String lastName;
    private boolean isAdmin;
    
    public User(){
        this.userName=null;
        this.password=null;
        this.lastName=null;
        this.isAdmin=false;
    }

    public User(String userName, String password, String lastName, boolean isAdmin) {
        this.userName = userName;
        this.password = password;
        this.lastName = lastName;
        this.isAdmin = isAdmin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public String toString() {
        return String.format("UserName:%s, Password:%s, LastName:%s, isAdmin:%b",
                userName, password, lastName, isAdmin);
    }
} // end class 
