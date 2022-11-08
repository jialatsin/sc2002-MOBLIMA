package control;

import java.util.ArrayList;

import entity.*;

/**
 * Represents an AdminController which contains the logic for handling Admin
 * data.
 */
public class AdminController extends DatabaseController<Admin> {
    /**
     * Creates an AdminController with the file path of the admin database file to
     * access.
     */
    public AdminController() {
        super(MainController.FILEPATH_ADMIN);
    }

    /**
     * Verifies whether an admin account exists in the admin database with the given
     * username and password.
     * 
     * @param usernameInput Username of admin to be verified.
     * @param passwordInput Password of admin to be verified.
     * @return Returns true if an admin with the given username and password has
     *         been successfully found in the database, else false.
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
     * Returns an admin by searching with the given username in the
     * admin database.
     * 
     * @param username Username of admin to search for.
     * @return Returns admin with matching username if found in database, else null.
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
     * Searches for and deletes admin with the given username in the admin database.
     * 
     * @param username Username of admin to be deleted.
     * @return Returns true if admin is uccessfully deleted, else false.
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
