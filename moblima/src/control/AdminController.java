package control;

import java.util.ArrayList;

import entity.*;
/**
 * It Represents the Admin Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class AdminController extends DatabaseController<Admin> {
	/** 
     * File path name of admin database file to access. 
     */
    public AdminController() {
        super(MainController.FILEPATH_ADMIN);
    }

    /**
     * Verifying an user's logging in (admin/movie goer) by checking with the database
     * @param usernameInput The username input by the admin
     * @param passwordInput The password input by the admin
     * @return boolean      return true if user has been verified based on the email and password entered
     */
    public boolean loginValidation(String usernameInput, String passwordInput) {
        ArrayList<Admin> adminData = readFromDatabase();
        // Search for input username and password in Admin database
        for (Admin admin : adminData) {
            if (usernameInput.equals(admin.getUsername()) &&
                    passwordInput.equals(admin.getPassword())) {
                return true;
            }
        }
        return false;
    }
    /**
     * Extracting Information of the admin from the username input from the Database
     * @param username Read admins username from Database
     * @return admin   If successful, it extracts the information of the admin by matching it with a username in the database, else null value is returned. 
     */
    public Admin getAdminByUsername(String username) {
        ArrayList<Admin> admins = readFromDatabase();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }
    /**
     * Deletes the username of the admin from the database
     * @param username   The username of the admin 
     * @return boolean   It returns true on successful deletion of admin username
     */
    public boolean deleteAdminByUsername(String username) {
        ArrayList<Admin> admins = readFromDatabase();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                admins.remove(admin);
                overwriteDatabase(admins);
                return true;
            }
        }
        return false;
    }
}
