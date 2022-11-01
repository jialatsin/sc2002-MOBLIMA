package boundary;

import java.util.ArrayList;

import control.CineplexController;
import control.MovieController;
import control.ShowingController;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Showing;
import entity.Constants.Age;
import entity.Constants.CinemaClass;
import entity.Constants.ContentRating;
import entity.Constants.Day;
import entity.Constants.MovieType;
import entity.Constants.ShowingStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

// Handle user input of custom-defined datatypes
public class UserHandler {
    private static MovieController movieController = new MovieController();
    private static CineplexController cineplexController = new CineplexController();
    private static ShowingController showingController = new ShowingController();

    public static int getIdFromUser() {
        System.out.println("Enter ID:");
        int id = InputHandler.scanInt();
        return id;
    }

    public static String getTitleFromUser() {
        System.out.println("Enter title:");
        String title = InputHandler.scanString();
        return title;
    }

    public static String getSynopsisFromUser() {
        System.out.println("Enter synopsis:");
        String synopsis = InputHandler.scanString();
        return synopsis;
    }

    public static String getDirectorFromUser() {
        System.out.println("Enter director:");
        String director = InputHandler.scanString();
        return director;
    }

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

    public static LocalDate getReleaseDateFromUser() {
        System.out.println("Enter release date 'dd/MM/yyyy':");
        LocalDate date = InputHandler.scanDate();
        return date;
    }

    public static LocalDate getEndDateFromUser() {
        System.out.println("Enter end date 'dd/MM/yyyy':");
        LocalDate date = InputHandler.scanDate();
        return date;
    }

    public static LocalDateTime getShowTimeFromUser() {
        System.out.println("\nEnter show timing 'dd/MM/yyyy HH:mm':");
        LocalDateTime showTime = InputHandler.scanDateTime();
        return showTime;
    }

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

    // Prompts a movie for a movie ID
    // Returns selected movie
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

    // Prompts user to select from a list of available cineplexes
    // Returns selected cineplex
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

    // Prompts user to select from a list of available cinemas for a known cineplex
    // Returns selected cinema
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

    // Prompts user for movie title and prints all movies with matching movie
    // title, then prompts user to choose movie ID
    // Returns selected movie
    public static Movie getMovieByTitleFromUser() {
        // Prompts user for movie title
        String title = UserHandler.getTitleFromUser();
        ArrayList<Movie> movies = movieController.findMovies(title);

        // Prints all movies with matching movie
        if (movies == null) {
            System.out.println("Movie not found in Movie database!");
            return null;
        }
        System.out.print("\n=================================================");
        for (Movie m : movies) {
            System.out.print(m);
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

    // Prompts user for cineplex and movie, prints all showings for given cineplex
    // and movie, then prompts user to choose showing ID
    // Returns selected showing
    public static Showing getShowingFromUser() {
        Cineplex cineplex = getCineplexFromUser();
        Movie movie = getMovieByTitleFromUser();
        if (cineplex == null || movie == null) {
            return null;
        }

        if (movie.getShowingStatus() != ShowingStatus.NOW_SHOWING
                && movie.getShowingStatus() != ShowingStatus.PREVIEW) {
            System.out.println("Movie is currently not showing!");
        }

        // List all showings for user to pick one
        SearchShowingUI.listAllShowings(cineplex, movie);
        System.out.println("Input movie showing ID:");
        int showingId = InputHandler.scanInt();

        Showing showing = showingController.findShowing(showingId);
        if (showing == null) {
            System.out.println("Showing of ID " + showingId + "not found in Showing database!");
            return null;
        }
        return showing;
    }

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

    public static Age getAgeFromUser() {
        int selection;
        Age age = null;
        do {
            System.out.println("\nSelect Age:\n"
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
