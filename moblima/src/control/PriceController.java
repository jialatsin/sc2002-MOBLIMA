package control;

import java.util.ArrayList;
import java.util.Map;

import entity.PriceType;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;

public class PriceController extends DatabaseController<Map<PriceType, Double>> {
    public PriceController() {
        super(MainController.FILEPATH_PRICE);
    }

    /**
     * Calculates the price of a showing ticket according to the current
     * type of Day, movieType, cinemaClass and Age category.
     * Regular 2D MovieType is taken as the base price, while additional fees added
     * onto the base price depending on the type of movie, class of cinema, age of
     * movie-goer and day of the week/public holiday.
     * 
     * @param day         Type of Day
     * @param movieType   Type of movie
     * @param cinemaClass Class of cinema
     * @param age         Age category of MovieGoer
     * @return Calculated price of ticket
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
     * Updates the price in Price database of a given class that implements
     * PriceType that determines the calculated price of a ticket.
     * 
     * @param priceType Selected class that implements PriceType
     * @param price     Corresponding price of the class
     */
    public void updatePriceType(PriceType priceType, double price) {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        Map<PriceType, Double> priceMap = priceMaps.get(0);
        priceMap.put(priceType, price);
        priceMaps.set(0, priceMap);
        overwriteDatabase(priceMaps);
    }

    /**
     * Gets all the classes implementing PriceType and their corresponding prices
     * that determine the calculated price of a ticket.
     * 
     * @return Price map
     */
    public Map<PriceType, Double> getPriceList() {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        return priceMaps.get(0);
    }

}
