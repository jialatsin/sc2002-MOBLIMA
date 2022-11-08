package control;

import java.util.ArrayList;

import entity.Booking;

/**
 * Represents a BookingController which contains the logic for handling Booking
 * data.
 */
public class BookingController extends DatabaseController<Booking> {
    /**
     * Creates a BookingController with the file path of the booking database file
     * to access.
     */
    public BookingController() {
        super(MainController.FILEPATH_BOOKING);
    }

    /**
     * Returns every booking with the given email in the booking database.
     * 
     * @param email Email of the moviegoer to find booking history for.
     * @return Returns list of bookings with matching email if found in database,
     *         else null.
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
