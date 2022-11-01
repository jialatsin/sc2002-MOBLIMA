package control;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import entity.Cinema;
import entity.Cineplex;
import entity.SeatingLayout;
import entity.Constants.Age;
import entity.Constants.CinemaClass;
import entity.Constants.Day;
import entity.Constants.MovieType;
import entity.PriceType;

public class MainController {

    public static final String FILEPATH_ADMIN = "moblima/db/admin.txt";
    public static final String FILEPATH_MOVIE = "moblima/db/movie.txt";
    public static final String FILEPATH_HOLIDAY = "moblima/db/holiday.txt";
    public static final String FILEPATH_CINEPLEX = "moblima/db/cineplex.txt";
    public static final String FILEPATH_CINEMA = "moblima/db/cinema.txt";
    public static final String FILEPATH_SHOWING = "moblima/db/showing.txt";
    public static final String FILEPATH_PRICE = "moblima/db/price.txt";

    private static CineplexController cineplexController = new CineplexController();
    private static PriceController priceController = new PriceController();

    public static void start() {
        // Initialize database

        ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
        cineplexes = cineplexController.readFromDatabase();
        if (cineplexes.size() == 0) {
            // TODO: Create seating layouts with aisles, stairs
            // TODO: Admin menu to manage Cineplexes, Cinemas and SeatLayouts??
            // Currently hard-coded data
            ArrayList<Cinema> cinemasA = new ArrayList<Cinema>();
            cinemasA.add(new Cinema("APL", new SeatingLayout(5, 5), CinemaClass.PLATINUM));
            cinemasA.add(new Cinema("AGO", new SeatingLayout(15, 10), CinemaClass.GOLD));
            cinemasA.add(new Cinema("AST", new SeatingLayout(15, 25), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("GV Jurong Point", "Jurong", cinemasA));

            ArrayList<Cinema> cinemasB = new ArrayList<Cinema>();
            cinemasB.add(new Cinema("APL", new SeatingLayout(6, 6), CinemaClass.PLATINUM));
            cinemasB.add(new Cinema("AGO", new SeatingLayout(10, 15), CinemaClass.GOLD));
            cinemasB.add(new Cinema("AST", new SeatingLayout(20, 20), CinemaClass.STANDARD));
            cineplexes.add(new Cineplex("Cathay Cineplex Cineleisure", "Cineleisure Orchard", cinemasB));

            cineplexController.overwriteDatabase(cineplexes);
        }
        
        ArrayList<Map<PriceType,Double>> priceMaps = new ArrayList<Map<PriceType,Double>>();
        priceMaps = priceController.readFromDatabase();
        if (priceMaps.size() == 0) {
            Map<PriceType,Double> priceMap = new HashMap<PriceType, Double>();
            priceMap.put(MovieType.REGULAR_TWO_D, 8.50);        //2D Base Price: 8.50
            priceMap.put(MovieType.REGULAR_THREE_D, 3.00);      //3D Surcharge: 3.00
            priceMap.put(MovieType.BLOCKBUSTER_TWO_D, 2.00);    //BlockBuster_2D Surcharge: 2.00
            priceMap.put(MovieType.BLOCKBUSTER_THREE_D, 5.00);  //BlockBuster_3D Surcharge: 5.00
            priceMap.put(CinemaClass.PLATINUM, 5.00);           //Platinum Surcharge: 5.00
            priceMap.put(CinemaClass.GOLD, 2.00);               //Gold Surcharge: 2.00
            priceMap.put(CinemaClass.STANDARD, 0.0);            //Standard Surcharge: 0.00
            priceMap.put(Age.ADULT, 0.00);                      //Adult Surcharge: 0.00
            priceMap.put(Age.CHILD, -2.00);                      //Child Discount: 2.00
            priceMap.put(Age.SENIOR, -4.00);                     //Senior Discount: 4.00
            priceMap.put(Day.HOLIDAY, 5.00);                    //Holiday Surcharge: 5.00
            priceMap.put(Day.WEEKDAY, 0.00);                    //Weekday Surcharge: 0.00
            priceMap.put(Day.WEEKEND, 5.00);                    //Weekend Surcharge: 5.00
            priceMaps.add(priceMap);

            priceController.overwriteDatabase(priceMaps);
        }
    }

}
