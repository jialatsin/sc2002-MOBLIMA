package control;

import entity.*;

public class BookingController extends DatabaseController<Booking>{
    public BookingController() {
        super(MainController.FILEPATH_MOVIE);
    }
}
