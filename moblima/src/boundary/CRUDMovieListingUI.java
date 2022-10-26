package boundary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.DelayQueue;

import control.MovieController;
import entity.Movie;
import entity.Constants.ContentRating;
import entity.Constants.MovieType;

public class CRUDMovieListingUI {
    private static MovieController movieController = new MovieController();

    public static void main() {
        int selection;
        do {
            System.out.println("\n===== MOVIE LISTINGS =====\n"
                    + "1. Create Movie Listing\n"
                    + "2. Update Movie Listing\n"
                    + "3. Delete Movie Listing\n"
                    + "4. Search/List Movies\n"
                    + "0. Return to Admin Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    createMovieListing();
                    break;
                case 2:
                    updateMovieListing();
                    break;
                case 3:
                    deleteMovieListing();
                    break;
                case 4:
                    MovieGoerUI.searchMovie();;
                case 0:
                    return;
            }
        } while (true);
    }

    private static void createMovieListing() {
        int selection;
        System.out.println("\nCREATING A MOVIE LISTING...");

        System.out.println("Enter movie ID:");
        int id = InputHandler.scanInt();

        System.out.println("\nEnter movie title:");
        String title = InputHandler.scanString();

        System.out.println("\nEnter movie synopsis:");
        String synopsis = InputHandler.scanString();

        System.out.println("\nEnter movie director name:");
        String director = InputHandler.scanString();

        do {
            System.out.println("\nEnter number of cast members (at least 2):");
            selection = InputHandler.scanInt();
        } while (selection < 2);
        ArrayList<String> cast = new ArrayList<String>();
        for (int i = 0; i < selection; i++) {
            System.out.println("Enter name of cast member " + (i + 1) + ":");
            String castMember = InputHandler.scanString();
            cast.add(castMember);
        }

        System.out.println("\nEnter number of genres:");
        selection = InputHandler.scanInt();
        ArrayList<String> genres = new ArrayList<String>();
        for (int i = 0; i < selection; i++) {
            System.out.println("Enter genre " + (i + 1) + ":");
            String genre = InputHandler.scanString();
            genres.add(genre);
        }

        System.out.println("\nEnter release date:");
        LocalDate releaseDate = InputHandler.scanDate();

        ContentRating contentRating = null;
        do {
            System.out.println("\nSelect movie content rating:\n"
                    + "1. G\n"
                    + "2. PG\n"
                    + "3. PG13\n"
                    + "4. R\n"
                    + "5. NC17");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    contentRating = ContentRating.G;
                    break;
                case 2:
                    contentRating = ContentRating.PG;
                    break;
                case 3:
                    contentRating = ContentRating.PG13;
                    break;
                case 4:
                    contentRating = ContentRating.R;
                    break;
                case 5:
                    contentRating = ContentRating.NC17;
                    break;
            }
        } while (selection < 1 || selection > 5);

        MovieType movieType = null;
        do {
            System.out.println("\nSelect movie type:\n"
                    + "1. Regular 2D\n"
                    + "2. Regular 3D\n"
                    + "3. Blockbuster 2D\n"
                    + "4. Blockbuster 3D");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    movieType = MovieType.REGULAR_TWO_D;
                    break;
                case 2:
                    movieType = MovieType.REGULAR_THREE_D;
                    break;
                case 3:
                    movieType = MovieType.BLOCKBUSTER_TWO_D;
                    break;
                case 4:
                    movieType = MovieType.BLOCKBUSTER_THREE_D;
                    break;
            }
        } while (selection < 1 || selection > 4);

        movieController.addToDatabase(
                new Movie(id, title, synopsis, director, cast, genres, releaseDate, contentRating, movieType));

        System.out.println(title + " (id: " + id + ") added to Movie database!");
    }

    // TODO
    private static void updateMovieListing() {
        System.out.println("\nUPDATING A MOVIE LISTING...");

        System.out.println("Enter ID of movie to update:");
        int id = InputHandler.scanInt();
        if (movieController.getMovieById(id) == null) {
            System.out.println("Movie of ID " + id + " does not exist in Movie database!");
            return;
        }

        System.out.println("\nSelect attribute to update for movie"
                + "1. ID\n"
                + "2. Title\n"
                + "3. Synopsis\n"
                + "4. Director\n"
                + "5. Cast\n"
                + "6. Genres\n"
                + "7. Release Date\n"
                + "8. Content Rating\n"
                + "9. Movie Type\n");
    }

    private static void deleteMovieListing() {
        System.out.println("\nDELETING A MOVIE LISTING...");

        System.out.println("Enter ID of movie to delete:");
        int id = InputHandler.scanInt();
        if (movieController.deleteMovieById(id)) {
            System.out.println("Deleted movie with ID " + id + "!");
        } else {
            System.out.println("Unable to delete movie with ID " + id + "!");
        }
    }

}
