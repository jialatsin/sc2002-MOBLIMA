package entity;

import java.io.Serializable;

/*
 * Represents an Admin with access to system settings
 * Adminstrators have the ability create, update, delete database records
 */
public class Admin implements Serializable {
    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [username=" + username + ", password=" + password + "]";
    }

}
