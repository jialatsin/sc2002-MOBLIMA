package control;

import entity.*;

public class MovieController extends DatabaseController<Movie> {
    public MovieController() {
        super(MainController.FILEPATH_MOVIE);
    }
}
