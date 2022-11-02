package control;

import java.util.ArrayList;

import entity.*;

public class AdminController extends DatabaseController<Admin> {

    public AdminController() {
        super(MainController.FILEPATH_ADMIN);
    }

    // Returns true on successful login validation
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

    public Admin getAdminByUsername(String username) {
        ArrayList<Admin> admins = readFromDatabase();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username)) {
                return admin;
            }
        }
        return null;
    }

    // Returns true on successful deletion
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
