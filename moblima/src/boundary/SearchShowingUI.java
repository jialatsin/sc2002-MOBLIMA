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
                    listAllShowings();
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

    // Print all showings in database
    public static void listAllShowings() {
        ArrayList<Showing> showings = showingController.readFromDatabase();
        if (showings.isEmpty()) {
            System.out.println("No showings exist in Showing database!");
            return;
        }
        for (Showing showing : showings) {
            System.out.println(showing);
        }
    }

    // Overloaded listAllShowings function to print all showings in database with
    // matching cineplex and movie
    public static void listAllShowings(Cineplex cineplex, Movie movie) {
        ArrayList<Showing> showings = showingController.findShowings(cineplex, movie);
        if (showings == null) {
            System.out.println("No showings of " + movie.getTitle() + " is found at " + cineplex.getName() + "!");
            return;
        }
        for (Showing showing : showings) {
            System.out.println(showing);
        }
    }
}
