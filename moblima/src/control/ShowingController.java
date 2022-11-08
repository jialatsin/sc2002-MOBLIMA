package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.*;

/**
 * Represents a ShowingController which contains the logic for handling Showing
 * data.
 */
public class ShowingController extends DatabaseController<Showing> {
    /**
     * Creates a ShowingController with the file path of the showing database file
     * to access.
     */
    public ShowingController() {
        super(MainController.FILEPATH_SHOWING);
    }

    /**
     * Returns a showing by searching with its showing id in the showing database.
     * Overloaded method.
     * 
     * @param id Id of showing to be searched.
     * @return Returns showing with matching id if found in database, else null.
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
     * Returns a showing by searching with its cinema and showtime in the showing
     * database. Overloaded method.
     * 
     * @param cinema   Cinema of the showing to be searched.
     * @param showTime Showtime of the showing to be searched.
     * @return Returns showing with matching cinema and showtime if found in
     *         database, else null.
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
     * Returns a list of showings by searching with their cineplex and movie in the
     * showing database. Overloaded method.
     * 
     * @param cineplex Cineplex of the showings to be searched.
     * @param movie    Movie of the showings to be searched.
     * @return Returns a list of showings with matching cineplex and movie if found
     *         in database, else null.
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
     * Returns a list of showings by searching with their movie in the
     * showing database. Overloaded method.
     * 
     * @param movie Movie of the showings to be searched.
     * @return Returns a list of showings with matching movie if found
     *         in database, else null.
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
     * Returns a list of showings by searching with their cineplex in the
     * showing database. Overloaded method.
     * 
     * @param cineplex Cineplex of the showings to be searched.
     * @return Returns a list of showings with matching cineplex if found
     *         in database, else null.
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
     * Deletes all showings with showtimes that no longer fall within the
     * valid showing period from the Showing database, for the given movie. A
     * movie's showing period is from 7 days before its release date to its end
     * date.
     * Called from CRUDMovieListingUI when releaseDate or endDate attribute is
     * updated.
     * 
     * @param movie       Selected movie.
     * @param releaseDate Release date of the selected movie.
     * @param endDate     End date of selected movie.
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
        /** Unique identifier for the showing. */
        ID,
        /** Movie being shown. */
        MOVIE,
        /** Showtime of the showing. */
        SHOWTIME,
        /** Cinema where the showing is being held. */
        CINEMA;

        /**
         * Returns the mapping of the user input selection to the attribute enumerator.
         * User selection starts from 1, but enumerator counting starts from 0.
         * 
         * @param i Input user selection.
         * @return Returns the mapping of the user input selection to the attribute
         *         enumerator.
         */
        public static ShowingAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    /**
     * Updates the selected showing's entry in Showing database with the given new
     * attribute.
     * 
     * @param showing           Showing to be updated.
     * @param attribute         Showing attribute to be updated.
     * @param newAttributeValue New attribute value.
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
     * Deletes showing by searching with the given showing id in the showing
     * database. Overloaded function.
     * 
     * @param id Id of the showing to be deleted.
     * @return Returns true if showing is successfully deleted, else false.
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
     * Deletes showing by searching with the given movie in the showing
     * database. Overloaded function.
     * 
     * @param movie Movie of the showing to be deleted.
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
