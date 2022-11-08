package control;

import java.util.ArrayList;

import java.util.Map;

import entity.PriceType;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;
/**
 * It Represents the Price Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class PriceController extends DatabaseController<Map<PriceType, Double>> {
	/** 
     * File path name of price database file to access. 
     */
	public PriceController() {
        super(MainController.FILEPATH_PRICE);
    }

    /**
     * It Calculates the price of a showing ticket according to the current type of Day, movieType, cinemaClass and Age category. Regular 2D MovieType is taken as the base price, while additional fees added onto the base price depending on the type of movie, class of cinema, age of
     * movie-goer and day of the week/public holiday.
     * @param day         The type of day as in, whether it is a Weekday, Weekend or a Holiday.
     * @param movieType   The type of movie as in whether it is a Regular 2D, Blockbuster 3D etc. 
     * @param cinemaClass The class of cinema as in whether it is a Platinum, Gold or Standard. 
     * @param age         The Age category of the MovieGoer.
     * @return price      The calculated price of the movie ticket.
     */
    public double calculatePriceType(Day day, MovieType movieType, CinemaClass cinemaClass, Age age) {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        Map<PriceType, Double> priceMap = priceMaps.get(0);

        double price = 0;
        if (!movieType.equals(MovieType.REGULAR_TWO_D)) {
            price = priceMap.get(movieType);
        }
        price += (priceMap.get(MovieType.REGULAR_TWO_D)
                + priceMap.get(day)
                + priceMap.get(cinemaClass)
                + priceMap.get(age));

        return price;
    }

    /**
     * It updates the price in Price database of a given class that implements PriceType that determines the calculated price of a ticket.
     * @param priceType It is selected class that implements PriceType
     * @param price     The corresponding price of the ticket
     */
    public void updatePriceType(PriceType priceType, double price) {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        Map<PriceType, Double> priceMap = priceMaps.get(0);
        priceMap.put(priceType, price);
        priceMaps.set(0, priceMap);
        overwriteDatabase(priceMaps);
    }

    /**
     * Gets all the classes implementing PriceType and their corresponding prices that determine the calculated price of a ticket.
     * @return priceMaps The total calculated price of the ticket
     */
    public Map<PriceType, Double> getPriceList() {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        return priceMaps.get(0);
    }

}
