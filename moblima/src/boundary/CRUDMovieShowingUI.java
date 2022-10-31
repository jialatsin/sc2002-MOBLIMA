package boundary;

import java.time.LocalDateTime;
import java.util.*;
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
                    searchMovieShowings();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    private static void createMovieShowing() {
        System.out.println("\nCREATING A MOVIE SHOWING...");

        int id = UserHandler.getIdFromUser();
        Movie movie = UserHandler.getMovieFromUser();
        LocalDateTime showTime = UserHandler.getShowTimeFromUser();
        Cineplex cineplex = UserHandler.getCineplexFromUser();
        Cinema cinema = UserHandler.getCinemaFromUser(cineplex);
        SeatingLayout seatingAvailability = cinema.getSeatingLayout();

        showingController.addToDatabase(new Showing(id, seatingAvailability, movie, showTime, cinema, cineplex));

        System.out.println(movie.getTitle() + " at showtime ID: " + id + " added to Showtime database!");
    }

    private static void updateMovieShowing() {
        System.out.println("\nUPDATING A MOVIE SHOWING...");

        int id = UserHandler.getIdFromUser();
        Showing showing = showingController.getShowingById(id);
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
        if (showingController.deleteShowingById(id)) {
            System.out.println("Deleted showing with ID " + id + "!");
        } else {
            System.out.println("Unable to delete showing with ID " + id + "!");
        }
    }

    public static void searchMovieShowings() {
        do {
            System.out.println("===== SEARCH/LIST SHOWING =====\n"
                    + "1. Search By Showing code\n"
                    + "2. List All Showings\n"
                    + "0. Return\n");
            int choice = InputHandler.scanInt();
            switch (choice) {
                case 1:
                    System.out.printf("Input showing code: ");
                    int id = InputHandler.scanInt();
                    Showing showObject = searchShowingObject(id);
                    if (showObject == null)
                        break;
                    System.out.println("\n=================================================");
                    System.out.println(
                            "Showing id=" + showObject.getId() + "\nmovieTitle=" + showObject.getMovie().getTitle()
                                    + ", movieID=" + showObject.getMovie().getId()
                                    + "\nshowTime=" + showObject.getShowTime() + "\ncineplex="
                                    + showObject.getCineplex().getName() + ", cinema="
                                    + showObject.getCinema());
                    System.out.println("=================================================");
                    break;
                case 2:
                    listAll();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Wrong input");
            }
        } while (true);
    }

    public static Showing searchShowingObject(int id) {
        ArrayList<Showing> showingList = showingController.readFromDatabase();
        for (Showing i : showingList) {
            if (id == i.getId()) { // Searches for all movies with
                return i;
            }
        }
        System.out.println("Movie not found"); // Returns if no showing is found with given id
        return null;
    }

    public static void listAll() {
        ArrayList<Showing> showingList = showingController.readFromDatabase();
        System.out.println("\n=================================================");
        for (Showing showing : showingList) {
            System.out.println("Showing id=" + showing.getId() + "\nmovieTitle=" + showing.getMovie().getTitle()
                    + ", movieID=" + showing.getMovie().getId()
                    + "\nshowTime=" + showing.getShowTime() + "\ncineplex=" + showing.getCineplex().getName()
                    + ", cinema="
                    + showing.getCinema());
            System.out.println("=================================================");
        }
    }

    public static void listAll(Cineplex cineplex, Movie movie) { // Overloaded listAll function to find all showings
                                                                 // with specific cineplex and movie. Method called in
                                                                 // MovieGoerUI.checkSeatAvailability
        ArrayList<Showing> showingList = showingController.readFromDatabase();
        int showingFound = 0;
        System.out.println("\n=================================================");
        for (Showing showing : showingList) {
            if ((showing.getCineplex().equals(cineplex)) && (showing.getMovie().equals(movie))) {
                showingFound = 1;
                System.out.println("Showing id=" + showing.getId() + "\nmovieTitle=" + showing.getMovie().getTitle()
                        + ", movieID=" + showing.getMovie().getId()
                        + "\nshowTime=" + showing.getShowTime() + "\ncineplex=" + showing.getCineplex().getName()
                        + ", cinema="
                        + showing.getCinema());
                System.out.println("=================================================");
            }
        }
        if (showingFound == 0) {
            System.out.println("No showing of " + movie.getTitle() + " is found at " + cineplex.getName());
        }
    }
}
