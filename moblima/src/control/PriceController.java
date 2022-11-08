package control;

import java.util.ArrayList;
import java.util.Map;

import entity.PriceType;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;

/**
 * Represents a PriceController which contains the logic for handling Price
 * data.
 */
public class PriceController extends DatabaseController<Map<PriceType, Double>> {
    /**
     * Creates a PriceController with the file path of the price database file
     * to access.
     */
    public PriceController() {
        super(MainController.FILEPATH_PRICE);
    }

    /**
     * Calculates the price of a showing ticket according to the current
     * type of day, movie type, cinema class and age category.
     * Regular 2D movie type is taken as the base price, while additional fees added
     * onto the base price depending on the type of movie, cinema class, age
     * category of moviegoer and day of the week/public holiday.
     * 
     * @param day         Type of day the showing is held on.
     * @param movieType   Type of the movie being shown.
     * @param cinemaClass Class of the cinema where the showing is being held.
     * @param age         Age category of moviegoer purchasing the ticket.
     * @return Returns the calculated price of the movie ticket.
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
     * Updates the price in the price database of a given class (eg. Blockbuster 3D,
     * Senior, Holiday) that implements PriceType, which determines the calculated
     * price of a ticket.
     * 
     * @param priceType Selected class that implements PriceType.
     * @param price     Price mapped to the class.
     */
    public void updatePriceType(PriceType priceType, double price) {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        Map<PriceType, Double> priceMap = priceMaps.get(0);
        priceMap.put(priceType, price);
        priceMaps.set(0, priceMap);
        overwriteDatabase(priceMaps);
    }

    /**
     * Gets all the classes (eg. Blockbuster 3D, Senior, Holiday) implementing
     * PriceType and their corresponding prices that determine the calculated price
     * of a ticket.
     * 
     * @return Returns a price list with every class mapped to their corresponding
     *         price.
     */
    public Map<PriceType, Double> getPriceList() {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        return priceMaps.get(0);
    }

}
