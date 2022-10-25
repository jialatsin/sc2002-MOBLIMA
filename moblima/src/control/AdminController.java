package control;

import entity.*;

public class AdminController extends DatabaseController<Admin> {

    public AdminController() {
        super(MainController.FILEPATH_ADMIN);
    }
}
