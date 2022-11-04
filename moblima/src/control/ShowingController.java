package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.*;

public class ShowingController extends DatabaseController<Showing> {
    public ShowingController() {
        super(MainController.FILEPATH_SHOWING);
    }

    /**
     * Search for a Showing by its showing id in the Showing database.
     * Overloaded method.
     * 
     * @param id Showing id
     * @return Matching Showing if found, else null
     */
    public Showing findShowing(int id) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getId() == id) {
                return showing;
            }
        }
        return null;
    }

    /**
     * Search for a Showing by its Cinema and showTime in the Showing database.
     * Overloaded method.
     * 
     * @param cinema   Selected Cinema
     * @param showTime Selected showTime
     * @return Matching Showing if found, else null
     */
    public Showing findShowing(Cinema cinema, LocalDateTime showTime) {
        ArrayList<Showing> showings = readFromDatabase();
        for (Showing showing : showings) {
            if (showing.getCinema().equals(cinema) && showing.getShowTime().equals(showTime)) {
                return showing;
            }
        }
        return null;
    }

    /**
     * Search for Showings with specified Movie and Cineplex in the Showing
     * database.
     * Overloaded method.
     * 
     * @param cineplex Selected Cineplex
     * @param movie    Selected Movie
     * @return ArrayList of matching showings if found, else null
     */
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

    /**
     * Search for Showings with specified Movie in the Showing database.
     * Overloaded method.
     * 
     * @param movie Selected Movie
     * @return ArrayList of matching showings if found, else null
     */
    public ArrayList<Showing> findShowings(Movie movie) {
        ArrayList<Showing> showings = readFromDatabase();
        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            if (showing.getMovie().equals(movie)) {
                showingsResult.add(showing);
            }
        }

        if (showingsResult.isEmpty()) {
            return null;
        }
        return showingsResult;
    }

    /**
     * Search for Showings with specified Cineplex in the Showing database.
     * Overloaded method.
     * 
     * @param movie Selected Cineplex
     * @return ArrayList of matching showings if found, else null
     */
    public ArrayList<Showing> findShowings(Cineplex cineplex) {
        ArrayList<Showing> showings = readFromDatabase();
        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            if (showing.getCineplex().equals(cineplex)) {
                showingsResult.add(showing);
            }
        }

        if (showingsResult.isEmpty()) {
            return null;
        }
        return showingsResult;
    }

    /**
     * Deletes all showings with showTimes that no longer fall within the valid
     * showing period from the Showing database.
     * Called from CRUDMovieListingUI when releaseDate or endDate is updated.
     * 
     * @param movie       Selected Showing
     * @param releaseDate Release date of Showing
     * @param endDate     End date of Showing
     */
    public void deleteInvalidShowings(Movie movie, LocalDate releaseDate, LocalDate endDate) {
        ArrayList<Showing> allShowings = readFromDatabase();
        ArrayList<Showing> validShowings = new ArrayList<Showing>();

        for (Showing showing : allShowings) {
            if (!showing.getMovie().equals(movie)) {
                validShowings.add(showing);
            }
            // Valid showtime is before end date
            // and after 7 days before release date
            else if (showing.getShowTime().toLocalDate().isBefore(endDate)
                    && showing.getShowTime().toLocalDate().isAfter(releaseDate.minusDays(7))) {
                validShowings.add(showing);
            }
        }
        overwriteDatabase(validShowings);
    }

    /**
     * Defined for readability and easier referencing of Showing attributes.
     * In the order of selection options provided to user when updating showing
     * attributes.
     */
    public enum ShowingAttribute {
        ID, MOVIE, SHOWTIME, CINEMA;

        public static ShowingAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    /**
     * Updates the selected showing's entry in Showing database with the new
     * attribute.
     * 
     * @param movie             Selected showing
     * @param attribute         Showing attribute to be updated
     * @param newAttributeValue New attribute value
     */
    public void updateShowingAttribute(Showing showing, ShowingAttribute attribute, Object newAttributeValue) {
        ArrayList<Showing> showings = readFromDatabase();
        int showingIndexInDatabase = showings.indexOf(showing);

        switch (attribute) {
            case ID:
                showing.setId((int) newAttributeValue);
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
        }
        showings.set(showingIndexInDatabase, showing);
        overwriteDatabase(showings);
    }

    /**
     * Search for and deletes Showing with showing id in the Showing database.
     * Overloaded function.
     * 
     * @param id Showing id
     * @return true if successfully deleted, else false
     */
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

    /**
     * Search for and deletes Showing with given Movie in the Showing database.
     * Overloaded function.
     * 
     * @param movie Movie of the Showing
     */
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
