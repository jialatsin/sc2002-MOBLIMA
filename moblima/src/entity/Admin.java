package entity;

import java.io.Serializable;

/**
 * Represents an Admin with access to system settings.
 * Adminstrators have the ability to create, update, delete database records.
 */
public class Admin implements Serializable {
    /** Unique username of this admin. */
    private String username;
    /** Password needed to login to this admin account. */
    private String password;

    /**
     * Creates an Admin with the given attributes.
     * 
     * @param username The admin's username.
     * @param password The admin's password (unencrypted).
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the unique username of this admin.
     * 
     * @return String containing this admin's username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Changes the username of this admin.
     * 
     * @param username The input username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of this admin.
     * 
     * @return String containing this admin's username.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Changes the password of this admin.
     * 
     * @param password The input password.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string containing this admin's username and password.
     * 
     * @return String containing this admin's username and password.
     */
    @Override
    public String toString() {
        return "Admin [username=" + username + ", password=" + password + "]";
    }

}
