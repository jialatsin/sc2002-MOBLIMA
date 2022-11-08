package boundary;

import java.util.ArrayList;

import control.CineplexController;
import control.MovieController;
import control.ShowingController;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showing;
import entity.Enumerators.Age;
import entity.Enumerators.CinemaClass;
import entity.Enumerators.ContentRating;
import entity.Enumerators.Day;
import entity.Enumerators.MovieType;
import entity.Enumerators.ShowingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Represents a UserHandler which is used to take user input of custom-defined
 * datatypes.
 * Implemented for reuse in multiple methods.
 */
public class UserHandler {
    /**
     * The movie controller to be referenced.
     */
    private static MovieController movieController = new MovieController();
    /**
     * The cineplex controller to be referenced.
     */
    private static CineplexController cineplexController = new CineplexController();
    /**
     * The showing controller to be referenced.
     */
    private static ShowingController showingController = new ShowingController();

    /**
     * Prompts user for an id (integer) input until correct input is given.
     * 
     * @return Returns a valid id.
     */
    public static int getIdFromUser() {
        System.out.println("Enter ID:");
        int id = InputHandler.scanInt();
        return id;
    }

    /**
     * Prompts user for a title (string) input until correct input is given.
     * 
     * @return Returns a valid title.
     */
    public static String getTitleFromUser() {
        System.out.println("Enter title:");
        String title = InputHandler.scanString();
        return title;
    }

    /**
     * Prompts user for a synopsis (string) input until correct input is given.
     * 
     * @return Returns a valid synopsis.
     */
    public static String getSynopsisFromUser() {
        System.out.println("Enter synopsis:");
        String synopsis = InputHandler.scanString();
        return synopsis;
    }

    /**
     * Prompts user for a director (string) input until correct input is given.
     * 
     * @return Returns a valid director.
     */
    public static String getDirectorFromUser() {
        System.out.println("Enter director:");
        String director = InputHandler.scanString();
        return director;
    }

