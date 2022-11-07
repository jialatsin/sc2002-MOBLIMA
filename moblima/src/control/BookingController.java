package control;

import java.util.ArrayList;
import entity.Booking;
/**
 * It Represents the Booking Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class BookingController extends DatabaseController<Booking> {
	/** 
     * File path name of booking database file to access. 
     */
	public BookingController() {
        super(MainController.FILEPATH_BOOKING);
    }

    /**
     * Search for bookings by given email in the Booking database.
     * @param email         The email of the movieGoer
     * @return ArrayList    The array list of matching Bookings if found, else null
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
