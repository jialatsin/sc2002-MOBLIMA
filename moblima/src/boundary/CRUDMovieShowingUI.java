package boundary;

import java.time.LocalDateTime;
import control.*;
import control.ShowingController.ShowingAttribute;
import entity.*;
import entity.Enumerators.ShowingStatus;
import entity.Enumerators.User;

/** Admin interface to create, update, delete, list Showings. */
public class CRUDMovieShowingUI {
    private static ShowingController showingController = new ShowingController();

    /** Main Menu for CRUD Movie Showings. */
    public static void main() {
        int selection;
        do {
            System.out.println("\n===== MOVIE SHOWING =====\n"
                    + "1. Create Movie Showing\n"
                    + "2. Update Movie Showing\n"
                    + "3. Remove Movie Showing\n"
                    + "4. Search/List Showings\n"
                    + "0. Return to Admin Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    createMovieShowing();
                    break;
                case 2:
                    updateMovieShowing();
                    break;
                case 3:
                    deleteMovieShowing();
                    break;
                case 4:
                    SearchShowingUI.main(User.ADMIN);
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    /**
     * Creates a Showing with user inputted attributes and adds it to the Showing
     * database.
     */
    private static void createMovieShowing() {
        System.out.println("\nCREATING A MOVIE SHOWING...");

        int id = UserHandler.getIdFromUser();
        if (showingController.findShowing(id) != null) {
            System.out.println("Showing of ID " + id + " already exists in Showing database!");
            return;
        }
        Movie movie = UserHandler.getMovieFromUser();
        if (movie == null) {
            System.out.println("Movie of ID " + id + " does not exist in Movie database!");
            return;
        }

        LocalDateTime showTime = UserHandler.getShowTimeFromUser();

        if (showTime.toLocalDate().isAfter(movie.getEndDate())
                || showTime.toLocalDate().isBefore(movie.getReleaseDate().minusDays(7))) {
            System.out.println("Cannot create showing when movie is not within releaseDate and endDates!");
            return;
        }

        Cineplex cineplex = UserHandler.getCineplexFromUser();
        Cinema cinema = UserHandler.getCinemaFromUser(cineplex);
        if (showingController.findShowing(cinema, showTime) != null) {
            System.out.println("There already exists a showing in cinema " + cinema.getCode()
                    + "at " + showTime.format(InputHandler.getDateTimeFormat()) + "! ");
            return;
        }

        SeatingLayout seatingAvailability = cinema.getSeatingLayout();

        if (cineplex == null || cinema == null) {
            System.out.println("Unable to create showing!");
            return;
        }

        showingController.addToDatabase(new Showing(id, seatingAvailability, movie, showTime, cinema, cineplex));
        System.out.println(movie.getTitle() + " at showtime ID: " + id + " added to Showtime database!");
    }

    /**
     * Updates a Showing with user inputted new attribute and updates the Showing's
     * entry in the Showing database.
     */
    private static void updateMovieShowing() {
        System.out.println("\nUPDATING A MOVIE SHOWING...");

        int id = UserHandler.getIdFromUser();
        Showing showing = showingController.findShowing(id);
        if (showing == null) {
            System.out.println("Showing of ID " + id + " does not exist in Showing database!");
            return;
        }

        System.out.println(showing);

        int selection;
        do {
            System.out.println("\nSelect attribute to update for showing\n"
                    + "1. ID\n"
                    + "2. Movie\n"
                    + "3. ShowTime\n"
                    + "4. Cinema\n");
            selection = InputHandler.scanInt();
        } while (selection < 1 || selection > 4);

        ShowingAttribute attribute = ShowingAttribute.get(selection);
        switch (attribute) {
            case ID:
                int newId = UserHandler.getIdFromUser();
                if (showingController.findShowing(newId) != null) {
                    System.out.println("Showing of ID " + id + " already exists in Showing database!");
                    return;
                }
                showingController.updateShowingAttribute(showing, attribute, newId);
                break;
            case MOVIE:
                Movie movie = UserHandler.getMovieFromUser();
                if (movie == null) {
                    System.out.println("Error updating Movie attribute!");
                    return;
                }
                if (showing.getShowTime().toLocalDate().isAfter(movie.getEndDate())
                        || showing.getShowTime().toLocalDate().isBefore(movie.getReleaseDate().minusDays(7))) {
                    System.out.println("Cannot create showing when movie is not within releaseDate and endDates!");
                    return;
                }
                if (!movie.getShowingStatus().equals(ShowingStatus.PREVIEW)
                        && !movie.getShowingStatus().equals(ShowingStatus.NOW_SHOWING)) {
                    System.out.println("Can not update to a movie that is not in 'Preview' or 'Now Showing'!");
                    return;
                }
                showingController.updateShowingAttribute(showing, attribute, movie);
                break;
            case SHOWTIME:
                LocalDateTime showTime = UserHandler.getShowTimeFromUser();
                movie = showing.getMovie();
                if (showTime.toLocalDate().isAfter(movie.getEndDate())
                        || showTime.toLocalDate().isBefore(movie.getReleaseDate())) {
                    System.out.println("Cannot update showing when movie is not within releaseDate and endDates!");
                    return;
                }
                showingController.updateShowingAttribute(showing, attribute, showTime);
                break;
            case CINEMA:
                Cineplex cineplex = UserHandler.getCineplexFromUser();
                Cinema cinema = UserHandler.getCinemaFromUser(cineplex);

                if (cinema == null) {
                    System.out.println("Error updating Cinema attribute!");
                    return;
                }
                if (showingController.findShowing(cinema, showing.getShowTime()) != null) {
                    System.out.println("There already exists a showing in cinema " + cinema.getCode()
                            + "at " + showing.getShowTime().format(InputHandler.getDateTimeFormat()) + "! ");
                    return;
                }
                showingController.updateShowingAttribute(showing, attribute, cinema);
                break;
        }

        System.out.println("\nUpdated showing details!");
    }

    /**
     * Deletes a Showing with selected showing id and deletes the Showing's entry in
     * the Showing database.
     */
    private static void deleteMovieShowing() {
        System.out.println("\nDELETING A MOVIE SHOWING...");

        int id = UserHandler.getIdFromUser();
        if (showingController.deleteShowing(id)) {
            System.out.println("Deleted showing with ID " + id + "!");
        } else {
            System.out.println("Unable to delete showing with ID " + id + "!");
        }
    }

}
