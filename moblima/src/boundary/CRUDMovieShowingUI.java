package boundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import control.*;
import entity.*;
import entity.Constants.CinemaClass;
import entity.Cineplex;

public class CRUDMovieShowingUI {
    private static ShowingController showingController = new ShowingController();
    private static CineplexController cineplexController = new CineplexController();

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

        int id = getIdFromUser();
        SeatingLayout seatingAvailability = getSeatingAvailabilityFromUser(); // TO BE CHANGED WHEN SEATING LAYOUT IS UPDATED
        Movie movie = getMovieFromUser();
        LocalDateTime showTime = getShowTimeFromUser();
        Cineplex cineplex = getCineplexFromUser();
        Cinema cinema = getCinemaFromUser(cineplex);

        showingController.addToDatabase(new Showing(id, seatingAvailability, movie, showTime, cinema, cineplex));

        System.out.println(movie.getTitle() + "at showtime ID: " + id + " added to Showtime database!");
    }

    private static void updateMovieShowing() {
        System.out.println("\nUPDATING A MOVIE SHOWING...");

        int id = getIdFromUser();
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
                    + "5. Cinema\n"
                    + "6. Cineplex\n");
            selection = InputHandler.scanInt();
        } while (selection < 1 || selection > 6);

        switch (selection) {
            case 1:
                int newId = getIdFromUser();
                showingController.updateShowingAttribute(showing, selection, newId);
                break;
            case 2:
                SeatingLayout seatingAvailability = getSeatingAvailabilityFromUser();
                showingController.updateShowingAttribute(showing, selection, seatingAvailability);
                break;
            case 3:
                Movie movie = getMovieFromUser();
                showingController.updateShowingAttribute(showing, selection, movie);
                break;
            case 4:
                LocalDateTime showTime = getShowTimeFromUser();
                showingController.updateShowingAttribute(showing, selection, showTime);
                break;
            case 5:
                Cineplex cineplex = getCineplexFromUser();
                Cinema cinema = getCinemaFromUser(cineplex);
                showingController.updateShowingAttribute(showing, selection, cinema);
                break;
            case 6:
                cineplex = getCineplexFromUser();
                showingController.updateShowingAttribute(showing, selection, cineplex);
                break;
        }

        System.out.println("\nUpdated showing details!");
    }

    private static void deleteMovieShowing() {
        System.out.println("\nDELETING A MOVIE SHOWING...");

        int id = getIdFromUser();
        if (showingController.deleteShowingById(id)) {
            System.out.println("Deleted showing with ID " + id + "!");
        } else {
            System.out.println("Unable to delete showing with ID " + id + "!");
        }
    }

    private static void searchMovieShowings() {
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

    private static int getIdFromUser() {
        System.out.println("\nEnter showing ID:");
        int id = InputHandler.scanInt();
        return id;
    }

    private static SeatingLayout getSeatingAvailabilityFromUser() {
        System.out.println("\nEnter seating layout:");
        int i = 1;
        for (CinemaClass E : java.util.Arrays.asList(CinemaClass.values())) {
            System.out.println(i++ + ". " + E);
        }
        int Selection = InputHandler.scanInt();
        SeatingLayout seatingAvailability = new SeatingLayout(Selection); // TO BE CHANGED WHEN SEATING LAYOUT IS UPDATED
        return seatingAvailability;
    }

    private static Movie getMovieFromUser() {
        System.out.println("\nEnter movie title:");
        String movieTitle = InputHandler.scanString();
        Movie movie = MovieGoerUI.searchMovieObject(movieTitle);
        if (movie == null) {
            System.out.println("Movie not found.");
            return null;
        }
        return movie;
    }

    private static LocalDateTime getShowTimeFromUser() {
        System.out.println("\nEnter show timing 'dd/MM/yyyy HH:mm':");
        LocalDateTime showTime = InputHandler.scanDateTime();
        return showTime;
    }

    private static Cinema getCinemaFromUser(Cineplex cineplex) {
        int n, i;
        System.out.println("\nEnter cinema name:");
        i = 1;
        for (Cinema cinema : cineplex.getCinemas()) {
            System.out.println(i++ + ". " + cinema.getCode());
        }
        n = InputHandler.scanInt();
        Cinema cinema = null;
        while (n < i && n > 0) {
            cinema = cineplex.getCinemas().get(n - 1);
            break;
        }
        return cinema;
    }

    private static Cineplex getCineplexFromUser() {
        int n, i;
        ArrayList<Cineplex> cineplexes = cineplexController.readFromDatabase();
        System.out.println("\nSelect cineplex:");
        i = 1;
        for (Cineplex cineplex : cineplexes) {
            System.out.println(i++ + ". " + cineplex.getName());
        }
        n = InputHandler.scanInt();
        Cineplex cineplex = null;
        while (n < i && n > 0) {
            cineplex = cineplexes.get(n - 1);
            break;
        }
        return cineplex;
    }
}
