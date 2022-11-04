package control;

import java.util.ArrayList;

import entity.Booking;

public class BookingController extends DatabaseController<Booking> {
    public BookingController() {
        super(MainController.FILEPATH_BOOKING);
    }

    /**
     * Search for bookings by given email in the Booking database.
     * 
     * @param email Email of the MovieGoer
     * @return ArrayList of matching Bookings if found, else null
     */
    public ArrayList<Booking> findBookings(String email) {
        ArrayList<Booking> bookings = readFromDatabase();
        ArrayList<Booking> bookingsResult = new ArrayList<Booking>();
        for (Booking booking : bookings) {
            if (booking.getMovieGoer().getEmail().equalsIgnoreCase(email)) {
                bookingsResult.add(booking);
            }
        }

        if (bookingsResult.isEmpty()) {
            return null;
        }
        return bookingsResult;
    }
}
