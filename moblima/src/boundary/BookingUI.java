package boundary;

import entity.SeatingLayout;
import entity.Showing;

public class BookingUI {
    public static void main() {
        Showing showing = UserHandler.getShowingFromUser();
        checkSeatAvailability(showing);
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
