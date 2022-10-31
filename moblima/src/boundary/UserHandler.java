package boundary;

import java.util.ArrayList;

import control.CineplexController;
import control.MovieController;
import entity.Cinema;
import entity.Cineplex;
import entity.Movie;
import entity.Constants.ContentRating;
import entity.Constants.MovieType;
import java.time.LocalDate;
import java.time.LocalDateTime;

// Handle user input of custom-defined datatypes
public class UserHandler {
    private static MovieController movieController = new MovieController();
    private static CineplexController cineplexController = new CineplexController();

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

    public static Movie getMovieFromUser() {
        System.out.println("\nEnter movie ID:");
        int id = InputHandler.scanInt();
        Movie movie = movieController.getMovieById(id);
        if (movie == null) {
            System.out.println("Movie of ID " + id + " does not exist in Movie database!");
            return null;
        }
        return movie;
    }

    // Prompts user to select from a list of available cineplexes
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
}
