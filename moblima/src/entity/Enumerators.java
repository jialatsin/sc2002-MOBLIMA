package entity;

/**
 * Enumerators defined for readability and easier referencing
 * @author SSP1 Lab Group 4
 * @version 08/11/2022
 */
public class Enumerators {
	/**
	 * The price type depends on the cinema class chosen by the movie-goer for the show. 
	 * @author OOP SSP1 Lab Group 4
	 * @version 8/11/2022
	 */
    public enum CinemaClass implements PriceType {
    	/**
    	 * The Platinum cinema class 
    	 */
    	PLATINUM("Platinum Class"),
    	/**
    	 * The Gold cinema class   
    	 */
        GOLD("Gold Class"),
        /**
    	 * The Standard cinema class   
    	 */
        STANDARD("Standard");
    	/**
    	 * The cinemaClass of the movie
    	 */
        private String cinemaClass;
        /**
         * The cinema class of the movie
         * @param cinemaClass The cinema class of the movie
         */
        CinemaClass(String cinemaClass) {
            this.cinemaClass = cinemaClass;
        }
        /**
         * The cinema class of the movie
         * @return cinemaClass The cinema class of the movie
         */
        @Override
        public String toString() {
            return cinemaClass;
        }
    };
    /**
     * The status of the seat, whether it is unoccupied or occupied.
     */
    public enum SeatStatus {
    	/**
    	 * It indicates the seat is unoccupied
    	 */
    	UNOCCUPIED,
    	/**
    	 * It indicates the seat is occupied
    	 */
    	 OCCUPIED
    };
    /**
     * The Content ratings are used to inform consumers, especially parents, of potentially objectionable content that is shown during the duration of the movie. 
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     */
    public enum ContentRating {
    	/**
    	 * It indicates the movie is for General Audience.
    	 */
        G, 
        /**
    	 * It indicates Parental Guidance suggested.
    	 */
        PG, 
        /**
    	 * It cautions Parents strongly, since some material may be inappropiate for Children under the age of 13. 
    	 */
        PG13, 
        /**
    	 * It indicates that it is not suitable to be seen by children because of violence, offensive language, or sexual activity.
    	 */
        R, 
        /**
    	 * It indicates that no one aged 17 or under is allowed to see it in a movie theater. 
    	 */
        NC17
    };
    /**
     * The price of the movie ticket depends on the type of the movie, whether it is Regular 2D, Regular 3, Blockbuster 2D,  Blockbuster 3D etc. 
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     *
     */
    public enum MovieType implements PriceType {
    	/**
    	 * It indicates that is a Regular 2D movie. 
    	 */
    	REGULAR_TWO_D("Regular 2D"),
    	/**
    	 * It indicates that is a Regular 3D movie.
    	 */
        REGULAR_THREE_D("Regular 3D"),
        /**
    	 * It indicates that is a Blockbuster 2D movie.
    	 */
        BLOCKBUSTER_TWO_D("Blockbuster 2D"),
        /**
    	 * It indicates that is a Blockbuster 3D movie.
    	 */
        BLOCKBUSTER_THREE_D("Blockbuster 3D");
    	/**
    	 * The type of movie, whether it is regular or blockbuster
    	 */
        private String movieType;
        /**
         * The type of movie, whether it is regular or blockbuster
         * @param movieType The type of movie, whether it is regular or blockbuster
         */
        MovieType(String movieType) {
            this.movieType = movieType;
        }
        /**
         * String to display the type of movie, whether it is regular or blockbuster
         * @return movieType The type of movie, whether it is regular or blockbuster
         */
        @Override
        public String toString() {
            return movieType;
        }
    };
    /**
     * It indicates the status of the movie, whether it is coming soon, preview, currently showing or end of showing. 
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     *
     */
    public enum ShowingStatus {
    	/**
    	 * It indicates that the movie is coming soon. 
    	 */
    	COMING_SOON("Coming Soon"),
    	/**
    	 *It indicates the movie preview. 
    	 */
        PREVIEW("Preview"),
        /**
    	 * It indicates that the movie is now showing.
    	 */
        NOW_SHOWING("Now Showing"),
        /**
    	 * It indicates the end of the movie show.  
    	 */
        END_OF_SHOWING("End Of Showing");
    	/**
    	 * The showing status of the movie
    	 */
        private String showingStatus;
        /**
         * The showing status of the movie
         * @param showingStatus The showing status of the movie
         */
        ShowingStatus(String showingStatus) {
            this.showingStatus = showingStatus;
        }
        /**
         * Get the showing status of the movie
         * @return showingStatus The showing status of the movie
         */
        @Override
        public String toString() {
            return showingStatus;
        }
    };
    /**
     * The pricetype depends on the age category of the movie-goer
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     */
    public enum Age implements PriceType {
    	/**
    	 * The age category between 18 and 60 years  
    	 */
        ADULT("Adult"),
        /**
         * The age category above 60 years
         */
        SENIOR("Senior"),
        /**
         * The age category below 18 years
         */
        CHILD("Child");
    	/**
    	 * The age category of movie=goer
    	 */
        private String age;
        /**
         * The age category of the movie-goer
         * @param age The age category of the movie-goer
         */
        Age(String age) {
            this.age = age;
        }
        /**
         * String to display the age 
         * @return age The age category of the movie-goer
         */
        @Override
        public String toString() {
            return age;
        }
    };
    /**
     * The price type depends on which day of the week the movie show is taking place.  
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     */
    public enum Day implements PriceType {
    	/**
    	 * The holidays which occur in a year because of periods of celebration such as festivals, national days etc. 
    	 */
        HOLIDAY("Holiday"),
        /**
    	 * The weekdays are Monday, Tuesday, Wednesday, Thursday and Friday
    	 */
        WEEKDAY("Weekday"),
        /**
    	 * Saturday and Sunday 
    	 */
        WEEKEND("Weekend");
    	/**
    	 * day of the movie
    	 */
        private String day;
        /**
         * The pricetype depends on day of the movie show 
         * @param day The day of the movie show
         */
        Day(String day) {
            this.day = day;
        }
        /**
         * String to display the day of the movie show
         * @return day The day of the movie show
         */
        @Override
        public String toString() {
            return day;
        }
    };
    /**
     * The type of user, whether it is admin or movie-goer
     * @author OOP SSP1 Lab Group 4
     * @version 8/11/2022
     */
    public enum User {
    	/**
    	 * It indicates the user is a movie-goer
    	 */
    	MOVIEGOER,
    	/**
    	 * It indicates the user is the admin
    	 */
    	ADMIN;
    }
}
