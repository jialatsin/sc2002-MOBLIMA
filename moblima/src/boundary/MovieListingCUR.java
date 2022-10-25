package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import control.MovieController;
import entity.Movie;

public class MovieListingCUR {
    public static void main() {
        int selection;
        do {
            System.out.println("\n===== MOVIE LISTINGS =====\n"
                    + "1. Create Movie Listing\n"
                    + "2. Update Movie Listing\n"
                    + "3. Remove Movie Listing\n"
                    + "4. Search/List Movies\n"
                    + "5. Return to Admin Menu\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    createMovieListing();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    // TODO: Replace with Search/List Movies method
                    // from MovieGoerUI. Currently just lists all movies
                    ArrayList<Movie> movies = MovieController.readFromDatabase();
                    for (Movie movie : movies) {
                        System.out.println(movie);
                    }
                case 5:
                    return;
            }
        } while (true);
    }

    private static void createMovieListing() {
        int n; // Temporary counter
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
            n = InputHandler.scanInt();
        } while (n < 2);
        ArrayList<String> cast = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter name of cast member " + (i + 1) + ":");
            String castMember = InputHandler.scanString();
            cast.add(castMember);
        }

        System.out.println("\nEnter number of genres:");
        n = InputHandler.scanInt();
        ArrayList<String> genres = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter genre " + (i + 1) + ":");
            String genre = InputHandler.scanString();
            genres.add(genre);
        }

        System.out.println("\nEnter release date (dd/MM/yyyy):");
        LocalDate releaseDate = InputHandler.scanDate();

        Movie.ContentRating contentRating = null;
        do {
            System.out.println("\nSelect movie content rating:\n"
                    + "1. G\n"
                    + "2. PG\n"
                    + "3. PG13\n"
                    + "4. R\n"
                    + "5. NC17");

            n = InputHandler.scanInt();
            switch (n) {
                case 1:
                    contentRating = Movie.ContentRating.G;
                    break;
                case 2:
                    contentRating = Movie.ContentRating.PG;
                    break;
                case 3:
                    contentRating = Movie.ContentRating.PG13;
                    break;
                case 4:
                    contentRating = Movie.ContentRating.R;
                    break;
                case 5:
                    contentRating = Movie.ContentRating.NC17;
                    break;
            }
        } while (n < 1 || n > 5);

        Movie.MovieType movieType = null;
        do {
            System.out.println("\nSelect movie type:\n"
                    + "1. Regular 2D\n"
                    + "2. Regular 3D\n"
                    + "3. Blockbuster 2D\n"
                    + "4. Blockbuster 3D");

            n = InputHandler.scanInt();
            switch (n) {
                case 1:
                    movieType = Movie.MovieType.REGULAR_TWO_D;
                    break;
                case 2:
                    movieType = Movie.MovieType.REGULAR_THREE_D;
                    break;
                case 3:
                    movieType = Movie.MovieType.BLOCKBUSTER_TWO_D;
                    break;
                case 4:
                    movieType = Movie.MovieType.BLOCKBUSTER_THREE_D;
                    break;
            }
        } while (n < 1 || n > 4);

        MovieController.addToDatabase(
                new Movie(id, title, synopsis, director, cast, genres, releaseDate, contentRating, movieType));
    }
}
