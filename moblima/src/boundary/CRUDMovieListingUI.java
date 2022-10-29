package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

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
                    MovieGoerUI.searchMovie();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    private static void createMovieListing() {
        System.out.println("\nCREATING A MOVIE LISTING...");

        int id = getIdFromUser();
        String title = getTitleFromUser();
        String synopsis = getSynopsisFromUser();
        String director = getDirectorFromUser();
        ArrayList<String> cast = getCastFromUser();
        ArrayList<String> genres = getGenresFromUser();
        LocalDate releaseDate = getReleaseDateFromUser();
        ContentRating contentRating = getContentRatingFromUser();
        MovieType movieType = getMovieTypeFromUser();

        movieController.addToDatabase(
                new Movie(id, title, synopsis, director, cast, genres, releaseDate, contentRating, movieType));

        System.out.println(title + " (id: " + id + ") added to Movie database!");
    }

    // TODO
    private static void updateMovieListing() {
        System.out.println("\nUPDATING A MOVIE LISTING...");

        int id = getIdFromUser();
        Movie movie = movieController.getMovieById(id);
        if (movie == null) {
            System.out.println("Movie of ID " + id + " does not exist in Movie database!");
            return;
        }

        System.out.println(movie);

        int selection;
        do {
            System.out.println("\nSelect attribute to update for movie\n"
                    + "1. ID\n"
                    + "2. Title\n"
                    + "3. Synopsis\n"
                    + "4. Director\n"
                    + "5. Cast\n"
                    + "6. Genres\n"
                    + "7. Release Date\n"
                    + "8. Content Rating\n"
                    + "9. Movie Type\n");
            selection = InputHandler.scanInt();
        } while (selection < 1 || selection > 9);

        switch (selection) {
            case 1:
                int newId = getIdFromUser();
                movieController.updateMovieAttribute(movie, selection, newId);
                break;
            case 2:
                String title = getTitleFromUser();
                movieController.updateMovieAttribute(movie, selection, title);
                break;
            case 3:
                String synopsis = getSynopsisFromUser();
                movieController.updateMovieAttribute(movie, selection, synopsis);
                break;
            case 4:
                String director = getDirectorFromUser();
                movieController.updateMovieAttribute(movie, selection, director);
                break;
            case 5:
                ArrayList<String> cast = getCastFromUser();
                movieController.updateMovieAttribute(movie, selection, cast);
                break;
            case 6:
                ArrayList<String> genres = getGenresFromUser();
                movieController.updateMovieAttribute(movie, selection, genres);
                break;
            case 7:
                LocalDate date = getReleaseDateFromUser();
                movieController.updateMovieAttribute(movie, selection, date);
                break;
            case 8:
                ContentRating contentRating = getContentRatingFromUser();
                movieController.updateMovieAttribute(movie, selection, contentRating);
                break;
            case 9:
                MovieType movieType = getMovieTypeFromUser();
                movieController.updateMovieAttribute(movie, selection, movieType);
                break;
        }

        System.out.println("\nUpdated movie details!");
    }

    private static void deleteMovieListing() {
        System.out.println("\nDELETING A MOVIE LISTING...");

        int id = getIdFromUser();
        if (movieController.deleteMovieById(id)) {
            System.out.println("Deleted movie with ID " + id + "!");
        } else {
            System.out.println("Unable to delete movie with ID " + id + "!");
        }
    }

    private static int getIdFromUser() {
        System.out.println("Enter ID:");
        int id = InputHandler.scanInt();
        return id;
    }

    private static String getTitleFromUser() {
        System.out.println("Enter title:");
        String title = InputHandler.scanString();
        return title;
    }

    private static String getSynopsisFromUser() {
        System.out.println("Enter synopsis:");
        String synopsis = InputHandler.scanString();
        return synopsis;
    }

    private static String getDirectorFromUser() {
        System.out.println("Enter director:");
        String director = InputHandler.scanString();
        return director;
    }

    private static ArrayList<String> getCastFromUser() {
        int selection;
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
        return cast;
    }

    private static ArrayList<String> getGenresFromUser() {
        int selection;
        System.out.println("\nEnter number of genres:");
        selection = InputHandler.scanInt();
        ArrayList<String> genres = new ArrayList<String>();
        for (int i = 0; i < selection; i++) {
            System.out.println("Enter genre " + (i + 1) + ":");
            String genre = InputHandler.scanString();
            genres.add(genre);
        }
        return genres;
    }

    private static LocalDate getReleaseDateFromUser() {
        System.out.println("Enter release date:");
        LocalDate date = InputHandler.scanDate();
        return date;
    }

    private static ContentRating getContentRatingFromUser() {
        int selection;
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
        return contentRating;
    }

    private static MovieType getMovieTypeFromUser() {
        int selection;
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
        return movieType;
    }

}
