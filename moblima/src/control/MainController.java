package control;

import java.util.ArrayList;

import entity.Cinema;
import entity.Cineplex;
import entity.SeatingLayout;
import entity.Constants.CinemaClass;

public class MainController {

    public static final String FILEPATH_ADMIN = "moblima/db/admin.txt";
    public static final String FILEPATH_MOVIE = "moblima/db/movie.txt";
    public static final String FILEPATH_HOLIDAY = "moblima/db/holiday.txt";
    public static final String FILEPATH_CINEPLEX = "moblima/db/cineplex.txt";
    public static final String FILEPATH_CINEMA = "moblima/db/cinema.txt";
    public static final String FILEPATH_SHOWING = "moblima/db/showing.txt";

    private static CineplexController cineplexController = new CineplexController();

    public static void start() {
        // Initialize database

        ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
        cineplexes = cineplexController.readFromDatabase();
        if (cineplexes.size() == 0) {
            // TODO: Create seating layouts with aisles, stairs
            // TODO: Admin menu to manage Cineplexes, Cinemas and SeatLayouts??
            // Currently hard-coded data
            ArrayList<Cinema> cinemasA = new ArrayList<Cinema>();
            cinemasA.add(new Cinema("APL", new SeatingLayout(10, 10), CinemaClass.PLATINUM));
            cinemasA.add(new Cinema("AGO", new SeatingLayout(20, 20), CinemaClass.GOLD));
            cinemasA.add(new Cinema("AST", new SeatingLayout(30, 30), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("GV Jurong Point", "Jurong", cinemasA));

            ArrayList<Cinema> cinemasB = new ArrayList<Cinema>();
            cinemasB.add(new Cinema("APL", new SeatingLayout(5, 5), CinemaClass.PLATINUM));
            cinemasB.add(new Cinema("AGO", new SeatingLayout(10, 10), CinemaClass.GOLD));
            cinemasB.add(new Cinema("AST", new SeatingLayout(15, 15), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("Cathay Cineplex Cineleisure", "Cineleisure Orchard", cinemasB));

            cineplexController.overwriteDatabase(cineplexes);
        }
    }
}
