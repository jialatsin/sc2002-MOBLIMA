package control;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import entity.*;
/**
 * It Represents the Showing Controller 
 * @author OOP SSP1 Lab Group 4
 * @version 8/11/2022
 */
public class ShowingController extends DatabaseController<Showing> {
	/** 
     * File path name of showing database file to access. 
     */	
	public ShowingController() {
        super(MainController.FILEPATH_SHOWING);
    }

    /**
     * Search for a Showing by its showing id in the Showing database, by using overloaded method.
     * 
     * @param id       The unique Showing id
     * @return showing If successful, it returns tshowing by matching its unique showingid with an id in the database, else null value is returned.
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
     * Search for a Showing by its Cinema and showTime in the Showing database, by using the overloaded method.      * 
     * @param cinema   The selected Cinema
     * @param showTime The Selected showTime
     * @return showing If successful, it returns the showing by matching its unique showingid with an id in the database, else null value is returned.
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
     * Search for Showings with specified Movie and Cineplex in the Showing database, by using the overloaded method.
     * @param cineplex       The selected Cineplex
     * @param movie          The name of the selected movie
     * @return showingResult It returns an array list of matching showings if found, else null. 
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
     * Search for Showings with specified Movie and Cineplex in the Showing database, by using the overloaded method.
     * @param movie          The name of the selected movie
     * @return showingResult It returns an array list of matching showings if found, else null. 
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
     * Search for Showings with specified Cineplex in the Showing database, by using the overloaded method.
     * @param  cineplex         The selected Cineplex
     * @return showingsResult It returns an array list of matching showings if found, else null.
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
     * Deletes all showings with showTimes that no longer fall within the valid showing period from the Showing database. It is Called from CRUDMovieListingUI when releaseDate or endDate is updated.
     * @param movie       The name of the selected movie
     * @param releaseDate The Release date of Showing of the movie in the cinema
     * @param endDate     The End date of Showing of the movie in the cinema
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
     * It is defined for readability and easier referencing of Showing attributes in the order of selection options provided to user when updating showing attributes.
     */
    public enum ShowingAttribute {
        /**
         * The unique showing id of the movie
         */
    	ID,
    	/**
         * The name of the movie currently being showed. 
         */
        MOVIE,
        /**
         * The show time of the movie
         */
        SHOWTIME,
        /**
         * The cinema type of the movie being showed 
         */
        CINEMA;
    	/**
    	 * CHECK THIS AGAIN
    	 * @param i To store the user selection 
    	 * @return values The values generated according to the chosen i. 
    	 */
        public static ShowingAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    /**
     * Updates the selected showing's entry in Showing database with the new attribute.
     * @param showing           The Selected showing of the movie
     * @param attribute         The Showing attribute that is to be updated
     * @param newAttributeValue The New attribute value which updates the showing's etry in the showing database
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
     * It searches for and deletes Showing with showing id in the Showing database, by using overloaded function.
     * @param id       The unique showing id of the movie
     * @return boolean It returns true if successfully deleted , else false
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
     * It searches for and deletes Showing with given movie in the Showing database, by using overloaded function.
     * @param movie The movie of the Showing which is to be deleted. 
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
