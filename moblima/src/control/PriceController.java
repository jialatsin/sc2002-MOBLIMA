package control;

import java.util.ArrayList;
import java.util.Map;

import entity.PriceType;
import entity.Constants.Age;
import entity.Constants.CinemaClass;
import entity.Constants.Day;
import entity.Constants.MovieType;

public class PriceController extends DatabaseController<Map<PriceType, Double>> {
    public PriceController() {
        super(MainController.FILEPATH_PRICE);
    }

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

    public void updatePriceType(PriceType priceType, double price) {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        Map<PriceType, Double> priceMap = priceMaps.get(0);
        priceMap.put(priceType, price);
        priceMaps.set(0, priceMap);
        overwriteDatabase(priceMaps);
    }

    public Map<PriceType, Double> getPriceList() {
        ArrayList<Map<PriceType, Double>> priceMaps = readFromDatabase();
        return priceMaps.get(0);
    }

}
