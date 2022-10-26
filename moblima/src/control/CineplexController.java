package control;

import entity.Cineplex;

public class CineplexController extends DatabaseController<Cineplex> {
    public CineplexController() {
        super(MainController.FILEPATH_CINEPLEX);
    }
}
