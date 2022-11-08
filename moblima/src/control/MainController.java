package control;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import entity.Admin;
import entity.Cinema;
import entity.Cineplex;
import entity.SeatingLayout;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;
import entity.PriceType;

/**
 * It Represents the Main Controller and it contains path names of Database files to access, and initializes starting data for some database files.
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class MainController {
	 /**
     * File name of admin Database file to access 
     */
    public static final String FILEPATH_ADMIN = "moblima/db/admin.txt";
    /**
     * File name of movie Database file to access
     */
    public static final String FILEPATH_MOVIE = "moblima/db/movie.txt";
    /**
     * File name of holiday Database file to access
     */
    public static final String FILEPATH_HOLIDAY = "moblima/db/holiday.txt";
    /**
     * File name of cineplex Database file to access
     */
    public static final String FILEPATH_CINEPLEX = "moblima/db/cineplex.txt";
    /**
     * File name of cinema Database file to access
     */
    public static final String FILEPATH_CINEMA = "moblima/db/cinema.txt";
    /**
     * File name of showing Database file to access
     */
    public static final String FILEPATH_SHOWING = "moblima/db/showing.txt";
    /**
     * File name of price Database file to access
     */
    public static final String FILEPATH_PRICE = "moblima/db/price.txt";
    /**
     * File name of booking Database file to access
     */
    public static final String FILEPATH_BOOKING = "moblima/db/booking.txt";
    /**
     * The cineplex controller 
     */
    private static CineplexController cineplexController = new CineplexController();
    /**
     * The price controller 
     */
    private static PriceController priceController = new PriceController();
    /**
     * The admin controller 
     */
    private static AdminController adminController = new AdminController();

    /**
     * It Creates starting data for admins, cineplexes and pricing if not already available in database
     */
    public static void initializeDatabase() {
        // Initialize admin database
        ArrayList<Admin> admins = new ArrayList<Admin>();
        admins = adminController.readFromDatabase();
        if (admins.size() == 0) {
            Admin admin = new Admin("admin", "pw123");
            admins.add(admin);
            adminController.overwriteDatabase(admins);
        }
        
        /**
         * Intiialize cineplex database
         */
        ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
        cineplexes = cineplexController.readFromDatabase();
        if (cineplexes.size() == 0) {
            // Currently hard-coded data
            ArrayList<Cinema> cinemasA = new ArrayList<Cinema>();
            cinemasA.add(new Cinema("APA", new SeatingLayout(4, 5), CinemaClass.PLATINUM));
            cinemasA.add(new Cinema("AGA", new SeatingLayout(7, 6), CinemaClass.GOLD));
            cinemasA.add(new Cinema("ASA", new SeatingLayout(8, 10), CinemaClass.STANDARD));
            cinemasA.add(new Cinema("ASB", new SeatingLayout(10, 10), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("GV Jurong Point", "Jurong", cinemasA));

            ArrayList<Cinema> cinemasB = new ArrayList<Cinema>();
            cinemasB.add(new Cinema("BPA", new SeatingLayout(6, 6), CinemaClass.PLATINUM));
            cinemasB.add(new Cinema("BGA", new SeatingLayout(8, 7), CinemaClass.GOLD));
            cinemasB.add(new Cinema("BSA", new SeatingLayout(9, 9), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("Cathay Cineplex Cineleisure", "Cineleisure Orchard", cinemasB));

            ArrayList<Cinema> cinemasC = new ArrayList<Cinema>();
            cinemasC.add(new Cinema("CPA", new SeatingLayout(4, 4), CinemaClass.PLATINUM));
            cinemasC.add(new Cinema("CGA", new SeatingLayout(5, 7), CinemaClass.GOLD));
            cinemasC.add(new Cinema("CSA", new SeatingLayout(10, 8), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("Cathay Cineplex AMK Hub", "AMK Hub", cinemasC));

            cineplexController.overwriteDatabase(cineplexes);
        }

        // Intiialize price database
        ArrayList<Map<PriceType, Double>> priceMaps = new ArrayList<Map<PriceType, Double>>();
        priceMaps = priceController.readFromDatabase();
        if (priceMaps.size() == 0) {
            Map<PriceType, Double> priceMap = new HashMap<PriceType, Double>();
            priceMap.put(MovieType.REGULAR_TWO_D, 8.50); // 2D Base Price: 8.50
            priceMap.put(MovieType.REGULAR_THREE_D, 3.00); // 3D Surcharge: 3.00
            priceMap.put(MovieType.BLOCKBUSTER_TWO_D, 2.00); // BlockBuster_2D Surcharge: 2.00
            priceMap.put(MovieType.BLOCKBUSTER_THREE_D, 5.00); // BlockBuster_3D Surcharge: 5.00
            priceMap.put(CinemaClass.PLATINUM, 5.00); // Platinum Surcharge: 5.00
            priceMap.put(CinemaClass.GOLD, 2.00); // Gold Surcharge: 2.00
            priceMap.put(CinemaClass.STANDARD, 0.0); // Standard Surcharge: 0.00
            priceMap.put(Age.ADULT, 0.00); // Adult Surcharge: 0.00
            priceMap.put(Age.CHILD, -2.00); // Child Discount: 2.00
            priceMap.put(Age.SENIOR, -4.00); // Senior Discount: 4.00
            priceMap.put(Day.HOLIDAY, 5.00); // Holiday Surcharge: 5.00
            priceMap.put(Day.WEEKDAY, 0.00); // Weekday Surcharge: 0.00
            priceMap.put(Day.WEEKEND, 5.00); // Weekend Surcharge: 5.00
            priceMaps.add(priceMap);

            priceController.overwriteDatabase(priceMaps);
        }
    }

}
