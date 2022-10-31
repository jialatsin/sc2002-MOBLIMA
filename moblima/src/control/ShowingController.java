package control;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.*;

public class ShowingController extends DatabaseController<Showing> {
    public ShowingController() {
        super(MainController.FILEPATH_SHOWING);
    }

    public Showing getShowingById(int id) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getId() == id) {
                return showing;
            }
        }
        return null;
    }

    private enum Attributes {
        ID, SEATINGAVAILABILITY, MOVIE, SHOWTIME, CINEMA, CINEPLEX
    }

    @SuppressWarnings("unchecked")
    public void updateShowingAttribute(Showing showing, int attribute, Object newAttributeValue) {
        ArrayList<Showing> showings = readFromDatabase();
        int showingIndexInDatabase = showings.indexOf(showing);

        Attributes[] attributes = Attributes.values();
        switch (attributes[attribute - 1]) {
            case ID:
                showing.setId((int) newAttributeValue);
                break;
            case SEATINGAVAILABILITY:
                showing.setSeatingAvailablity((SeatingLayout) newAttributeValue);
                break;
            case MOVIE:
                showing.setMovie((Movie) newAttributeValue);
                break;
            case SHOWTIME:
                showing.setShowTime((LocalDateTime) newAttributeValue);
                break;
            case CINEMA:
                showing.setCinema((Cinema) newAttributeValue);
                break;
            case CINEPLEX:
                showing.setCineplex((Cineplex) newAttributeValue);
                break;
        }
        showings.set(showingIndexInDatabase, showing);
        overwriteDatabase(showings);
    }

    public boolean deleteShowingById(int id) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getId() == id) {
                showings.remove(showing);
                overwriteDatabase(showings);
                return true;
            }
        }
        return false;
    }
}
