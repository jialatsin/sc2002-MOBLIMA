package control;

import entity.Cineplex;

/**
 * Represents a CineplexController which contains the logic for handling
 * Cineplex data.
 */
public class CineplexController extends DatabaseController<Cineplex> {
    /**
     * Creates a CineplexController with the file path of the cineplex database file
     * to access.
     */
    public CineplexController() {
        super(MainController.FILEPATH_CINEPLEX);
    }
}
