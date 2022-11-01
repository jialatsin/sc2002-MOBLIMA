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
                    // TODO: Refactor out search movies
                    SearchMovieUI.main();
                    break;
                case 0:
                    return;
            }
        } while (true);
    }

    private static void createMovieListing() {
        System.out.println("\nCREATING A MOVIE LISTING...");

        int id = UserHandler.getIdFromUser();
        if (movieController.findMovie(id) != null) {
            System.out.println("Movie of ID " + id + " already exists in Movie database!");
            return;
        }

        String title = UserHandler.getTitleFromUser();
        String synopsis = UserHandler.getSynopsisFromUser();
        String director = UserHandler.getDirectorFromUser();
        ArrayList<String> cast = UserHandler.getCastFromUser();
        ArrayList<String> genres = UserHandler.getGenresFromUser();
        LocalDate releaseDate = UserHandler.getReleaseDateFromUser();
        ContentRating contentRating = UserHandler.getContentRatingFromUser();
        MovieType movieType = UserHandler.getMovieTypeFromUser();

        movieController.addToDatabase(
                new Movie(id, title, synopsis, director, cast, genres, releaseDate, contentRating, movieType));

        System.out.println(title + " (id: " + id + ") added to Movie database!");
    }

    // TODO
    private static void updateMovieListing() {
        System.out.println("\nUPDATING A MOVIE LISTING...");

        int id = UserHandler.getIdFromUser();
        Movie movie = movieController.findMovie(id);
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
                int newId = UserHandler.getIdFromUser();
                movieController.updateMovieAttribute(movie, selection, newId);
                break;
            case 2:
                String title = UserHandler.getTitleFromUser();
                movieController.updateMovieAttribute(movie, selection, title);
                break;
            case 3:
                String synopsis = UserHandler.getSynopsisFromUser();
                movieController.updateMovieAttribute(movie, selection, synopsis);
                break;
            case 4:
                String director = UserHandler.getDirectorFromUser();
                movieController.updateMovieAttribute(movie, selection, director);
                break;
            case 5:
                ArrayList<String> cast = UserHandler.getCastFromUser();
                movieController.updateMovieAttribute(movie, selection, cast);
                break;
            case 6:
                ArrayList<String> genres = UserHandler.getGenresFromUser();
                movieController.updateMovieAttribute(movie, selection, genres);
                break;
            case 7:
                LocalDate date = UserHandler.getReleaseDateFromUser();
                movieController.updateMovieAttribute(movie, selection, date);
                break;
            case 8:
                ContentRating contentRating = UserHandler.getContentRatingFromUser();
                movieController.updateMovieAttribute(movie, selection, contentRating);
                break;
            case 9:
                MovieType movieType = UserHandler.getMovieTypeFromUser();
                movieController.updateMovieAttribute(movie, selection, movieType);
                break;
        }

        System.out.println("\nUpdated movie details!");
    }

    private static void deleteMovieListing() {
        System.out.println("\nDELETING A MOVIE LISTING...");

        int id = UserHandler.getIdFromUser();
        if (movieController.deleteMovie(id)) {
            System.out.println("Deleted movie with ID " + id + "!");
        } else {
            System.out.println("Unable to delete movie with ID " + id + "!");
        }
    }
}
