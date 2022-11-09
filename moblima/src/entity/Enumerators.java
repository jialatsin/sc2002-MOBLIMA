package entity;

/** Enumerators defined for readability and easier referencing. */
public class Enumerators {

    /**
     * Enumerators to define the various cinema classes.
     * Implements {@link PriceType} as it affects the price of a movie ticket.
     */
    public enum CinemaClass implements PriceType {
        /** Platinum cinema class. */
        PLATINUM("Platinum Class"),
        /** Gold cinema class. */
        GOLD("Gold Class"),
        /** Standard cinema class. */
        STANDARD("Standard");

        /** String containing the cinema class. */
        private String cinemaClass;

        /**
         * Creates a CinemaClass with the given attribute.
         * 
         * @param cinemaClass The input cinema class.
         */
        CinemaClass(String cinemaClass) {
            this.cinemaClass = cinemaClass;
        }

        /**
         * Returns a string containing the cinema class.
         * 
         * @return String containing the cinema class.
         */
        @Override
        public String toString() {
            return cinemaClass;
        }
    };

    /**
     * Enumerators to define the various seat statuses.
     */
    public enum SeatStatus {
        /** Indicates that the seat is currently unoccupied. */
        UNOCCUPIED,
        /** Indicates that the seat is currently occupied. */
        OCCUPIED
    };

    /**
     * Enumerators to define the various movie content ratings.
     * Content ratings are used to inform consumers of potentionally objectionable
     * content that is shown during the duration of the movie.
     */
    public enum ContentRating {
        /** Indicates that the movie is for general audiences. */
        G,
        /** Indicates that some parental guidance is suggested for the movie. */
        PG,
        /**
         * Indicates that some materials may be inappropriate for children under 13, and
         * cautions parents strongly.
         */
        PG13,
        /**
         * Indicates that the movie is not suitable to be seen by children because of
         * violence, offensive language, or sexual activity.
         */
        R,
        /** Indicates that no one aged 17 or under is allowed to view the movie. */
        NC17
    };

    /**
     * Enumerators to define the various movie types.
     * Implements {@link PriceType} as it affects the price of a movie ticket.
     */
    public enum MovieType implements PriceType {
        /** Regular two-dimensional movie. */
        REGULAR_TWO_D("Regular 2D"),
        /** Regular three-dimensional movie. */
        REGULAR_THREE_D("Regular 3D"),
        /** Blockbuster two-dimensional movie. */
        BLOCKBUSTER_TWO_D("Blockbuster 2D"),
        /** Blockbuster three-dimensional movie. */
        BLOCKBUSTER_THREE_D("Blockbuster 3D");

        /** String containing the movie type. */
        private String movieType;

        /**
         * Creates a MovieType with the given attribute.
         * 
         * @param movieType The input movie type.
         */
        MovieType(String movieType) {
            this.movieType = movieType;
        }

        /**
         * Returns a string containing the movie type.
         * 
         * @return String containing the movie type.
         */
        @Override
        public String toString() {
            return movieType;
        }
    };

    /**
     * Enumerators to define the various showing statuses.
     */
    public enum ShowingStatus {
        /** Indicates that the movie is coming soon. */
        COMING_SOON("Coming Soon"),
        /** Indicates that the movie is previewing. */
        PREVIEW("Preview"),
        /** Indicates that the movie is now showing. */
        NOW_SHOWING("Now Showing"),
        /** Indicates that the movie is no longer showing. */
        END_OF_SHOWING("End Of Showing");

        /** String containing the showing status. */
        private String showingStatus;

        /**
         * Creates a ShowingStatus with the given attribute.
         * 
         * @param showingStatus The input showing status.
         */
        ShowingStatus(String showingStatus) {
            this.showingStatus = showingStatus;
        }

        /**
         * Returns a string containing the showing status.
         * 
         * @return String containing the showing status.
         */
        @Override
        public String toString() {
            return showingStatus;
        }
    };

