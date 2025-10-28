/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
    private String duplicateUserName;

    public UserError() {
    }

    public UserError(String userNameError, String lastNameError, String passwordError, String duplicateUserName) {
        this.userNameError = userNameError;
        this.lastNameError = lastNameError;
        this.passwordError = passwordError;
        this.duplicateUserName = duplicateUserName;
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

    public String getDuplicateUserName() {
        return duplicateUserName;
    }

    public void setDuplicateUserName(String duplicateUserName) {
        this.duplicateUserName = duplicateUserName;
    }
}
