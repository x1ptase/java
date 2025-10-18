/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package Models.DTO;

/**
 *
 * @author x1pta
 */
public class UserError {
    private String userNameError;
    private String lastNameError;
    private String passwordError;
    private String duplicatedUserName;

    public UserError() {
    }

    public UserError(String userNameError, String lastNameError, String passwordError, String duplicatedUserName) {
        this.userNameError = userNameError;
        this.lastNameError = lastNameError;
        this.passwordError = passwordError;
        this.duplicatedUserName = duplicatedUserName;
    }

    public String getUserNameError() {
        return userNameError;
    }

    public void setUserNameError(String userNameError) {
        this.userNameError = userNameError;
    }

    public String getLastNameError() {
        return lastNameError;
    }

    public void setLastNameError(String lastNameError) {
        this.lastNameError = lastNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getDuplicatedUserName() {
        return duplicatedUserName;
    }

    public void setDuplicatedUserName(String duplicatedUserName) {
        this.duplicatedUserName = duplicatedUserName;
    }

    public void setDuplicateUserName(String the_UserName_already_exists) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