    /**
     * Prompts user to input a list of cast members (at least 2) until correct input
     * is given.
     * 
     * @return Returns a valid list of cast members.
     */
    public static ArrayList<String> getCastFromUser() {
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

    /**
     * Prompts user to input a list of genres until correct input
     * is given.
     * 
     * @return Returns a valid list of genres.
     */
    public static ArrayList<String> getGenresFromUser() {
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

    /**
     * Prompts user for a release date input of format 'dd/MM/yyyy' until
     * correct input is given.
     * 
     * @return Returns a valid release date.
     */
    public static LocalDate getReleaseDateFromUser() {
        System.out.println("Enter release date 'dd/MM/yyyy':");
        LocalDate date = InputHandler.scanDate();
        return date;
    }

    /**
     * Prompts user for a end date input of format 'dd/MM/yyyy' until
     * correct input is given.
     * 
     * @return Returns a valid end date.
     */
    public static LocalDate getEndDateFromUser() {
        System.out.println("Enter end date 'dd/MM/yyyy':");
        LocalDate date = InputHandler.scanDate();
        return date;
    }

    /**
     * Prompts user for a showtime input of format 'dd/MM/yyyy HH:mm'
     * until correct input is given.
     * 
     * @return Returns a valid showtime.
     */
    public static LocalDateTime getShowTimeFromUser() {
        System.out.println("\nEnter show timing 'dd/MM/yyyy HH:mm':");
        LocalDateTime showTime = InputHandler.scanDateTime();
        return showTime;
    }

    /**
     * Prompts user to choose a content rating until correct input is given.
     * 
     * @return Returns a valid content rating.
     */
    public static ContentRating getContentRatingFromUser() {
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

    /**
     * Prompts user to choose a movie type until correct input is given.
     * 
     * @return Returns a valid movie type.
     */
    public static MovieType getMovieTypeFromUser() {
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

    /**
     * Returns a movie by prompting user to input its movie id until correct input
     * is given.
     * 
     * @return Returns movie with the selected movie id.
     */
    public static Movie getMovieFromUser() {
        System.out.println("\nEnter movie ID:");
        int id = InputHandler.scanInt();
        Movie movie = movieController.findMovie(id);
        if (movie == null) {
            System.out.println("Movie of ID " + id + " does not exist in Movie database!");
            return null;
        }
        return movie;
    }

    /**
     * Returns a cineplex by prompting user to choose from a list of available
     * cineplexes.
     * 
     * @return Returns the selected cineplex.
     */
    public static Cineplex getCineplexFromUser() {
        ArrayList<Cineplex> cineplexes = cineplexController.readFromDatabase();
        if (cineplexes.isEmpty()) {
            return null;
        }

        int selection, i;
        do {
            i = 1;
            System.out.println("\nSelect cineplex:");
            for (Cineplex cineplex : cineplexes) {
                System.out.println(i++ + ". " + cineplex.getName());
            }
            selection = InputHandler.scanInt();
        } while (selection >= i || selection < 1);

        Cineplex cineplex = cineplexes.get(selection - 1);
        return cineplex;
    }

    /**
     * Returns a cinema by prompting user to choose from a list of available
     * cinema from a given cineplex.
     * 
     * @param cineplex Given cineplex to select a cinema from.
     * @return Returns the selected cinema.
     */
    public static Cinema getCinemaFromUser(Cineplex cineplex) {
        ArrayList<Cinema> cinemas = cineplex.getCinemas();
        if (cinemas.isEmpty()) {
            return null;
        }

        int selection, i;
        do {
            i = 1;
            System.out.println("\nSelect cinema code:");
            for (Cinema cinema : cinemas) {
                System.out.println(i++ + ". " + cinema.getCode());
            }
            selection = InputHandler.scanInt();
        } while (selection >= i || selection < 1);

        Cinema cinema = cinemas.get(selection - 1);
        return cinema;
    }

    /**
     * Returns a movie by prompting user for movie title, then movie id from a list
     * of movies with matching movie title.
     * User needs to input movie id because multiple movies may have the same title,
     * but each movie will have its own unique id.
     * 
     * @return Returns the selected movie.
     */
    public static Movie getMovieByTitleFromUser() {
        // Prompts user for movie title
        System.out.println("Enter movie title: ");
        String title = InputHandler.scanString();
        ArrayList<Movie> movies = movieController.findMovies(title);

        // Prints all movies with matching movie
        if (movies == null) {
            System.out.println("Movie not found in Movie database!");
            return null;
        }
        System.out.println("\n=================================================");
        for (Movie m : movies) {
            System.out.println(m);
        }
        System.out.println("\n");

        // Prompts user to choose movie ID
        System.out.print("Input movie ID from above results: ");
        int movieId = InputHandler.scanInt();
        Movie movie = movieController.findMovie(movieId);
        if (movie == null) {
            System.out.println("Movie of ID " + movieId + " does not exist in Movie database!");
            return null;
        }
        return movie;
    }

    /**
     * Returns a showing by prompting user for cineplex and movie, then showing id
     * from a list of showings with matching cineplex and movie.
     * User needs to input showing id because there may be multiple showings for the
     * same movie at the same cineplex.
     * 
     * @return Returns the selected showing.
     */
    public static Showing getShowingFromUser() {
        Movie movie = getMovieByTitleFromUser();
        if (movie == null) {
            return null;
        }

        if (!movie.getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                && !movie.getShowingStatus().equals(ShowingStatus.PREVIEW)) {
            System.out.println("Movie is currently not showing!");
            return null;
        }
        Cineplex cineplex = getCineplexFromUser();
        if (cineplex == null || movie == null) {
            return null;
        }

        // List all showings for user to pick one
        ArrayList<Showing> showings = showingController.findShowings(cineplex, movie);
        if (showings == null) {
            System.out.println(
                    "\nNo showings of " + movie.getTitle() + " are currently available at " + cineplex.getName() + "!");
            return null;
        }

        ArrayList<Showing> showingsResult = new ArrayList<Showing>();
        for (Showing showing : showings) {
            // Moviegoer only can view showings with "Preview" or "Now Showing" status and
            // showtime have not already passed current time
            if (!showing.getMovie().getShowingStatus().equals(ShowingStatus.NOW_SHOWING)
                    && !showing.getMovie().getShowingStatus().equals(ShowingStatus.PREVIEW)
                    || showing.getShowTime().isBefore(LocalDateTime.now())) {
                continue;
            }
            showingsResult.add(showing);
        }

        if (showingsResult.isEmpty()) {
            System.out.println(
                    "\nNo showings of " + movie.getTitle() + " are currently available at " + cineplex.getName() + "!");
            return null;
        }
        System.out.println("\nShowings of " + movie.getTitle() + " at " + cineplex.getName());
        System.out.println("=================================================");
        for (Showing showing : showingsResult) {
            System.out.println(showing);
        }
        System.out.println();

        System.out.println("Input movie showing ID:");
        int showingId = InputHandler.scanInt();

        Showing showing = showingController.findShowing(showingId);
        if (showing == null) {
            System.out.println("Showing of ID " + showingId + " not found in Showing database!");
            return null;
        }
        return showing;
    }

    /**
     * Returns a cinema class by prompting user to choose from a list of available
     * cinema classes.
     * 
     * @return Returns the selected cinema class.
     */
    public static CinemaClass getCinemaClassFromUser() {
        int selection;
        CinemaClass cinemaClass = null;
        do {
            System.out.println("\nSelect Cinema Class:\n"
                    + "1. Platinum\n"
                    + "2. Gold\n"
                    + "3. Standard\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    cinemaClass = CinemaClass.PLATINUM;
                    break;
                case 2:
                    cinemaClass = CinemaClass.GOLD;
                    break;
                case 3:
                    cinemaClass = CinemaClass.STANDARD;
                    break;
            }
        } while (selection < 1 || selection > 3);
        return cinemaClass;
    }

    /**
     * Returns an age category by prompting user to choose from a list of available
     * age categories.
     * 
     * @return Returns the selected age category.
     */
    public static Age getAgeFromUser() {
        int selection;
        Age age = null;
        do {
            System.out.println("Select Age:\n"
                    + "1. Adult\n"
                    + "2. Senior\n"
                    + "3. Child\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    age = Age.ADULT;
                    break;
                case 2:
                    age = Age.SENIOR;
                    break;
                case 3:
                    age = Age.CHILD;
                    break;
            }
        } while (selection < 1 || selection > 3);
        return age;
    }

    /**
     * Returns the type of day by prompting user to choose from a list of available
     * types of day.
     * 
     * @return Returns the selected type of day.
     */
    public static Day getDayFromUser() {
        int selection;
        Day day = null;
        do {
            System.out.println("\nSelect Day:\n"
                    + "1. Holiday\n"
                    + "2. Weekday\n"
                    + "3. Weekend\n");

            selection = InputHandler.scanInt();
            switch (selection) {
                case 1:
                    day = Day.HOLIDAY;
                    break;
                case 2:
                    day = Day.WEEKDAY;
                    break;
                case 3:
                    day = Day.WEEKEND;
                    break;
            }
        } while (selection < 1 || selection > 3);
        return day;
    }
}
