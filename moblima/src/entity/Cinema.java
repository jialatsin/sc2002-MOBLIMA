package entity;

public class Cinema {
    public enum CinemaClass {
        PLATINUM, GOLD
    };

    private String code;
    private SeatingLayout seatingLayout;
    private CinemaClass cinemaClass;

    public Cinema(String code, SeatingLayout seatingLayout, CinemaClass cinemaClass) {
        this.code = code;
        this.seatingLayout = seatingLayout;
        this.cinemaClass = cinemaClass;
    }
}
