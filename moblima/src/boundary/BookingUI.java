package boundary;

import control.PriceController;
import entity.Cineplex;
import entity.SeatingLayout;
import entity.Showing;
import entity.Constants.Age;
import entity.Constants.CinemaClass;
import entity.Constants.Day;
import entity.Constants.MovieType;

public class BookingUI {
    private static PriceController priceController = new PriceController();

    public static void main() {
        // Showing showing = UserHandler.getShowingFromUser();
        // checkSeatAvailability(showing);
        Day day = UserHandler.getDayFromUser();
        MovieType movieType = UserHandler.getMovieTypeFromUser();
        CinemaClass cinemaClass = UserHandler.getCinemaClassFromUser();
        Age age = UserHandler.getAgeFromUser();
        double priceOfTicket = priceController.calculatePriceType(day, movieType, cinemaClass, age);
        System.out.println("The price of ticket is " + priceOfTicket);
    }

    // Prints current seating layout for the given showing
    public static void checkSeatAvailability(Showing showing) {
        if (showing == null) {
            System.out.println("Movie showing does not exist in Showing database!");
            return;
        }
        SeatingLayout seatingAvailability = showing.getSeatingAvailablity();
        System.out.println(seatingAvailability);
    }

}
