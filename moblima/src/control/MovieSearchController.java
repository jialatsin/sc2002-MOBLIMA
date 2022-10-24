package control;

import java.util.*;
import entity.Movie;
import entity.Movie.*;

import java.nio.file.attribute.FileStoreAttributeView;
import java.time.LocalDate;

public class MovieSearchController {
    // Create thor movie from scratch
    public static ArrayList<String> cast = new ArrayList<>(List.of("Chris Hemsworth", "Natalie Portman"));
    public static ArrayList<String> genres = new ArrayList<>(List.of("Superhero", "Action"));
    public static LocalDate releaseDate = LocalDate.of(2011, 4, 28);
    public static Movie thor = new Movie(00001, "Thor", "Lightning guy go brrrr", "Tanaka Waititi", cast, genres,
            releaseDate,
            ContentRating.PG13, MovieType.BLOCKBUSTER, ShowingStatus.NOW_SHOWING);

    public static ArrayList<String> cast2 = new ArrayList<>(List.of("Will Smith", "Tommy Lee Jones"));
    public static ArrayList<String> genres2 = new ArrayList<>(List.of("Sci-Fi", "Comedy"));
    public static LocalDate releaseDate2 = LocalDate.of(1997, 7, 31);
    public static Movie meninblack = new Movie(00002, "Men in Black", "Spy pew pew flash", "Barry Sonnenfield", cast2,
            genres2,
            releaseDate2, ContentRating.NC17, MovieType.BLOCKBUSTER, ShowingStatus.NOW_SHOWING);

    public static void searchTitle(String movieTitle) {
        ArrayList<Movie> MovieList = new ArrayList<Movie>();
        MovieList.add(thor);
        MovieList.add(meninblack);
        int movieFound = 0;
        for (Movie i : MovieList) {
            if ((movieTitle.toLowerCase()).compareTo(i.getTitle().toLowerCase()) == 0) {
                movieFound = 1;
                System.out.println("\n=================================================");
                System.out.println("Movie: " + i.getTitle());
                System.out.println("Release Date: " + i.getReleaseDate());
                System.out.printf("Genres: ");
                for (String k : i.getGenres())
                    System.out.printf(k + ", ");
                System.out.printf("\n");
                System.out.println("Showing Status: " + i.getShowingStatus());
                System.out.println("=================================================\n");
            }
        }
        if (movieFound == 0) {
            System.out.println("Movie not found");
        }
    }

    public static void listAll() {
        ArrayList<Movie> MovieList = new ArrayList<Movie>();
        MovieList.add(thor);
        MovieList.add(meninblack);
        System.out.println();
        for (Movie i : MovieList) {
            System.out.println("=================================================");
            System.out.println("Movie: " + i.getTitle());
            System.out.println("Release Date: " + i.getReleaseDate());
            System.out.printf("Genres: ");
            for (String k : i.getGenres())
                System.out.printf(k + ", ");
                System.out.printf("\n");
            System.out.println("Showing Status: " + i.getShowingStatus());
        }
        System.out.println("=================================================\n");
    }

    public static void fullMovieDetails(String movieTitle) {
        ArrayList<Movie> MovieList = new ArrayList<Movie>();
        MovieList.add(thor);
        MovieList.add(meninblack);
        int movieFound = 0;
        for (Movie i : MovieList) {
            if ((movieTitle.toLowerCase()).compareTo(i.getTitle().toLowerCase()) == 0) {
                movieFound = 1;
                System.out.println("\n=================================================");
                System.out.println("Movie: " + i.getTitle());
                System.out.println("id: " + i.getId());
                System.out.println("Synopsis: " + i.getSynopsis());
                System.out.println("Director: " + i.getDirector());
                System.out.printf("Cast: ");
                for (String j : i.getCast())
                    System.out.printf(j + ", ");
                System.out.printf("\n");
                System.out.printf("Genres: ");
                for (String k : i.getGenres())
                    System.out.printf(k + ", ");
                System.out.printf("\n");
                System.out.println("Release Date: " + i.getReleaseDate());
                System.out.println("Content Rating: " + i.getContentRating());
                System.out.println("Movie Type: " + i.getMovieType());
                System.out.println("Showing Status: " + i.getShowingStatus());
                System.out.println("=================================================\n");
            }
        }
        if (movieFound == 0) {
            System.out.println("Movie not found");
        }
    }
}