    /**
     * Enumerators to define the age category of the moviegoer.
     * Implements {@link PriceType} as it affects the price of a movie ticket.
     */
    public enum Age implements PriceType {
        /** Adult category for those aged between 18 and 60 years. */
        ADULT("Adult"),
        /** Senior category for those aged above 60 years. */
        SENIOR("Senior"),
        /** Child category for those aged below 18 years. */
        CHILD("Child");

        /** String containing the age category. */
        private String age;

        /**
         * Creates an Age category with the given attribute.
         * 
         * @param age The input age.
         */
        Age(String age) {
            this.age = age;
        }

        /**
         * Returns a string containing the age category.
         * 
         * @return String containing the age category.
         */
        @Override
        public String toString() {
            return age;
        }
    };

    /**
     * Enumerators to define the type of day a movie showing falls on.
     * Implements {@link PriceType} as it affects the price of a movie ticket.
     */
    public enum Day implements PriceType {
        /**
         * Holidays which occur in a year because of periods of celebration such as
         * festivals, national days, etc.
         */
        HOLIDAY("Holiday"),
        /** Weekdays are Monday, Tuesday, Wednesday, Thursday and Friday. */
        WEEKDAY("Weekday"),
        /** Weekends are Saturday and Sunday. */
        WEEKEND("Weekend");

        /** String containing the type of day. */
        private String day;

        /**
         * Creates a Day category with the given attribute.
         * 
         * @param day The input day.
         */
        Day(String day) {
            this.day = day;
        }

        /**
         * Returns a string containing the type of day.
         * 
         * @return String containing the type of day.
         */
        @Override
        public String toString() {
            return day;
        }
    };

    /**
     * Enumerators to define the various users.
     */
    public enum User {
        /** Indicates the user is a moviegoer. */
        MOVIEGOER,
        /** Indicates the user is an admin with adminstrative powers. */
        ADMIN;
    }

    /**
     * Defined for readability and easier referencing of Movie attributes.
     * In the order of selection options provided to user when updating movie
     * attributes.
     */
    public enum MovieAttribute {
        /** Unique identifier for the movie. */
        ID,
        /** Title of the movie. */
        TITLE,
        /** Synopsis of the movie. */
        SYNOPSIS,
        /** Director of the movie. */
        DIRECTOR,
        /** List of cast members for the movie. */
        CAST,
        /** List of genres for the movie. */
        GENRES,
        /** Release date of the movie. */
        RELEASE_DATE,
        /** Date when the movie stops showing. */
        END_DATE,
        /** Content rating of the movie (eg. PG, NC17). */
        CONTENT_RATING,
        /** Type of the movie (eg. Blockbuster 3D). */
        MOVIE_TYPE,
        /** Total tickets sold for the movie. */
        TICKET_SALES;

        /**
         * Returns the mapping of the user input selection to the attribute enumerator.
         * User selection starts from 1, but enumerator counting starts from 0.
         * 
         * @param i Input user selection.
         * @return Returns the mapping of the user input selection to the attribute
         *         enumerator.
         */
        public static MovieAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }

    /**
     * Defined for readability and easier referencing of Showing attributes.
     * In the order of selection options provided to user when updating showing
     * attributes.
     */
    public enum ShowingAttribute {
        /** Unique identifier for the showing. */
        ID,
        /** Movie being shown. */
        MOVIE,
        /** Showtime of the showing. */
        SHOWTIME,
        /** Cinema where the showing is being held. */
        CINEMA;

        /**
         * Returns the mapping of the user input selection to the attribute enumerator.
         * User selection starts from 1, but enumerator counting starts from 0.
         * 
         * @param i Input user selection.
         * @return Returns the mapping of the user input selection to the attribute
         *         enumerator.
         */
        public static ShowingAttribute get(int i) {
            return values()[i - 1]; // User selection starts from 1, but enum counting starts from 0
        }
    }
}
