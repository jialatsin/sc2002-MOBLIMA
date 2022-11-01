package control;

import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.*;

public class ShowingController extends DatabaseController<Showing> {
    public ShowingController() {
        super(MainController.FILEPATH_SHOWING);
    }

    // Search for a showing by its showing id in the Showing database
    // Returns the matching showing, returns null if showing not found
    public Showing findShowing(int id) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getId() == id) {
                return showing;
            }
        }
        return null;
    }

    // Search for a showing by its cinema and showtime in the Showing database
    // Returns the matching showing, returns null if showing not found
    public Showing findShowing(Cinema cinema, LocalDateTime showTime) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getCinema() == cinema && showing.getShowTime() == showTime) {
                return showing;
            }
        }
        return null;
    }

    // Search for showings with specified movie and cineplex in the Showing database
    // Returns an ArrayList with matching showings, returns null if no showing found
    public ArrayList<Showing> findShowings(Cineplex cineplex, Movie movie) {
        ArrayList<Showing> showings = readFromDatabase();
        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            if (showing.getCineplex().equals(cineplex) && showing.getMovie().equals(movie)) {
                showingsResult.add(showing);
            }
        }

        if (showingsResult.isEmpty()) {
            return null;
        }
        return showingsResult;
    }

    private enum Attributes {
        ID, SEATINGAVAILABILITY, MOVIE, SHOWTIME, CINEMA, CINEPLEX
    }

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

    // Search for and deletes showing with showing id in the Showing database
    // Returns true on successful deletion
    public boolean deleteShowing(int id) {
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

    // Search for and deletes all showings with given movie in Showing database
    public void deleteShowing(Movie movie) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getMovie().equals(movie)) {
                showings.remove(showing);
            }
        }
        overwriteDatabase(showings);
    }
}
