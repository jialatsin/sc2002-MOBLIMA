package boundary;

import java.time.LocalDate;
import java.util.ArrayList;

import control.MovieController;
import control.ShowingController;
import control.MovieController.MovieAttribute;
import control.ShowingController.ShowingAttribute;
import entity.Movie;
import entity.Showing;
import entity.Constants.ContentRating;
import entity.Constants.MovieType;

public class CRUDMovieListingUI {
    private static MovieController movieController = new MovieController();
    private static ShowingController showingController = new ShowingController();

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
        LocalDate endDate = UserHandler.getEndDateFromUser();
        if (releaseDate.isAfter(endDate)) {
            System.out.println("Release date cannot be after end date!");
            return;
        }
        ContentRating contentRating = UserHandler.getContentRatingFromUser();
        MovieType movieType = UserHandler.getMovieTypeFromUser();

        movieController.addToDatabase(
                new Movie(id, title, synopsis, director, cast, genres, releaseDate, endDate, contentRating, movieType));

        System.out.println(title + " (id: " + id + ") added to Movie database!");
    }

    private static void updateMovieListing() {
        System.out.println("\nUPDATING A MOVIE LISTING...");

        int id = UserHandler.getIdFromUser();
        Movie movie = movieController.findMovie(id);
        if (movie == null) {
            System.out.println("Movie of ID " + id + " does not exist in Movie database!");
            return;
        }

        System.out.println(movie);

        // Get all showings to be updated
        ArrayList<Showing> showings = showingController.findShowings(movie);

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
                    + "8. End Date\n"
                    + "9. Content Rating\n"
                    + "10. Movie Type\n"
                    + "11. Ticket Sales");
            selection = InputHandler.scanInt();
        } while (selection < 1 || selection > 11);

        MovieAttribute attribute = MovieAttribute.get(selection);
        switch (attribute) {
            case ID:
                int newId = UserHandler.getIdFromUser();
                movieController.updateMovieAttribute(movie, attribute, newId);
                break;
            case TITLE:
                String title = UserHandler.getTitleFromUser();
                movieController.updateMovieAttribute(movie, attribute, title);
                break;
            case SYNOPSIS:
                String synopsis = UserHandler.getSynopsisFromUser();
                movieController.updateMovieAttribute(movie, attribute, synopsis);
                break;
            case DIRECTOR:
                String director = UserHandler.getDirectorFromUser();
                movieController.updateMovieAttribute(movie, attribute, director);
                break;
            case CAST:
                ArrayList<String> cast = UserHandler.getCastFromUser();
                movieController.updateMovieAttribute(movie, attribute, cast);
                break;
            case GENRES:
                ArrayList<String> genres = UserHandler.getGenresFromUser();
                movieController.updateMovieAttribute(movie, attribute, genres);
                break;
            case RELEASE_DATE:
                LocalDate releaseDate = UserHandler.getReleaseDateFromUser();
                if (releaseDate.isAfter(movie.getEndDate())) {
                    System.out.println("Release date cannot be after end date!");
                    return;
                }
                showingController.deleteInvalidShowings(movie, releaseDate, movie.getEndDate());
                System.out.println("Deleted all showings with invalid showtimes!");
                // Get all remaining showings to be updated
                showings = showingController.findShowings(movie);
                movieController.updateMovieAttribute(movie, attribute, releaseDate);
                break;
            case END_DATE:
                LocalDate endDate = UserHandler.getEndDateFromUser();
                if (endDate.isBefore(movie.getReleaseDate())) {
                    System.out.println("End date cannot be before release date!");
                    return;
                }
                showingController.deleteInvalidShowings(movie, movie.getReleaseDate(), endDate);
                System.out.println("Deleted all showings with invalid showtimes!");
                // Get all remaining showings to be updated
                showings = showingController.findShowings(movie);
                movieController.updateMovieAttribute(movie, attribute, endDate);
                break;
            case CONTENT_RATING:
                ContentRating contentRating = UserHandler.getContentRatingFromUser();
                movieController.updateMovieAttribute(movie, attribute, contentRating);
                break;
            case MOVIE_TYPE:
                MovieType movieType = UserHandler.getMovieTypeFromUser();
                movieController.updateMovieAttribute(movie, attribute, movieType);
                break;
            case TICKET_SALES:
                int ticketSales = InputHandler.scanInt();
                movieController.updateMovieAttribute(movie, attribute, ticketSales);
                break;
        }

        // Update movie attribute of all showings with the given updated movie
        if (showings != null) {
            for (Showing showing : showings) {
                showingController.updateShowingAttribute(showing, ShowingAttribute.MOVIE, movie);
            }
        }

        System.out.println("\nUpdated movie details!");
    }

    private static void deleteMovieListing() {
        System.out.println("\nDELETING A MOVIE LISTING...");

        int id = UserHandler.getIdFromUser();
        Movie movie = movieController.findMovie(id);
        if (movieController.deleteMovie(id)) {
            System.out.println("Deleted movie with ID " + id + "!");
            showingController.deleteShowing(movie); // Deletes all showings for the movie
        } else {
            System.out.println("Unable to delete movie with ID " + id + "!");
        }
    }
}
