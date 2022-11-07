package entity;

import java.io.Serializable;
/**
 * It represents an Admin with access to system settings and the adminstrators have the ability create, update, delete database records.
 * @author  OOP SSP1 Lab Group 4
 * @version 7/11/2022
 */
public class Admin implements Serializable {
    // username and password are private Strings
	/**
	 * username is the unique username of the admin
	 */
	
	private String username;
	/**
	 * password is the secure password entered by the admin
	 */
    private String password;
    /**
     * Creates a admin login page with these given attributes
     * @param username The Admin's username 
     * @param password The Admin's password (unencrypted)
     */
    public Admin(String username, String password) {
    	
        this.username = username;
        this.password = password;
    }
    /**
     * Get the unique username of admin
     * @return username The unique username of admin
     */
    public String getUsername() {
        return username;
    }
    /**
     * String for username of admin
     * @param username The username input by admin
     */
    public void setUsername(String username) {
        this.username = username;
    }
    /**
     * Get the password of admin
     * @return password The password of the admin
     */
    public String getPassword() {
        return password;
    }
    /**
     * String for password of admin
     * @param password The password of the admin
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * String for displaying the admin username and password
     * @return string displaying admin username and password 
     */
    @Override
    public String toString() {
        return "Admin [username=" + username + ", password=" + password + "]";
    }

}

