package boundary;

import java.time.LocalDateTime;
import control.*;
import entity.*;
import entity.Cineplex;

public class CRUDMovieShowingUI {
    private static ShowingController showingController = new ShowingController();

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
                    SearchShowingUI.main();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

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
        Cineplex cineplex = UserHandler.getCineplexFromUser();
        Cinema cinema = UserHandler.getCinemaFromUser(cineplex);
        if (showingController.findShowing(cinema, showTime) != null) {
            System.out.println("There already exists a showing in cinema " + cinema.getCode()
                    + "at " + showTime.format(InputHandler.getDateTimeFormat()) + "! ");
        }

        SeatingLayout seatingAvailability = cinema.getSeatingLayout();

        if (cineplex == null || cinema == null) {
            System.out.println("Unable to create showing!");
            return;
        }

        showingController.addToDatabase(new Showing(id, seatingAvailability, movie, showTime, cinema, cineplex));
        System.out.println(movie.getTitle() + " at showtime ID: " + id + " added to Showtime database!");
    }

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
                    + "2. seatingAvailability\n"
                    + "3. Movie\n"
                    + "4. ShowTime\n"
                    + "5. Cinema\n");
            selection = InputHandler.scanInt();
        } while (selection < 1 || selection > 5);

        switch (selection) {
            case 1:
                int newId = UserHandler.getIdFromUser();
                showingController.updateShowingAttribute(showing, selection, newId);
                break;
            case 2:
                // TODO: Menu to change available seats
                break;
            case 3:
                Movie movie = UserHandler.getMovieFromUser();
                if (movie == null) {
                    System.out.println("Error updating Movie attribute!");
                    break;
                }
                showingController.updateShowingAttribute(showing, selection, movie);
                break;
            case 4:
                LocalDateTime showTime = UserHandler.getShowTimeFromUser();
                showingController.updateShowingAttribute(showing, selection, showTime);
                break;
            case 5:
                Cineplex cineplex = UserHandler.getCineplexFromUser();
                Cinema cinema = UserHandler.getCinemaFromUser(cineplex);
                if (cinema == null) {
                    System.out.println("Error updating Cinema attribute!");
                    break;
                }
                showingController.updateShowingAttribute(showing, selection, cinema);
                break;
        }

        System.out.println("\nUpdated showing details!");
    }

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
