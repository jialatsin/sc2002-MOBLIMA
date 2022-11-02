package entity;

public class Constants {

    public enum CinemaClass implements PriceType {
        PLATINUM("Platinum Class"),
        GOLD("Gold Class"),
        STANDARD("Standard");

        private String cinemaClass;

        CinemaClass(String cinemaClass) {
            this.cinemaClass = cinemaClass;
        }

        @Override
        public String toString() {
            return cinemaClass;
        }
    };

    public enum SeatStatus {
        UNOCCUPIED, OCCUPIED
    };

    public enum ContentRating {
        G, PG, PG13, R, NC17
    };

    public enum MovieType implements PriceType {
        REGULAR_TWO_D("Regular 2D"),
        REGULAR_THREE_D("Regular 3D"),
        BLOCKBUSTER_TWO_D("Blockbuster 2D"),
        BLOCKBUSTER_THREE_D("Blockbuster 3D");

        private String movieType;

        MovieType(String movieType) {
            this.movieType = movieType;
        }

        @Override
        public String toString() {
            return movieType;
        }
    };

    public enum ShowingStatus {
        COMING_SOON("Coming Soon"),
        PREVIEW("Preview"),
        NOW_SHOWING("Now Showing"),
        END_OF_SHOWING("End Of Showing");

        private String showingStatus;

        ShowingStatus(String showingStatus) {
            this.showingStatus = showingStatus;
        }

        @Override
        public String toString() {
            return showingStatus;
        }
    };

    public enum Age implements PriceType {
        ADULT("Adult"),
        SENIOR("Senior"),
        CHILD("Child");

        private String age;

        Age(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return age;
        }
    };

    public enum Day implements PriceType {
        HOLIDAY("Holiday"),
        WEEKDAY("Weekday"),
        WEEKEND("Weekend");

        private String day;

        Day(String day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return day;
        }
    };

    public enum User {
        MOVIEGOER, ADMIN;
    }
}
