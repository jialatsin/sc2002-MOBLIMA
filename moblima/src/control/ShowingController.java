package control;

import entity.*;

public class ShowingController extends DatabaseController<Showing>{
    public ShowingController() {
        super(MainController.FILEPATH_MOVIE);
    }
}
