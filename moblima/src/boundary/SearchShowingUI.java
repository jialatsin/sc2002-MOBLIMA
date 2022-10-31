package boundary;

import java.util.ArrayList;

import control.ShowingController;
import entity.Cineplex;
import entity.Movie;
import entity.Showing;

public class SearchShowingUI {
    private static ShowingController showingController = new ShowingController();

    public static void main() {
        do {
            System.out.println("===== SEARCH/LIST SHOWING =====\n"
                    + "1. Search By Showing code\n"
                    + "2. List All Showings\n"
                    + "0. Return\n");
            int choice = InputHandler.scanInt();
            switch (choice) {
                case 1:
                    searchShowingById();
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

    public static void searchShowingById() {
        System.out.printf("Input showing id: ");
        int id = InputHandler.scanInt();
        Showing showing = searchShowingObject(id);
        if (showing == null)
            return;
        System.out.println(showing);
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

    // TODO: refactor
    public static void listAll() {
        ArrayList<Showing> showings = showingController.readFromDatabase();
        if (showings == null) {
            System.out.println("No showings exist in Showing database!");
        }
        for (Showing showing : showings) {
            System.out.println(showing);
        }
    }

    // TODO: refactor
    public static void listAll(Cineplex cineplex, Movie movie) { // Overloaded listAll function to find all showings
                                                                 // with specific cineplex and movie. Method called in
                                                                 // MovieGoerUI.checkSeatAvailability
        ArrayList<Showing> showingList = showingController.readFromDatabase();
        boolean showingFound = false;
        for (Showing showing : showingList) {
            if ((showing.getCineplex().equals(cineplex)) && (showing.getMovie().equals(movie))) {
                showingFound = true;
                System.out.println(showing);
            }
        }
        if (!showingFound) {
            System.out.println("No showing of " + movie.getTitle() + " is found at " + cineplex.getName());
        }
    }
}
